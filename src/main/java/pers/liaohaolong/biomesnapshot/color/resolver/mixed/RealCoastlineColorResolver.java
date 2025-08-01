package pers.liaohaolong.biomesnapshot.color.resolver.mixed;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import pers.liaohaolong.biomesnapshot.color.ColorResolver;

/**
 * <h3>真实的海岸线颜色解析器</h3>
 */
public class RealCoastlineColorResolver implements ColorResolver {

    @Override
    public int getColor(ServerWorld world, int x, int z) {
        // 尝试加载NOISE阶段的区块。
        // 如果区块阶段比NOISE更丰富，则返回更丰富阶段的区块；
        // 如果区块阶段低于NOISE，则新生成一个NOISE阶段的区块。
        Chunk chunk = world.getChunkManager().getChunk(
                ChunkSectionPos.getSectionCoord(x),
                ChunkSectionPos.getSectionCoord(z),
                ChunkStatus.CARVERS,
                true
        );

        if (chunk == null)
            return -1;

        // 等价于 world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z) - 1;
        // 在 NOISE 阶段中，只有 WORLD_SURFACE_WG 高度图和 OCEAN_FLOOR_WG 存在
        int topY = chunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, x & 15, z & 15);
        // 获取表面方块
        BlockState blockState = chunk.getBlockState(new BlockPos(x, topY, z));

        // 返回方块颜色
        return blockState.getMapColor(world, null).color;
    }

    @Override
    public boolean enableChunkOptimization() {
        return true;
    }

}
