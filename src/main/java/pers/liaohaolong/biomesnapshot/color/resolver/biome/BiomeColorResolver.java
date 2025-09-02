package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * <h3>生物群系颜色解析器</h3>
 */
public class BiomeColorResolver extends AbstractBiomeColorResolver {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected int getBiomeColor(ServerWorld world, BlockPos pos) {
        Optional<RegistryKey<Biome>> biomeRegistryKey = world.getBiome(pos).getKey();
        if (biomeRegistryKey.isPresent()) {
            // 获取生物群系的名称
            String key = biomeRegistryKey.get().getValue().getPath();
            // 遍历枚举
            for (BiomeColor biome : BiomeColor.values()) {
                if (biome.name().toLowerCase().equals(key)) {
                    return biome.getColor();
                }
            }
            LOGGER.warn("无法解析的生物群系名称: {}", key);
            return -1;
        }
        LOGGER.warn("异常的生物群系注册状态");
        return -1;
    }

}
