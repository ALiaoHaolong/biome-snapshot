package pers.liaohaolong.biomesnapshot.command.argument;

import net.minecraft.util.StringIdentifiable;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolvers;
import pers.liaohaolong.biomesnapshot.color.resolver.block.RealCoastlineColorResolver;

/**
 * <h3>颜色解析器枚举类</h3>
 *
 * <p>与颜色解析器一一对应，命名方式为省略 ColorResolver 后缀的大蛇形命名。</p>
 *
 * @see net.minecraft.world.Heightmap.Type
 * @see net.minecraft.util.BlockMirror
 * @see net.minecraft.util.BlockRotation
 */
public enum ColorResolverEnum implements StringIdentifiable {

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
     * 大陆河流海洋生物群系颜色解析器
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

    public static final com.mojang.serialization.Codec<ColorResolverEnum> CODEC = StringIdentifiable.createCodec(ColorResolverEnum::values);
    private final String id;
    private final ColorResolver colorResolver;

    ColorResolverEnum(final String id, final ColorResolver colorResolver) {
        this.id = id;
        this.colorResolver = colorResolver;
    }

    public ColorResolver getColorResolver() {
        return this.colorResolver;
    }

    @Override
    public String asString() {
        return this.id;
    }

}
