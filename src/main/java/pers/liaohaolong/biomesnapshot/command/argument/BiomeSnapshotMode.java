package pers.liaohaolong.biomesnapshot.command.argument;

/**
 * 生物群系截图模式
 */
public enum BiomeSnapshotMode {

    /**
     * 默认模式
     *
     * @see pers.liaohaolong.biomesnapshot.biomecolor.resolver.DefaultBiomeColorResolver
     */
    DEFAULT,

    /**
     * 大陆海洋模式
     *
     * @see pers.liaohaolong.biomesnapshot.biomecolor.resolver.MainlandOceanBiomeColorResolver
     */
    MAINLAND_OCEAN,

    /**
     * 大陆河域海洋模式
     *
     * @see pers.liaohaolong.biomesnapshot.biomecolor.resolver.MainlandRiverOceanBiomeColorResolver
     */
    MAINLAND_RIVER_OCEAN;

}
