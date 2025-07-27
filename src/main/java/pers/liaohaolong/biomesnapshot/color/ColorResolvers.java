package pers.liaohaolong.biomesnapshot.color;

import pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver;
import pers.liaohaolong.biomesnapshot.color.resolver.mixed.RealCoastlineColorResolver;

public final class ColorResolvers {

    public static final ColorResolver BIOME_COLOR_RESOLVER = new BiomeColorResolver();
    public static final ColorResolver MAINLAND_OCEAN_BIOME_COLOR_RESOLVER = new MainlandOceanBiomeColorResolver();
    public static final ColorResolver MAINLAND_RIVER_OCEAN_BIOME_COLOR_RESOLVER = new MainlandRiverOceanBiomeColorResolver();
    public static final ColorResolver REAL_COASTLINE_COLOR_RESOLVER = new RealCoastlineColorResolver();

}
