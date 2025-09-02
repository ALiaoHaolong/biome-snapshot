package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;

/**
 * <h3>抽象的生物群系颜色解析器</h3>
 *
 * <p>以 WORLD_SURFACE 为高度图，封装坐标转换。</p>
 */
public abstract class AbstractBiomeColorResolver implements ColorResolver {

    protected abstract int getBiomeColor(ServerWorld world, BlockPos pos);

    @Override
    public int getColor(ServerWorld world, int x, int z) {
        BlockPos pos = new BlockPos(x, world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z), z);
        return getBiomeColor(world, pos);
    }

}
