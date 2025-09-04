package pers.liaohaolong.biomesnapshot.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.ColumnPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.ColumnPos;
import net.minecraft.world.chunk.ChunkStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolverWrapper;
import pers.liaohaolong.biomesnapshot.command.argument.ColorResolverEnum;
import pers.liaohaolong.biomesnapshot.command.argument.ColorResolverArgumentType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static pers.liaohaolong.biomesnapshot.BiomeSnapshot.MOD_ID;

/**
 * <h3>/biome-snapshot 命令</h3>
 */
public class BiomeSnapshotCommand implements Command<ServerCommandSource> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        // 发送提示
        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.start"), false);

        // 设置文件名与路径
        String fileName = getCurrentTimestamp() + ".png";
        String levelName = context.getSource().getServer().getSaveProperties().getLevelName();
        File directory = new File("config/" + MOD_ID + "/" + levelName);
        File file = new File(directory, fileName);

        // 获取颜色解析器枚举值
        ColorResolverEnum colorResolverEnum = ColorResolverArgumentType.getColorResolverEnum(context, "colorResolver");
        // 获取起始坐标
        ColumnPos pos1 = ColumnPosArgumentType.getColumnPos(context, "from");
        // 获取结束坐标
        ColumnPos pos2 = ColumnPosArgumentType.getColumnPos(context, "to");

        // 规格化起始坐标
        ColumnPos startPos = new ColumnPos(Math.min(pos1.x(), pos2.x()),  Math.min(pos1.z(), pos2.z()));
        // 规格化结束坐标
        ColumnPos endPos = new ColumnPos(Math.max(pos1.x(), pos2.x()), Math.max(pos1.z(), pos2.z()));

        // 初始化图片
        int width = endPos.x() - startPos.x() + 1;
        int height = endPos.z() - startPos.z() + 1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 颜色解析
        ColorResolverWrapper colorResolverWrapper = new ColorResolverWrapper();
        colorResolverWrapper.setColorResolver(colorResolverEnum.getColorResolver());

        // ------------------------------ 区块缓存命中优化 ------------------------------
        // 区块起始坐标
        int startChunkX = ChunkSectionPos.getSectionCoord(startPos.x());
        int startChunkZ = ChunkSectionPos.getSectionCoord(startPos.z());
        // 区块结束坐标
        int endChunkX = ChunkSectionPos.getSectionCoord(endPos.x());
        int endChunkZ = ChunkSectionPos.getSectionCoord(endPos.z());
        // 局部变量优化
        int chunkX, chunkZ;
        int endX, endZ;
        int x, z;
        // ------------------------------ 计算进度/区块回收优化 ------------------------------
        // 区块总数
        int totalCountOfChunk = (endChunkX - startChunkX + 1) * (endChunkZ - startChunkZ + 1);
        // 已处理的区块数
        int chunkCount = 0;
        // 上次反馈时间
        long lastTime = System.currentTimeMillis();
        // 当前时间/局部变量优化
        long currentTime;
        // 当前百分比/局部变量优化
        int currentPercent;
        // 是否启用区块优化/局部变量优化
        boolean enableChunkOptimization = colorResolverWrapper.enableChunkOptimization();

        // 绘制生物群系图片
        ServerWorld world = context.getSource().getWorld();
        // 遍历区块
        for (chunkX = startChunkX; chunkX <= endChunkX; chunkX++) {
            for (chunkZ = startChunkZ; chunkZ <= endChunkZ; chunkZ++) {
                // 获取区块内的起始坐标和结束坐标，并遍历
                endX = Math.min(ChunkSectionPos.getBlockCoord(chunkX) | 0xF, endPos.x());
                endZ = Math.min(ChunkSectionPos.getBlockCoord(chunkZ) | 0xF, endPos.z());
                for (x = Math.max(ChunkSectionPos.getBlockCoord(chunkX), startPos.x()); x <= endX; x++) {
                    for (z = Math.max(ChunkSectionPos.getBlockCoord(chunkZ), startPos.z()); z <= endZ; z++) {
                        // 获取并设置颜色
                        image.setRGB(x - startPos.x(), z - startPos.z(), colorResolverWrapper.getColor(world, x, z));
                    }
                }

                // 区块处理计数
                chunkCount++;
                // 计算进度，发送间隔5秒
                currentTime = System.currentTimeMillis();
                if (currentTime - lastTime > 5000) {
                    lastTime = currentTime;
                    currentPercent = (int) Math.ceil((double) chunkCount / totalCountOfChunk * 100) - 1;
                    context.getSource().sendFeedback(Text.literal(currentPercent + "% chunk:" + chunkCount + "/" + totalCountOfChunk), false);
                }

                // 区块回收优化
                if (enableChunkOptimization) {
                    // 删除区块票据
                    ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
                    world.getChunkManager().threadedAnvilChunkStorage.getTicketManager().removeTicketWithLevel(ChunkTicketType.UNKNOWN, chunkPos, 33 + ChunkStatus.getDistanceFromFull(ChunkStatus.NOISE), chunkPos);
                    // 定期卸载区块
                    if (chunkCount % 1000 == 0) {
                        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.savingChunks"), false);
                        world.getChunkManager().save(true);
                        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.savingDone"), false);
                    }
                }
            }
        }

        // 保存
        try {
            context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.savingImage"), false);
            // 检查文件夹
            if (!directory.exists() && !directory.mkdirs())
                throw new IOException("Failed to create directory");
            // 保存图片
            ImageIO.write(image, "png", file);
        } catch (Exception e) {
            LOGGER.error("Failed to save biome snapshot image", e);
            // 保存失败
            context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.fail"), false);
            return 0;
        }

        // 成功
        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.success", file.getName()), false);
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
