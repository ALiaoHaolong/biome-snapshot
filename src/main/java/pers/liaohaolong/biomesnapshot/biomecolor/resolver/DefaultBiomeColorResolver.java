package pers.liaohaolong.biomesnapshot.biomecolor.resolver;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.liaohaolong.biomesnapshot.biomecolor.BiomeColorResolver;

import java.awt.*;

/**
 * 默认生物群系颜色解析器
 */
public class DefaultBiomeColorResolver implements BiomeColorResolver {

    public @Nullable Color getColor(@NotNull RegistryEntry<Biome> registryEntry) {
        // 获取生物群系的名称
        String key = registryEntry.getKey().get().getValue().getPath();
        // 遍历枚举
        for (DefaultBiomeColor biome : DefaultBiomeColor.values()) {
            if (biome.name().toLowerCase().equals(key)) {
                return biome.getColor();
            }
        }
        return null;
    }

}
