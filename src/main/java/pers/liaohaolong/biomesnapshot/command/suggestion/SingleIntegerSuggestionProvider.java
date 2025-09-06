package pers.liaohaolong.biomesnapshot.command.suggestion;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

/**
 * <h3>单整数建议供应商</h3>
 *
 * <p>在合适的时候，向用户建议指定的值</p>
 */
public class SingleIntegerSuggestionProvider implements SuggestionProvider<ServerCommandSource> {

    private final String suggestionValue;

    private SingleIntegerSuggestionProvider(int suggestionValue) {
        this.suggestionValue = String.valueOf(suggestionValue);
    }

    public static SingleIntegerSuggestionProvider of(int suggestionValue) {
        return new SingleIntegerSuggestionProvider(suggestionValue);
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
        // 获取当前输入
        String input = builder.getRemaining();
        // 判断当前输入的是否是枚举值的前缀
        if (suggestionValue.startsWith(input))
            // 添加建议
            builder.suggest(suggestionValue);

        return builder.buildFuture();
    }

}
