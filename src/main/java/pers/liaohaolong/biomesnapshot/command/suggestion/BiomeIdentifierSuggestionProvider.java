package pers.liaohaolong.biomesnapshot.command.suggestion;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolvers;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver;

import java.util.concurrent.CompletableFuture;

/**
 * <h3>Biome 标识符建议供应商</h3>
 */
public class BiomeIdentifierSuggestionProvider {

    protected final BiomeColorResolver colorResolver = (BiomeColorResolver) ColorResolvers.BIOME_COLOR_RESOLVER;

    private BiomeIdentifierSuggestionProvider() {
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull SuggestionProvider<ServerCommandSource> ofNamespace() {
        return new BiomeNamespaceSuggestionProvider();
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull SuggestionProvider<ServerCommandSource> ofPath() {
        return new BiomePathSuggestionProvider();
    }

    public static class BiomeNamespaceSuggestionProvider extends BiomeIdentifierSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

        protected BiomeNamespaceSuggestionProvider() {
        }

        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, @NotNull SuggestionsBuilder builder) {
            // 获取当前输入
            String input = builder.getRemaining();
            // 遍历
            for (Identifier identifier : colorResolver.getBiomeColorMap().keySet()) {
                // 判断当前输入的是否是某个命名空间的前缀
                if (identifier.getNamespace().startsWith(input))
                    // 添加建议
                    builder.suggest(identifier.getNamespace()); // 自动去重
            }
            return builder.buildFuture();
        }

    }

    public static class BiomePathSuggestionProvider extends BiomeIdentifierSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

        protected BiomePathSuggestionProvider() {
        }

        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, @NotNull SuggestionsBuilder builder) {
            String namespace = StringArgumentType.getString(context, "namespace");

            // 获取当前输入
            String input = builder.getRemaining();
            // 遍历
            for (Identifier identifier : colorResolver.getBiomeColorMap().keySet()) {
                // 先匹配命名空间，再判断当前输入的是否是某个路径的前缀
                if (identifier.getNamespace().equals(namespace) && identifier.getPath().startsWith(input))
                    // 添加建议
                    builder.suggest(identifier.getPath());
            }
            return builder.buildFuture();
        }

    }

}
