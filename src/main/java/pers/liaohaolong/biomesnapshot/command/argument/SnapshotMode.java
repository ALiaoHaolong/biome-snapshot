package pers.liaohaolong.biomesnapshot.command.argument;

import pers.liaohaolong.biomesnapshot.color.resolver.mixed.RealCoastlineColorResolver;

/**
 * <h3>快照模式</h3>
 *
 * <p>与颜色解析器一一对应，命名方式为省略 ColorResolver 后缀的大蛇形命名。</p>
 */
public enum SnapshotMode {

    /**
     * 默认生物群系模式
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver
     */
    BIOME,

    /**
     * 大陆海洋生物群系模式
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver
     */
    MAINLAND_OCEAN_BIOME,

    /**
     * 大陆河域海洋生物群系模式
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver
     */
    MAINLAND_RIVER_OCEAN_BIOME,

    /**
     * 真实海岸线模式
     *
     * @see RealCoastlineColorResolver
     */
    REAL_COASTLINE,

}
