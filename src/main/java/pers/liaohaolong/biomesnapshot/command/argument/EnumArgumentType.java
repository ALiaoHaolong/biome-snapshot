package pers.liaohaolong.biomesnapshot.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * 枚举参数类型，仿照原版的其他参数类型编写
 *
 * @param <E> 枚举类型
 */
public class EnumArgumentType<E extends Enum<E>> implements ArgumentType<E> {
    private final Class<E> enumClass;
    private final Collection<String> examples;

    public static final SimpleCommandExceptionType INVALID_ENUM_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("argument.enum.invalid"));

    private EnumArgumentType(Class<E> enumClass) {
        this.enumClass = enumClass;
        this.examples = Arrays.stream(enumClass.getEnumConstants())
                .map(e -> e.name().toLowerCase())
                .toList();
    }

    public static <E extends Enum<E>> EnumArgumentType<E> enumArgument(Class<E> enumClass) {
        return new EnumArgumentType<>(enumClass);
    }

    public static <E extends Enum<E>> E getEnum(CommandContext<?> context, String name, Class<E> enumClass) {
        return context.getArgument(name, enumClass);
    }

    @Override
    public E parse(StringReader reader) throws CommandSyntaxException {
        int start = reader.getCursor();
        String value = reader.readString();

        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            reader.setCursor(start);
            throw INVALID_ENUM_EXCEPTION.createWithContext(reader);
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String input = builder.getRemaining().toLowerCase();
        for (E enumConstant : enumClass.getEnumConstants()) {
            String name = enumConstant.name().toLowerCase();
            if (name.startsWith(input)) {
                builder.suggest(name);
            }
        }
        return builder.buildFuture();
    }

    @Override
    public Collection<String> getExamples() {
        return examples;
    }
}
