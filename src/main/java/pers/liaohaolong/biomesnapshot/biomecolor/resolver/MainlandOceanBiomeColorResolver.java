package pers.liaohaolong.biomesnapshot.biomecolor.resolver;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.liaohaolong.biomesnapshot.biomecolor.BiomeColorResolver;

import java.awt.*;

/**
 * 大陆海洋生物群系颜色解析器
 */
public class MainlandOceanBiomeColorResolver implements BiomeColorResolver {

    @Override
    public @Nullable Color getColor(@NotNull RegistryEntry<Biome> registryEntry) {
        // 获取生物群系的分类名称
        Biome.Category category = Biome.getCategory(registryEntry);
        return switch (category) {
            case OCEAN -> new Color(0x000070);
            case NETHER -> new Color(0xBF3B3B);
            case THEEND -> new Color(0x8080FF);
            default -> new Color(0x8DB360);
        };
    }

}
