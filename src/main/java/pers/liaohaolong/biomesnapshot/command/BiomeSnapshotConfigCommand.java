package pers.liaohaolong.biomesnapshot.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolvers;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver;

public class BiomeSnapshotConfigCommand {

    public int set(CommandContext<ServerCommandSource> context) {
        // 读参
        String namespace = StringArgumentType.getString(context, "namespace");
        String path = StringArgumentType.getString(context, "path");
        int color = IntegerArgumentType.getInteger(context, "color");
        // 标识符
        Identifier identifier = new Identifier(namespace, path);
        // 业务
        BiomeColorResolver biomeColorResolver = (BiomeColorResolver) ColorResolvers.BIOME_COLOR_RESOLVER;
        biomeColorResolver.getBiomeColorMap().put(identifier, color);
        // 反馈
        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.config.biome_color_resolver.set.success", identifier.toString(), String.valueOf(color), Integer.toHexString(color).toUpperCase()), true);
        return 1;
    }

    public int remove(CommandContext<ServerCommandSource> context) {
        // 读参
        String namespace = StringArgumentType.getString(context, "namespace");
        String path = StringArgumentType.getString(context, "path");
        // 标识符
        Identifier identifier = new Identifier(namespace, path);
        // 业务
        BiomeColorResolver biomeColorResolver = (BiomeColorResolver) ColorResolvers.BIOME_COLOR_RESOLVER;
        biomeColorResolver.getBiomeColorMap().remove(identifier);
        // 反馈
        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.config.biome_color_resolver.remove.success", identifier.toString()), true);
        return 1;
    }

    public int get(CommandContext<ServerCommandSource> context) {
        // 读参
        String namespace = StringArgumentType.getString(context, "namespace");
        String path = StringArgumentType.getString(context, "path");
        // 标识符
        Identifier identifier = new Identifier(namespace, path);
        // 业务
        BiomeColorResolver biomeColorResolver = (BiomeColorResolver) ColorResolvers.BIOME_COLOR_RESOLVER;
        int color = biomeColorResolver.getBiomeColorMap().get(identifier);
        // 反馈
        context.getSource().sendFeedback(Text.translatable("command.biome-snapshot.config.biome_color_resolver.get", identifier.toString(), String.valueOf(color), Integer.toHexString(color).toUpperCase()), true);
        return 1;
    }

}
