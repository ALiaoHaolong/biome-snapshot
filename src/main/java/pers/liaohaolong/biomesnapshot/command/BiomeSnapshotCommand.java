package pers.liaohaolong.biomesnapshot.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.ColumnPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import pers.liaohaolong.biomesnapshot.biomecolor.resolver.DefaultBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.biomecolor.resolver.MainlandOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.biomecolor.resolver.MainlandRiverOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.command.argument.BiomeSnapshotMode;
import pers.liaohaolong.biomesnapshot.command.argument.EnumArgumentType;
import pers.liaohaolong.biomesnapshot.biomecolor.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

public class BiomeSnapshotCommand implements Command<ServerCommandSource> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final BiomeColorResolver defaultBiomeColorResolver = new DefaultBiomeColorResolver();
    private final BiomeColorResolver mainlandOceanBiomeColorResolver = new MainlandOceanBiomeColorResolver();
    private final BiomeColorResolver mainlandRiverOceanBiomeColorResolver = new MainlandRiverOceanBiomeColorResolver();

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        // 发送提示
        context.getSource().sendFeedback(new TranslatableText("command.biome-snapshot.start"), false);

        // 设置文件名与路径
        String fileName = getCurrentTimestamp() + ".png";
        File file = new File("config/biome_snapshot/" + fileName);

        // 获取截图模式
        BiomeSnapshotMode mode = EnumArgumentType.getEnum(context, "mode", BiomeSnapshotMode.class);
        // 获取起始坐标
        ColumnPos pos1 = ColumnPosArgumentType.getColumnPos(context, "pos1");
        // 获取结束坐标
        ColumnPos pos2 = ColumnPosArgumentType.getColumnPos(context, "pos2");

        // 规格化起始坐标
        ColumnPos startPos = new ColumnPos(Math.min(pos1.x, pos2.x),  Math.min(pos1.z, pos2.z));
        // 规格化结束坐标
        ColumnPos endPos = new ColumnPos(Math.max(pos1.x, pos2.x), Math.max(pos1.z, pos2.z));

        // 初始化图片
        int width = endPos.x - startPos.x + 1;
        int height = endPos.z - startPos.z + 1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 未找到生物群系处理器
        Set<String> notFoundBiomes = new LinkedHashSet<>();
        Consumer<RegistryEntry<Biome>> notFountHandler = registryEntry -> notFoundBiomes.add(registryEntry.getKey().get().getValue().getPath());

        // 生物群系颜色管理器
        BiomeColorWrapper biomeColorWrapper = new BiomeColorWrapper(notFountHandler);
        // 配置生物群系颜色解析器
        switch (mode) {
            case DEFAULT:
                biomeColorWrapper.setBiomeColorFinder(defaultBiomeColorResolver);
                break;
            case MAINLAND_OCEAN:
                biomeColorWrapper.setBiomeColorFinder(mainlandOceanBiomeColorResolver);
                break;
            case MAINLAND_RIVER_OCEAN:
                biomeColorWrapper.setBiomeColorFinder(mainlandRiverOceanBiomeColorResolver);
                break;
        }

        // 计算进度
        int total = width * height;
        int currentNum = 0;
        long lastTime = System.currentTimeMillis();
        int lastPercent = 0;

        // 绘制生物群系图片
        ServerWorld world = context.getSource().getWorld();
        for (int x = 0, actualX = startPos.x; x < width; x++, actualX++) {
            for (int z = 0, actualZ = startPos.z; z < height; z++, actualZ++, currentNum++) {
                // 生成当前xyz坐标
                BlockPos currentPos = new BlockPos(
                        actualX,
                        world.getTopY(Heightmap.Type.WORLD_SURFACE, actualX, actualZ),
                        actualZ
                );
                // 设置图片颜色
                image.setRGB(x, z, biomeColorWrapper.getColor(world.getBiome(currentPos)).getRGB());
            }
            // 计算进度
            long currentTime = System.currentTimeMillis();
            int currentPercent = (int) ((double) currentNum / total * 100);
            if (currentTime - lastTime > 1000 && (currentPercent != lastPercent || currentTime - lastTime > 5000)) {
                lastTime = currentTime;
                lastPercent = currentPercent;
                context.getSource().sendFeedback(new LiteralText(lastPercent + "%"), false);
            }
        }

        // 检查文件夹
        File directory = new File("config/biome_snapshot");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 保存图片
        boolean success = false;
        try {
            ImageIO.write(image, "png", file);
            success = true;
        } catch (Exception e) {
            LOGGER.error("Failed to save biome snapshot image", e);
        }

        // 发送反馈
        if (success) {
            BaseText feedback1 = new TranslatableText("command.biome-snapshot.success-prefix");
            BaseText feedback2 = new LiteralText(fileName);
            feedback2.setStyle(feedback2.getStyle().withUnderline(true));
            feedback2.setStyle(feedback2.getStyle().withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getAbsolutePath())));
            context.getSource().sendFeedback(feedback1.append(feedback2), false);
        } else {
            context.getSource().sendFeedback(new TranslatableText("command.biome-snapshot.fail"), false);
            return 0;
        }

        // 打印错误群系
        if (!notFoundBiomes.isEmpty()) {
            BaseText feedback1 = new TranslatableText("command.biome-snapshot.not-found");
            BaseText feedback2 = new LiteralText(String.join(", ", notFoundBiomes));
            context.getSource().sendFeedback(feedback1.append(feedback2), false);
        }
        return 1;
    }

    /**
     * 格式：2024-09-08_15.24.48
     *
     * @return 时间戳
     */
    private static @NotNull String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss"));
    }

}
