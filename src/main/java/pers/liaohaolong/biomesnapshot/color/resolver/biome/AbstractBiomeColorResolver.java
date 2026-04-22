package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;

/**
 * <h3>抽象的生物群系颜色解析器</h3>
 *
 * <p>以 WORLD_SURFACE_WG 为高度图，封装坐标转换。</p>
 */
public abstract class AbstractBiomeColorResolver implements ColorResolver {

    protected abstract int getBiomeColor(MinecraftServer world, BlockPos pos);

    @Override
    public int getColor(MinecraftServer world, int x, int z) {
        // 1.21.4 中，如果区块未生成，则无法获取其 topY，因此需要尝试加载 NOISE 阶段区块。
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

        return getBiomeColor(world, new BlockPos(x, topY, z));
    }

    @Override
    public boolean enableChunkOptimization() {
        return true;
    }

}
