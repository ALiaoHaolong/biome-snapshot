package pers.liaohaolong.biomesnapshot;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.ColumnPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import pers.liaohaolong.biomesnapshot.command.BiomeSnapshotCommand;
import pers.liaohaolong.biomesnapshot.command.argument.EnumArgumentType;
import pers.liaohaolong.biomesnapshot.command.argument.BiomeSnapshotMode;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class BiomeSnapshot implements ModInitializer {

    private static final Command<ServerCommandSource> COMMAND = new BiomeSnapshotCommand();

    @Override
    public void onInitialize() {
        // 创建命令
        LiteralArgumentBuilder<ServerCommandSource> command = literal("biome-snapshot")
                .requires(source ->  source.hasPermissionLevel(4))
                // 配色模式
                .then(argument("mode", EnumArgumentType.enumArgument(BiomeSnapshotMode.class))
                        // 起始坐标
                        .then(argument("pos1", ColumnPosArgumentType.columnPos())
                                // 结束坐标
                                .then(argument("pos2", ColumnPosArgumentType.columnPos())
                                        .executes(COMMAND)
                                )
                        )
                );

        // 注册命令
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(command));
    }

}
