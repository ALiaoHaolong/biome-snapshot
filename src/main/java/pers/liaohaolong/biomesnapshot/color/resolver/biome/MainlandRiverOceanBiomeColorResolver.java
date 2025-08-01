package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

/**
 * <h3>大陆河流海洋生物群系颜色解析器</h3>
 */
public class MainlandRiverOceanBiomeColorResolver extends AbstractBiomeColorResolver {

    @Override
    protected int getBiomeColor(ServerWorld world, BlockPos pos) {
        // 获取生物群系的分类名称
        Biome.Category category = world.getBiome(pos).getCategory();
        return switch (category) {
            case OCEAN -> 0x000070;
            case RIVER -> 0x0000FF;
            case NETHER -> 0xBF3B3B;
            case THEEND -> 0x8080FF;
            default -> 0x8DB360;
        };
    }

}
