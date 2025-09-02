package pers.liaohaolong.biomesnapshot;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.ColumnPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import pers.liaohaolong.biomesnapshot.command.BiomeSnapshotCommand;
import pers.liaohaolong.biomesnapshot.command.argument.ColorResolverEnum;
import pers.liaohaolong.biomesnapshot.command.argument.EnumArgumentType;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * <h1>Biome Snapshot</h1>
 *
 * <p>这是一个基于Fabric的Minecraft游戏模组，提供了一个命令来将指定坐标范围的生物群系状况导出为平面图。</p>
 *
 * <p>相关链接：
 *     <a href="https://gitee.com/AnNight/biome-snapshot">主页</a>
 *     <a href="https://gitee.com/AnNight/biome-snapshot/issues">问题反馈</a>
 *     <a href="https://gitee.com/AnNight/biome-snapshot/releases">下载链接</a>
 * </p>
 *
 * @author 廖浩龙
 */
public class BiomeSnapshot implements ModInitializer {

    public static final String MOD_ID = "biome-snapshot";

    private static final Command<ServerCommandSource> COMMAND = new BiomeSnapshotCommand();

    @Override
    public void onInitialize() {
        // 注册表：颜色解析器
        BiomeSnapshotRegistry.registerColorResolvers();

        // 创建命令
        LiteralArgumentBuilder<ServerCommandSource> command = literal(MOD_ID)
                .requires(source -> source.hasPermissionLevel(4))
                // 颜色解析器
                .then(argument("colorResolver", EnumArgumentType.enumArgument(ColorResolverEnum.class))
                        // 起始坐标
                        .then(argument("from", ColumnPosArgumentType.columnPos())
                                // 结束坐标
                                .then(argument("to", ColumnPosArgumentType.columnPos())
                                        .executes(COMMAND)
                                )
                        )
                );

        // 注册命令
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(command));
    }

}
