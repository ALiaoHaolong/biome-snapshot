package pers.liaohaolong.biomesnapshot.command.argument;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EnumArgumentType;

/**
 * <h3>颜色解析器参数类型</h3>
 *
 * @see net.minecraft.command.argument.HeightmapArgumentType
 * @see net.minecraft.command.argument.BlockMirrorArgumentType
 * @see net.minecraft.command.argument.BlockRotationArgumentType
 */
public class ColorResolverArgumentType extends EnumArgumentType<ColorResolverEnum> {

    protected ColorResolverArgumentType() {
        super(ColorResolverEnum.CODEC, ColorResolverEnum::values);
    }

    public static ColorResolverArgumentType colorResolver() {
        return new ColorResolverArgumentType();
    }

    public static ColorResolverEnum getColorResolverEnum(CommandContext<?> context, String id) {
        return context.getArgument(id, ColorResolverEnum.class);
    }

}
