package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

/**
 * <h3>大陆海洋生物群系颜色解析器</h3>
 *
 * <p>由于1.19-22w11a版本更新中，移除了生物群系的category字段，因此改用标签进行判断。</p>
 *
 * @see <a href="https://zh.minecraft.wiki/w/%E7%94%9F%E7%89%A9%E7%BE%A4%E7%B3%BB%E5%AE%9A%E4%B9%89%E6%A0%BC%E5%BC%8F?variant=zh-cn#%E5%8E%86%E5%8F%B2">生物群系定义格式</a>
 */
public class MainlandOceanBiomeColorResolver extends AbstractBiomeColorResolver {

    public static final int DEFAULT_MAINLAND_COLOR = 0x8DB360;
    public static final int DEFAULT_OCEAN_COLOR = 0x000070;

    private int mainlandColor = DEFAULT_MAINLAND_COLOR;
    private int oceanColor = DEFAULT_OCEAN_COLOR;

    @Override
    protected int getBiomeColor(MinecraftServer world, BlockPos pos) {
        // 获取生物群系
        Holder<Biome> biomeRegistryEntry = world.findRespawnDimension().getBiome(pos);
        // 判断标签
        if (biomeRegistryEntry.is(BiomeTags.IS_OVERWORLD)) {
            if (biomeRegistryEntry.is(BiomeTags.IS_OCEAN))
                return oceanColor;
            return mainlandColor;
        }
        return -1;
    }

    public int getMainlandColor() {
        return mainlandColor;
    }

    public void setMainlandColor(int mainlandColor) {
        this.mainlandColor = mainlandColor;
    }

    public int getOceanColor() {
        return oceanColor;
    }

    public void setOceanColor(int oceanColor) {
        this.oceanColor = oceanColor;
    }

}
