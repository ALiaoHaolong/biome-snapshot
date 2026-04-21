package pers.liaohaolong.biomesnapshot;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.ColumnPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.command.BiomeSnapshotCommand;
import pers.liaohaolong.biomesnapshot.command.BiomeSnapshotConfigCommand.BiomeCommand;
import pers.liaohaolong.biomesnapshot.command.BiomeSnapshotConfigCommand.MainlandOceanBiomeCommand;
import pers.liaohaolong.biomesnapshot.command.BiomeSnapshotConfigCommand.MainlandRiverOceanBiomeCommand;
import pers.liaohaolong.biomesnapshot.command.argument.ColorResolverEnum;
import pers.liaohaolong.biomesnapshot.command.suggestion.BiomeColorSuggestionProvider;
import pers.liaohaolong.biomesnapshot.command.suggestion.BiomeIdentifierSuggestionProvider;
import pers.liaohaolong.biomesnapshot.command.suggestion.SingleIntegerSuggestionProvider;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * <h1>Biome Snapshot</h1>
 *
 * <p>这是一个基于Fabric的Minecraft游戏模组，提供了一个命令来将指定坐标范围的生物群系状况导出为平面图。</p>
 *
 * @author 廖浩龙
 */
public class BiomeSnapshot implements ModInitializer {

    public static final String MOD_ID = "biome-snapshot";

    private static final BiomeCommand BIOME_COMMAND = new BiomeCommand();
    private static final MainlandOceanBiomeCommand MAINLAND_OCEAN_BIOME_COMMAND = new MainlandOceanBiomeCommand();
    private static final MainlandRiverOceanBiomeCommand MAINLAND_RIVER_OCEAN_BIOME_COMMAND = new MainlandRiverOceanBiomeCommand();

    @Override
    public void onInitialize() {
        // 创建命令
        LiteralArgumentBuilder<ServerCommandSource> command = literal(MOD_ID)
                .requires(source -> source.hasPermissionLevel(4))
                .then(colorResolver(ColorResolverEnum.BIOME))
                .then(colorResolver(ColorResolverEnum.MAINLAND_OCEAN_BIOME))
                .then(colorResolver(ColorResolverEnum.MAINLAND_RIVER_OCEAN_BIOME))
                .then(colorResolver(ColorResolverEnum.REAL_COASTLINE));
        LiteralArgumentBuilder<ServerCommandSource> configCommand = literal(MOD_ID + "-config")
                .requires(source -> source.hasPermissionLevel(4))
                .then(literal(ColorResolverEnum.BIOME.asString())
                        .then(literal("set")
                                .then(argument("namespace", StringArgumentType.word())
                                        .suggests(BiomeIdentifierSuggestionProvider.ofNamespace())
                                        .then(argument("path", StringArgumentType.word())
                                                .suggests(BiomeIdentifierSuggestionProvider.ofPath())
                                                .then(argument("color", IntegerArgumentType.integer(0, 16777215)) // [0, 0xFFFFFF]
                                                        .suggests(BiomeColorSuggestionProvider.of())
                                                        .executes(BIOME_COMMAND::set)
                                                )
                                        )
                                )
                        )
                        .then(literal("remove")
                                .then(argument("namespace", StringArgumentType.word())
                                        .suggests(BiomeIdentifierSuggestionProvider.ofNamespace())
                                        .then(argument("path", StringArgumentType.word())
                                                .suggests(BiomeIdentifierSuggestionProvider.ofPath())
                                                .executes(BIOME_COMMAND::remove)
                                        )
                                )
                        )
                        .then(literal("get")
                                .then(argument("namespace", StringArgumentType.word())
                                        .suggests(BiomeIdentifierSuggestionProvider.ofNamespace())
                                        .then(argument("path", StringArgumentType.word())
                                                .suggests(BiomeIdentifierSuggestionProvider.ofPath())
                                                .executes(BIOME_COMMAND::get)
                                        )
                                )
                        )
                )
                .then(literal(ColorResolverEnum.MAINLAND_OCEAN_BIOME.asString())
                        .then(literal("set")
                                .then(literal("mainland")
                                        .then(argument("color", IntegerArgumentType.integer(0, 16777215))
                                                .suggests(SingleIntegerSuggestionProvider.of(MainlandOceanBiomeColorResolver.DEFAULT_MAINLAND_COLOR))
                                                .executes(MAINLAND_OCEAN_BIOME_COMMAND::setMainland)
                                        )
                                )
                                .then(literal("ocean")
                                        .then(argument("color", IntegerArgumentType.integer(0, 16777215))
                                                .suggests(SingleIntegerSuggestionProvider.of(MainlandOceanBiomeColorResolver.DEFAULT_OCEAN_COLOR))
                                                .executes(MAINLAND_OCEAN_BIOME_COMMAND::setOcean)
                                        )
                                )
                        )
                        .then(literal("get")
                                .then(literal("mainland")
                                        .executes(MAINLAND_OCEAN_BIOME_COMMAND::getMainland)
                                )
                                .then(literal("ocean")
                                        .executes(MAINLAND_OCEAN_BIOME_COMMAND::getOcean)
                                )
                        )
                )
                .then(literal(ColorResolverEnum.MAINLAND_RIVER_OCEAN_BIOME.asString())
                        .then(literal("set")
                                .then(literal("mainland")
                                        .then(argument("color", IntegerArgumentType.integer(0, 16777215))
                                                .suggests(SingleIntegerSuggestionProvider.of(MainlandRiverOceanBiomeColorResolver.DEFAULT_MAINLAND_COLOR))
                                                .executes(MAINLAND_RIVER_OCEAN_BIOME_COMMAND::setMainland)
                                        )
                                )
                                .then(literal("river")
                                        .then(argument("color", IntegerArgumentType.integer(0, 16777215))
                                                .suggests(SingleIntegerSuggestionProvider.of(MainlandRiverOceanBiomeColorResolver.DEFAULT_RIVER_COLOR))
                                                .executes(MAINLAND_RIVER_OCEAN_BIOME_COMMAND::setRiver)
                                        )
                                )
                                .then(literal("ocean")
                                        .then(argument("color", IntegerArgumentType.integer(0, 16777215))
                                                .suggests(SingleIntegerSuggestionProvider.of(MainlandRiverOceanBiomeColorResolver.DEFAULT_OCEAN_COLOR))
                                                .executes(MAINLAND_RIVER_OCEAN_BIOME_COMMAND::setOcean)
                                        )
                                )
                        )
                        .then(literal("get")
                                .then(literal("mainland")
                                        .executes(MAINLAND_RIVER_OCEAN_BIOME_COMMAND::getMainland)
                                )
                                .then(literal("river")
                                        .executes(MAINLAND_RIVER_OCEAN_BIOME_COMMAND::getRiver)
                                )
                                .then(literal("ocean")
                                        .executes(MAINLAND_RIVER_OCEAN_BIOME_COMMAND::getOcean)
                                )
                        )
                );

        // 注册命令
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(command);
            dispatcher.register(configCommand);
        });
    }

    private static LiteralArgumentBuilder<ServerCommandSource> colorResolver(final ColorResolverEnum colorResolver) {
        return literal(colorResolver.asString())
                .then(argument("from", ColumnPosArgumentType.columnPos())
                        .then(argument("to", ColumnPosArgumentType.columnPos())
                                .executes(new BiomeSnapshotCommand() {
                                    @Override
                                    public int run(CommandContext<ServerCommandSource> context) {
                                        return this.run(context, colorResolver);
                                    }
                                })
                        )
                );
    }

}
