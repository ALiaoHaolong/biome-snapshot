package pers.liaohaolong.biomesnapshot.color.resolver;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;

import java.awt.*;
import java.util.Objects;

/**
 * <h3>颜色解析器包装器</h3>
 *
 * <p>可动态配置颜色解析器{@link #colorResolver}，默认颜色。</p>
 */
public class ColorResolverWrapper implements ColorResolver {

    private ColorResolver colorResolver;

    private int defaultColor;

    public ColorResolverWrapper() {
        setColorResolver((world, x, z) -> -1);
        setDefaultColor(Color.BLACK);
    }

    @Override
    public void prepare(ServerCommandSource source) {
        if (this.colorResolver != null)
            this.colorResolver.prepare(source);
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
    public void finish(ServerCommandSource source) {
        if (this.colorResolver != null)
            this.colorResolver.finish(source);
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
