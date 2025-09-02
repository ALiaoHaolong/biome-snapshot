package pers.liaohaolong.biomesnapshot.color.resolver;

import pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.block.RealCoastlineColorResolver;

/**
 * <h3>颜色解析器常量类</h3>
 *
 * <p>颜色解析器常量类，存放可用的颜色解析器单例</p>
 */
public final class ColorResolvers {

    public static final ColorResolver BIOME_COLOR_RESOLVER = new BiomeColorResolver();
    public static final ColorResolver MAINLAND_OCEAN_BIOME_COLOR_RESOLVER = new MainlandOceanBiomeColorResolver();
    public static final ColorResolver MAINLAND_RIVER_OCEAN_BIOME_COLOR_RESOLVER = new MainlandRiverOceanBiomeColorResolver();
    public static final ColorResolver REAL_COASTLINE_COLOR_RESOLVER = new RealCoastlineColorResolver();

}
