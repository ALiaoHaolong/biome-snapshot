package pers.liaohaolong.biomesnapshot.color.resolver.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;

/**
 * <h3>真实的海岸线颜色解析器</h3>
 */
public class RealCoastlineColorResolver implements ColorResolver {

    @Override
    public int getColor(MinecraftServer world, int x, int z) {
        // 尝试加载NOISE阶段的区块。
        // 如果区块阶段比NOISE更丰富，则返回更丰富阶段的区块；
        // 如果区块阶段低于NOISE，则新生成一个NOISE阶段的区块。
        ChunkAccess chunk = world.findRespawnDimension().getChunk(
                SectionPos.blockToSectionCoord(x),
                SectionPos.blockToSectionCoord(z),
                ChunkStatus.NOISE,
                true
        );

        if (chunk == null)
            return -1;

        // 等价于 world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z) - 1;
        // 在 NOISE 阶段中，只有 WORLD_SURFACE_WG 高度图和 OCEAN_FLOOR_WG 存在
        int topY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x & 15, z & 15);
        // 表面方块坐标
        BlockPos blockPos = new BlockPos(x, topY, z);
        // 获取表面方块
        BlockState blockState = chunk.getBlockState(blockPos);
        // 返回方块颜色
        return blockState.getMapColor(world.findRespawnDimension(), blockPos).col;
    }

    @Override
    public boolean enableChunkOptimization() {
        return true;
    }

}
