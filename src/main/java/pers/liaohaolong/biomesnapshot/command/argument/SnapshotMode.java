package pers.liaohaolong.biomesnapshot.command.argument;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;
import pers.liaohaolong.biomesnapshot.color.ColorResolver;
import pers.liaohaolong.biomesnapshot.color.ColorResolvers;
import pers.liaohaolong.biomesnapshot.color.resolver.mixed.RealCoastlineColorResolver;

/**
 * <h3>快照模式</h3>
 *
 * <p>与颜色解析器一一对应，命名方式为省略 ColorResolver 后缀的大蛇形命名。</p>
 *
 * @see net.minecraft.world.Heightmap.Type
 * @see net.minecraft.util.BlockMirror
 * @see net.minecraft.util.BlockRotation
 */
public enum SnapshotMode implements StringIdentifiable {

    /**
     * 默认生物群系模式
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.BiomeColorResolver
     */
    BIOME("BIOME", ColorResolvers.BIOME_COLOR_RESOLVER),

    /**
     * 大陆海洋生物群系模式
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandOceanBiomeColorResolver
     */
    MAINLAND_OCEAN_BIOME("MAINLAND_OCEAN_BIOME", ColorResolvers.MAINLAND_OCEAN_BIOME_COLOR_RESOLVER),

    /**
     * 大陆河域海洋生物群系模式
     *
     * @see pers.liaohaolong.biomesnapshot.color.resolver.biome.MainlandRiverOceanBiomeColorResolver
     */
    MAINLAND_RIVER_OCEAN_BIOME("MAINLAND_RIVER_OCEAN_BIOME", ColorResolvers.MAINLAND_RIVER_OCEAN_BIOME_COLOR_RESOLVER),

    /**
     * 真实海岸线模式
     *
     * @see RealCoastlineColorResolver
     */
    REAL_COASTLINE("REAL_COASTLINE", ColorResolvers.REAL_COASTLINE_COLOR_RESOLVER),
    ;

    public static final Codec<SnapshotMode> CODEC = StringIdentifiable.createCodec(SnapshotMode::values);
    private final String name;
    private final ColorResolver colorResolver;

    SnapshotMode(final String name, final ColorResolver colorResolver) {
        this.name = name;
        this.colorResolver = colorResolver;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return this.name;
    }

    public ColorResolver getColorResolver() {
        return this.colorResolver;
    }

    @Override
    public String asString() {
        return this.name;
    }

}
