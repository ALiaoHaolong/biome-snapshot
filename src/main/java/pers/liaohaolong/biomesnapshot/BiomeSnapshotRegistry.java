package pers.liaohaolong.biomesnapshot;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import pers.liaohaolong.biomesnapshot.color.ColorResolver;

import static pers.liaohaolong.biomesnapshot.BiomeSnapshot.MOD_ID;
import static pers.liaohaolong.biomesnapshot.color.ColorResolvers.*;

/**
 * <h3>注册表</h3>
 */
public class BiomeSnapshotRegistry {

    /**
     * 自定义注册表：颜色解析器
     */
    public static final Registry<ColorResolver> COLOR_RESOLVER = FabricRegistryBuilder
            .createSimple(ColorResolver.class, new Identifier(MOD_ID, "color_resolver"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    /**
     * 注册颜色解析器的封装方法
     *
     * @param path 颜色解析器的路径
     * @param colorResolver 颜色解析器
     * @param <T> 颜色解析器的类型
     */
    private static <T extends ColorResolver> void colorResolverRegistry(String path, T colorResolver) {
        Registry.register(COLOR_RESOLVER, new Identifier(MOD_ID, path), colorResolver);
    }

    /**
     * 注册颜色解析器
     */
    public static void registerColorResolvers() {
        // 路径命名方式为：省略 color_resolver 后缀的小蛇形命名
        colorResolverRegistry("biome", BIOME_COLOR_RESOLVER);
        colorResolverRegistry("mainland_ocean_biome", MAINLAND_OCEAN_BIOME_COLOR_RESOLVER);
        colorResolverRegistry("mainland_river_ocean_biome", MAINLAND_RIVER_OCEAN_BIOME_COLOR_RESOLVER);
        colorResolverRegistry("real_coastline", REAL_COASTLINE_COLOR_RESOLVER);
    }

}
