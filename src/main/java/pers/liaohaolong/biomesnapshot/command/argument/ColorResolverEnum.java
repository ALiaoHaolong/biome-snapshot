package pers.liaohaolong.biomesnapshot.command.argument;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolvers;
import pers.liaohaolong.biomesnapshot.color.resolver.block.RealCoastlineColorResolver;

/**
 * <h3>颜色解析器枚举类</h3>
 *
 * <p>与颜色解析器一一对应，命名方式为省略 ColorResolver 后缀的大蛇形命名。</p>
 *
 * @see net.minecraft.world.level.levelgen.Heightmap.Types
 */
public enum ColorResolverEnum implements StringRepresentable {

    /**
     * 生物群系颜色解析器
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver
     */
    BIOME("biome", ColorResolvers.BIOME_COLOR_RESOLVER),

    /**
     * 大陆海洋生物群系颜色解析器
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver
     */
    MAINLAND_OCEAN_BIOME("mainland_ocean_biome", ColorResolvers.MAINLAND_OCEAN_BIOME_COLOR_RESOLVER),

    /**
     * 大陆河域海洋生物群系颜色解析器
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver
     */
    MAINLAND_RIVER_OCEAN_BIOME("mainland_river_ocean_biome", ColorResolvers.MAINLAND_RIVER_OCEAN_BIOME_COLOR_RESOLVER),

    /**
     * 真实海岸线颜色解析器
     *
     * @see RealCoastlineColorResolver
     */
    REAL_COASTLINE("real_coastline", ColorResolvers.REAL_COASTLINE_COLOR_RESOLVER),
    ;

    public static final Codec<ColorResolverEnum> CODEC = StringRepresentable.fromEnum(ColorResolverEnum::values);
    private final String serializationKey;
    private final ColorResolver colorResolver;

    ColorResolverEnum(final String serializationKey, final ColorResolver colorResolver) {
        this.serializationKey = serializationKey;
        this.colorResolver = colorResolver;
    }

    public ColorResolver getColorResolver() {
        return this.colorResolver;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.serializationKey;
    }

}
