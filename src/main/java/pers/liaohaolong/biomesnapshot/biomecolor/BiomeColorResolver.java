package pers.liaohaolong.biomesnapshot.biomecolor;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * 生物群系颜色解析器
 */
public interface BiomeColorResolver {

    /**
     * 解析生物群系颜色
     *
     * @param registryEntry 生物群系注册表项
     * @return 颜色
     */
    Color getColor(@NotNull RegistryEntry<Biome> registryEntry);

}
