package pers.liaohaolong.biomesnapshot.command.argument;

import pers.liaohaolong.biomesnapshot.color.resolver.block.RealCoastlineColorResolver;

/**
 * <h3>颜色解析器枚举类</h3>
 *
 * <p>与颜色解析器一一对应，命名方式为省略 ColorResolver 后缀的大蛇形命名。</p>
 */
public enum ColorResolverEnum {

    /**
     * 生物群系颜色解析器
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver
     */
    BIOME,

    /**
     * 大陆海洋生物群系颜色解析器
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver
     */
    MAINLAND_OCEAN_BIOME,

    /**
     * 大陆河流海洋生物群系颜色解析器
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver
     */
    MAINLAND_RIVER_OCEAN_BIOME,

    /**
     * 真实海岸线颜色解析器
     *
     * @see RealCoastlineColorResolver
     */
    REAL_COASTLINE,

}
