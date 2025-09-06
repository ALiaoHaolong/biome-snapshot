package pers.liaohaolong.biomesnapshot.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import pers.liaohaolong.biomesnapshot.BiomeSnapshotUtils;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolvers;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver;

/**
 * <h3>/biome-snapshot-config 命令</h3>
 */
public class BiomeSnapshotConfigCommand {

    protected void sendFeedback(CommandContext<ServerCommandSource> context, String key, Object... args) {
        context.getSource().sendFeedback(Text.translatable(key, args), true);
    }

    /**
     * <h3>/biome-snapshot-config biome 命令</h3>
     */
    public static class BiomeCommand extends BiomeSnapshotConfigCommand {

        private final BiomeColorResolver colorResolver = (BiomeColorResolver) ColorResolvers.BIOME_COLOR_RESOLVER;

        public int set(CommandContext<ServerCommandSource> context) {
            String namespace = StringArgumentType.getString(context, "namespace");
            String path = StringArgumentType.getString(context, "path");
            int color = IntegerArgumentType.getInteger(context, "color");

            Identifier identifier = new Identifier(namespace, path);
            colorResolver.getBiomeColorMap().put(identifier, color);
            sendFeedback(context, "command.biome-snapshot.config.biome_color_resolver.set.success", identifier.toString(), String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int remove(CommandContext<ServerCommandSource> context) {
            String namespace = StringArgumentType.getString(context, "namespace");
            String path = StringArgumentType.getString(context, "path");

            Identifier identifier = new Identifier(namespace, path);
            colorResolver.getBiomeColorMap().remove(identifier);
            sendFeedback(context, "command.biome-snapshot.config.biome_color_resolver.remove.success", identifier.toString());
            return 1;
        }

        public int get(CommandContext<ServerCommandSource> context) {
            String namespace = StringArgumentType.getString(context, "namespace");
            String path = StringArgumentType.getString(context, "path");

            Identifier identifier = new Identifier(namespace, path);
            int color = colorResolver.getBiomeColorMap().get(identifier);
            sendFeedback(context, "command.biome-snapshot.config.biome_color_resolver.get", identifier.toString(), String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

    }

    /**
     * <h3>/biome-snapshot-config mainland_ocean_biome 命令</h3>
     */
    public static class MainlandOceanBiomeCommand extends BiomeSnapshotConfigCommand {

        private final MainlandOceanBiomeColorResolver colorResolver = (MainlandOceanBiomeColorResolver) ColorResolvers.MAINLAND_OCEAN_BIOME_COLOR_RESOLVER;

        public int setMainland(CommandContext<ServerCommandSource> context) {
            int color = IntegerArgumentType.getInteger(context, "color");

            colorResolver.setMainlandColor(color);
            sendFeedback(context, "command.biome-snapshot.config.mainland_ocean_biome_color_resolver.set.mainland", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int setOcean(CommandContext<ServerCommandSource> context) {
            int color = IntegerArgumentType.getInteger(context, "color");

            colorResolver.setOceanColor(color);
            sendFeedback(context, "command.biome-snapshot.config.mainland_ocean_biome_color_resolver.set.ocean", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int getMainland(CommandContext<ServerCommandSource> context) {
            int color = colorResolver.getMainlandColor();
            sendFeedback(context, "command.biome-snapshot.config.mainland_ocean_biome_color_resolver.get.mainland", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int getOcean(CommandContext<ServerCommandSource> context) {
            int color = colorResolver.getOceanColor();
            sendFeedback(context, "command.biome-snapshot.config.mainland_ocean_biome_color_resolver.get.ocean", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

    }

    /**
     * <h3>/biome-snapshot-config mainland_river_ocean_biome 命令</h3>
     */
    public static class MainlandRiverOceanBiomeCommand extends BiomeSnapshotConfigCommand {

        private final MainlandRiverOceanBiomeColorResolver colorResolver = (MainlandRiverOceanBiomeColorResolver) ColorResolvers.MAINLAND_RIVER_OCEAN_BIOME_COLOR_RESOLVER;

        public int setMainland(CommandContext<ServerCommandSource> context) {
            int color = IntegerArgumentType.getInteger(context, "color");

            colorResolver.setMainlandColor(color);
            sendFeedback(context, "command.biome-snapshot.config.mainland_river_ocean_biome_color_resolver.set.mainland", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int setRiver(CommandContext<ServerCommandSource> context) {
            int color = IntegerArgumentType.getInteger(context, "color");

            colorResolver.setRiverColor(color);
            sendFeedback(context, "command.biome-snapshot.config.mainland_river_ocean_biome_color_resolver.set.river", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int setOcean(CommandContext<ServerCommandSource> context) {
            int color = IntegerArgumentType.getInteger(context, "color");

            colorResolver.setOceanColor(color);
            sendFeedback(context, "command.biome-snapshot.config.mainland_river_ocean_biome_color_resolver.set.ocean", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int getMainland(CommandContext<ServerCommandSource> context) {
            int color = colorResolver.getMainlandColor();
            sendFeedback(context, "command.biome-snapshot.config.mainland_river_ocean_biome_color_resolver.get.mainland", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int getRiver(CommandContext<ServerCommandSource> context) {
            int color = colorResolver.getRiverColor();
            sendFeedback(context, "command.biome-snapshot.config.mainland_river_ocean_biome_color_resolver.get.river", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

        public int getOcean(CommandContext<ServerCommandSource> context) {
            int color = colorResolver.getOceanColor();
            sendFeedback(context, "command.biome-snapshot.config.mainland_river_ocean_biome_color_resolver.get.ocean", String.valueOf(color), BiomeSnapshotUtils.toHexString(color));
            return 1;
        }

    }

}
