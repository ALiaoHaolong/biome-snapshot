package pers.liaohaolong.biomesnapshot.biomecolor;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.function.Consumer;

/**
 * 生物群系颜色包装器，可动态配置生物群系颜色解析器{@link #biomeColorResolver}，扩展了解析器解析失败时的处理{@link #notFountHandler}，并默认返回黑色
 */
public class BiomeColorWrapper implements BiomeColorResolver {

    private final Consumer<RegistryEntry<Biome>> notFountHandler;

    private BiomeColorResolver biomeColorResolver;

    public BiomeColorWrapper(Consumer<RegistryEntry<Biome>> notFountHandler) {
        this.notFountHandler = notFountHandler;
    }

    public BiomeColorResolver getBiomeColorFinder() {
        return biomeColorResolver;
    }

    public void setBiomeColorFinder(BiomeColorResolver biomeColorResolver) {
        this.biomeColorResolver = biomeColorResolver;
    }

    @Override
    public @NotNull Color getColor(@NotNull RegistryEntry<Biome> registryEntry) {
        if (biomeColorResolver == null) {
            throw new RuntimeException("biomeColorFinder is null");
        }
        Color color = biomeColorResolver.getColor(registryEntry);
        if (color != null) {
            return color;
        }
        // 未找到
        notFountHandler.accept(registryEntry);
        // 默认返回黑色
        return Color.BLACK;
    }

}
