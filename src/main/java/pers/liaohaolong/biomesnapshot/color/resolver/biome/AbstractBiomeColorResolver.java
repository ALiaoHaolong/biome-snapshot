package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;

/**
 * <h3>抽象的生物群系颜色解析器</h3>
 *
 * <p>以 WORLD_SURFACE_WG 为高度图，封装坐标转换。</p>
 */
public abstract class AbstractBiomeColorResolver implements ColorResolver {

    protected abstract int getBiomeColor(ServerWorld world, BlockPos pos);

    @Override
    public int getColor(ServerWorld world, int x, int z) {
        // 1.19 中，如果区块未生成，则无法获取其 topY，因此需要尝试加载 NOISE 阶段区块。
        // 注意不能使用 BIOMES 阶段，虽然在该阶段后，生物群系已经生成，
        // 但由于 NOISE 阶段的高度还未叠加，BIOMES 阶段即使存在高度图 WORLD_SURFACE_WG（通过 DEBUG 可知此时仅有这个高度图），其中的值也全为 0。
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
