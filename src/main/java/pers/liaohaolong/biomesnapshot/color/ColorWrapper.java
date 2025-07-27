package pers.liaohaolong.biomesnapshot.color;

import net.minecraft.server.world.ServerWorld;

import java.awt.*;
import java.util.Objects;

/**
 * <h3>颜色包装器</h3>
 *
 * <p>可动态配置颜色解析器{@link #colorResolver}，默认颜色。</p>
 */
public class ColorWrapper implements ColorResolver {

    private ColorResolver colorResolver;

    private int defaultColor;

    public ColorWrapper() {
        setColorResolver((world, x, z) -> -1);
        setDefaultColor(Color.BLACK);
    }

    @Override
    public int getColor(ServerWorld world, int x, int z) {
        int color = colorResolver.getColor(world, x, z);
        // 解析成功
        if (color >= 0) {
            return color;
        }
        // 返回默认色
        return defaultColor;
    }

    @Override
    public boolean enableChunkOptimization() {
        return colorResolver.enableChunkOptimization();
    }

    ////////////////////////////// getter & setter ///////////////////////////////

    @SuppressWarnings("unused")
    public ColorResolver getColorResolver() {
        return colorResolver;
    }

    public void setColorResolver(ColorResolver colorResolver) {
        this.colorResolver = Objects.requireNonNull(colorResolver);
    }

    @SuppressWarnings("unused")
    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = Objects.requireNonNull(defaultColor).getRGB();
    }

}
