package pers.liaohaolong.biomesnapshot.command.suggestion;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver;

import java.util.concurrent.CompletableFuture;

/**
 * <h3>生物群系颜色建议供应商</h3>
 *
 * <p>在 Biome 颜色解析器中搜索指定生物群系的默认颜色，如果存在，向用户提供默认值的建议。</p>
 */
public class BiomeColorSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

    private BiomeColorSuggestionProvider() {
    }

    public static BiomeColorSuggestionProvider of() {
        return new BiomeColorSuggestionProvider();
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
        String namespace = StringArgumentType.getString(context, "namespace");
        String path = StringArgumentType.getString(context, "path");
        // 拼接要设置的生物群系标识符
        Identifier identifier = new Identifier(namespace, path);
        // 搜索默认值
        int defaultColor = BiomeColorResolver.DEFAULT_BIOME_COLOR_MAP.getOrDefault(identifier, -1);
        // 如果有默认值
        if (defaultColor != -1) {
            // 获取当前输入
            String defaultColorString = String.valueOf(defaultColor);
            // 判断当前输入的是否是默认值的前缀
            if (defaultColorString.startsWith(builder.getRemaining()))
                // 添加建议
                builder.suggest(defaultColorString);
        }
        return builder.buildFuture();
    }

}
