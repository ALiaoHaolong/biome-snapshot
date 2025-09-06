package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import static net.minecraft.world.biome.Biome.Category.OCEAN;

/**
 * <h3>大陆海洋生物群系颜色解析器</h3>
 */
public class MainlandOceanBiomeColorResolver extends AbstractBiomeColorResolver {

    public static final int DEFAULT_MAINLAND_COLOR = 0x8DB360;
    public static final int DEFAULT_OCEAN_COLOR = 0x000070;

    private int mainlandColor = DEFAULT_MAINLAND_COLOR;
    private int oceanColor = DEFAULT_OCEAN_COLOR;

    @Override
    protected int getBiomeColor(ServerWorld world, BlockPos pos) {
        // 获取生物群系的分类名称
        Biome.Category category = Biome.getCategory(world.getBiome(pos));
        // 返回分类颜色
        return category == OCEAN ? oceanColor : mainlandColor;
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
