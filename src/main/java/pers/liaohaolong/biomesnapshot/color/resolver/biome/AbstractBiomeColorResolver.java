package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import pers.liaohaolong.biomesnapshot.color.ColorResolver;

/**
 * <h3>抽象的生物群系颜色解析器</h3>
 *
 * <p>以 WORLD_SURFACE_WG 为高度图，封装坐标转换。</p>
 */
public abstract class AbstractBiomeColorResolver implements ColorResolver {

    protected abstract int getBiomeColor(ServerWorld world, BlockPos pos);

    @Override
    public int getColor(ServerWorld world, int x, int z) {
        // 1.21.4 中，如果区块未生成，则无法获取其 topY，因此需要尝试加载 NOISE 阶段区块。
        Chunk chunk = world.getChunkManager().getChunk(
                ChunkSectionPos.getSectionCoord(x),
                ChunkSectionPos.getSectionCoord(z),
                ChunkStatus.NOISE,
                true
        );

        if (chunk == null)
            return -1;

        // 等价于 world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z) - 1;
        // 在 NOISE 阶段中，只有 WORLD_SURFACE_WG 高度图和 OCEAN_FLOOR_WG 存在
        int topY = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, x & 15, z & 15);

        return getBiomeColor(world, new BlockPos(x, topY, z));
    }

    @Override
    public boolean enableChunkOptimization() {
        return true;
    }

}
