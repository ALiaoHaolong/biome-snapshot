package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;

/**
 * <h3>大陆海洋生物群系颜色解析器</h3>
 *
 * <p>由于1.19-22w11a版本更新中，移除了生物群系的category字段，因此改用标签进行判断。</p>
 *
 * @see <a href="https://zh.minecraft.wiki/w/%E7%94%9F%E7%89%A9%E7%BE%A4%E7%B3%BB%E5%AE%9A%E4%B9%89%E6%A0%BC%E5%BC%8F?variant=zh-cn#%E5%8E%86%E5%8F%B2">生物群系定义格式</a>
 */
public class MainlandOceanBiomeColorResolver extends AbstractBiomeColorResolver {

    @Override
    protected int getBiomeColor(ServerWorld world, BlockPos pos) {
        // 获取生物群系
        RegistryEntry<Biome> biomeRegistryEntry = world.getBiome(pos);
        // 判断标签
        if (biomeRegistryEntry.isIn(BiomeTags.IS_OVERWORLD)) {
            if (biomeRegistryEntry.isIn(BiomeTags.IS_OCEAN))
                return 0x000070;
            return 0x8DB360;
        }
        if (biomeRegistryEntry.isIn(BiomeTags.IS_NETHER))
            return 0xBF3B3B;
        if (biomeRegistryEntry.isIn(BiomeTags.IS_END))
            return 0x8080FF;
        return -1;
    }

}
