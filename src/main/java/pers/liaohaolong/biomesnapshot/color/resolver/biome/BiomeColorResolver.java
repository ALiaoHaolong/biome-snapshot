package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * <h3>生物群系颜色解析器</h3>
 */
public class BiomeColorResolver extends AbstractBiomeColorResolver {

    private static final Logger LOGGER = LogManager.getLogger();

    private final HashMap<Identifier, Integer> biomeColorMap = new LinkedHashMap<>();

    public BiomeColorResolver() {
        // 以下生物群系颜色来自 https://github.com/toolbox4minecraft/amidst/wiki/Biome-Color-Table
        biomeColorMap.put(Identifier.ofVanilla("ocean"), 0x000070);
        biomeColorMap.put(Identifier.ofVanilla("plains"), 0x8DB360);
        biomeColorMap.put(Identifier.ofVanilla("desert"), 0xFA9418);
        biomeColorMap.put(Identifier.ofVanilla("mountains"), 0x606060);
        biomeColorMap.put(Identifier.ofVanilla("forest"), 0x056621);
        biomeColorMap.put(Identifier.ofVanilla("taiga"), 0x0B6659);
        biomeColorMap.put(Identifier.ofVanilla("swamp"), 0x07F9B2);
        biomeColorMap.put(Identifier.ofVanilla("river"), 0x0000FF);
        biomeColorMap.put(Identifier.ofVanilla("nether_wastes"), 0xBF3B3B);
        biomeColorMap.put(Identifier.ofVanilla("the_end"), 0x8080FF);
        biomeColorMap.put(Identifier.ofVanilla("frozen_ocean"), 0x7070D6);
        biomeColorMap.put(Identifier.ofVanilla("frozen_river"), 0xA0A0FF);
        biomeColorMap.put(Identifier.ofVanilla("snowy_tundra"), 0xFFFFFF);
        biomeColorMap.put(Identifier.ofVanilla("snowy_mountains"), 0xA0A0A0);
        biomeColorMap.put(Identifier.ofVanilla("mushroom_fields"), 0xFF00FF);
        biomeColorMap.put(Identifier.ofVanilla("mushroom_field_shore"), 0xA000FF);
        biomeColorMap.put(Identifier.ofVanilla("beach"), 0xFADE55);
        biomeColorMap.put(Identifier.ofVanilla("desert_hills"), 0xD25F12);
        biomeColorMap.put(Identifier.ofVanilla("wooded_hills"), 0x22551C);
        biomeColorMap.put(Identifier.ofVanilla("taiga_hills"), 0x163933);
        biomeColorMap.put(Identifier.ofVanilla("mountain_edge"), 0x72789A);
        biomeColorMap.put(Identifier.ofVanilla("jungle"), 0x537B09);
        biomeColorMap.put(Identifier.ofVanilla("jungle_hills"), 0x2C4205);
        biomeColorMap.put(Identifier.ofVanilla("jungle_edge"), 0x628B17);
        biomeColorMap.put(Identifier.ofVanilla("deep_ocean"), 0x000030);
        biomeColorMap.put(Identifier.ofVanilla("stone_shore"), 0xA2A284);
        biomeColorMap.put(Identifier.ofVanilla("snowy_beach"), 0xFAF0C0);
        biomeColorMap.put(Identifier.ofVanilla("birch_forest"), 0x307444);
        biomeColorMap.put(Identifier.ofVanilla("birch_forest_hills"), 0x1F5F32);
        biomeColorMap.put(Identifier.ofVanilla("dark_forest"), 0x40511A);
        biomeColorMap.put(Identifier.ofVanilla("snowy_taiga"), 0x31554A);
        biomeColorMap.put(Identifier.ofVanilla("snowy_taiga_hills"), 0x243F36);
        biomeColorMap.put(Identifier.ofVanilla("giant_tree_taiga"), 0x596651);
        biomeColorMap.put(Identifier.ofVanilla("giant_tree_taiga_hills"), 0x454F3E);
        biomeColorMap.put(Identifier.ofVanilla("wooded_mountains"), 0x507050);
        biomeColorMap.put(Identifier.ofVanilla("savanna"), 0xBDB25F);
        biomeColorMap.put(Identifier.ofVanilla("savanna_plateau"), 0xA79D64);
        biomeColorMap.put(Identifier.ofVanilla("badlands"), 0xD94515);
        biomeColorMap.put(Identifier.ofVanilla("wooded_badlands_plateau"), 0xB09765);
        biomeColorMap.put(Identifier.ofVanilla("badlands_plateau"), 0xCA8C65);
        biomeColorMap.put(Identifier.ofVanilla("small_end_islands"), 0x8080FF);
        biomeColorMap.put(Identifier.ofVanilla("end_midlands"), 0x8080FF);
        biomeColorMap.put(Identifier.ofVanilla("end_highlands"), 0x8080FF);
        biomeColorMap.put(Identifier.ofVanilla("end_barrens"), 0x8080FF);
        biomeColorMap.put(Identifier.ofVanilla("warm_ocean"), 0x0000AC);
        biomeColorMap.put(Identifier.ofVanilla("lukewarm_ocean"), 0x000090);
        biomeColorMap.put(Identifier.ofVanilla("cold_ocean"), 0x202070);
        biomeColorMap.put(Identifier.ofVanilla("deep_warm_ocean"), 0x000050);
        biomeColorMap.put(Identifier.ofVanilla("deep_lukewarm_ocean"), 0x000040);
        biomeColorMap.put(Identifier.ofVanilla("deep_cold_ocean"), 0x202038);
        biomeColorMap.put(Identifier.ofVanilla("deep_frozen_ocean"), 0x404090);
        biomeColorMap.put(Identifier.ofVanilla("the_void"), 0x000000);
        biomeColorMap.put(Identifier.ofVanilla("sunflower_plains"), 0xB5DB88);
        biomeColorMap.put(Identifier.ofVanilla("desert_lakes"), 0xFFBC40);
        biomeColorMap.put(Identifier.ofVanilla("gravelly_mountains"), 0x888888);
        biomeColorMap.put(Identifier.ofVanilla("flower_forest"), 0x2D8E49);
        biomeColorMap.put(Identifier.ofVanilla("taiga_mountains"), 0x338E81);
        biomeColorMap.put(Identifier.ofVanilla("swamp_hills"), 0x2FFFDA);
        biomeColorMap.put(Identifier.ofVanilla("ice_spikes"), 0xB4DCDC);
        biomeColorMap.put(Identifier.ofVanilla("modified_jungle"), 0x7BA331);
        biomeColorMap.put(Identifier.ofVanilla("modified_jungle_edge"), 0x8AB33F);
        biomeColorMap.put(Identifier.ofVanilla("tall_birch_forest"), 0x589C6C);
        biomeColorMap.put(Identifier.ofVanilla("tall_birch_hills"), 0x47875A);
        biomeColorMap.put(Identifier.ofVanilla("dark_forest_hills"), 0x687942);
        biomeColorMap.put(Identifier.ofVanilla("snowy_taiga_mountains"), 0x597D72);
        biomeColorMap.put(Identifier.ofVanilla("giant_spruce_taiga"), 0x818E79);
        biomeColorMap.put(Identifier.ofVanilla("giant_spruce_taiga_hills"), 0x6D7766);
        biomeColorMap.put(Identifier.ofVanilla("gravelly_mountains_plus"), 0x789878);
        biomeColorMap.put(Identifier.ofVanilla("shattered_savanna"), 0xE5DA87);
        biomeColorMap.put(Identifier.ofVanilla("shattered_savanna_plateau"), 0xCFC58C);
        biomeColorMap.put(Identifier.ofVanilla("eroded_badlands"), 0xFF6D3D);
        biomeColorMap.put(Identifier.ofVanilla("modified_wooded_badlands_plateau"), 0xD8BF8D);
        biomeColorMap.put(Identifier.ofVanilla("modified_badlands_plateau"), 0xF2B48D);
        biomeColorMap.put(Identifier.ofVanilla("bamboo_jungle"), 0x768E14);
        biomeColorMap.put(Identifier.ofVanilla("bamboo_jungle_hills"), 0x3B470A);
        biomeColorMap.put(Identifier.ofVanilla("soul_sand_valley"), 0x5E3830);
        biomeColorMap.put(Identifier.ofVanilla("crimson_forest"), 0xDD0808);
        biomeColorMap.put(Identifier.ofVanilla("warped_forest"), 0x49907B);
        biomeColorMap.put(Identifier.ofVanilla("basalt_deltas"), 0x403636);
        // 以下生物群系颜色来自 chunkbase
        biomeColorMap.put(Identifier.ofVanilla("grove"), 0xDFECE5);
        biomeColorMap.put(Identifier.ofVanilla("snowy_plains"), 0xFFFFFF);
        biomeColorMap.put(Identifier.ofVanilla("frozen_peaks"), 0xEAFBFB);
        biomeColorMap.put(Identifier.ofVanilla("stony_shore"), 0xA2A284);
        biomeColorMap.put(Identifier.ofVanilla("meadow"), 0x8CA470);
        biomeColorMap.put(Identifier.ofVanilla("old_growth_spruce_taiga"), 0x818E79);
        biomeColorMap.put(Identifier.ofVanilla("old_growth_pine_taiga"), 0x596651);
        biomeColorMap.put(Identifier.ofVanilla("windswept_hills"), 0x606060);
        biomeColorMap.put(Identifier.ofVanilla("windswept_forest"), 0x22551C);
        biomeColorMap.put(Identifier.ofVanilla("old_growth_birch_forest"), 0x589C6C);
        biomeColorMap.put(Identifier.ofVanilla("windswept_savanna"), 0xE5DA87);
        biomeColorMap.put(Identifier.ofVanilla("sparse_jungle"), 0x628B17);
        biomeColorMap.put(Identifier.ofVanilla("windswept_gravelly_hills"), 0x888888);
        biomeColorMap.put(Identifier.ofVanilla("jagged_peaks"), 0xE3ECED);
        biomeColorMap.put(Identifier.ofVanilla("snowy_slopes"), 0xDAF1F1);
        biomeColorMap.put(Identifier.ofVanilla("wooded_badlands"), 0xB09765);
        biomeColorMap.put(Identifier.ofVanilla("stony_peaks"), 0xD1D1D1);
        biomeColorMap.put(Identifier.ofVanilla("dripstone_caves"), 0xC1A58F);
        biomeColorMap.put(Identifier.ofVanilla("lush_caves"), 0xDF9634);
        biomeColorMap.put(Identifier.ofVanilla("deep_dark"), 0x000000); // 1.19
        biomeColorMap.put(Identifier.ofVanilla("mangrove_swamp"), 0x24C48E); // 1.19
        biomeColorMap.put(Identifier.ofVanilla("cherry_grove"), 0xF7B9DC); // 1.19.4
        biomeColorMap.put(Identifier.ofVanilla("pale_garden"), 0x6C6F96); // 1.21.2
    }

    @Override
    protected int getBiomeColor(ServerWorld world, BlockPos pos) {
        Optional<RegistryKey<Biome>> biomeRegistryKey = world.getBiome(pos).getKey();
        if (biomeRegistryKey.isPresent()) {
            // 获取生物群系的标识符
            Identifier biomeIdentifier = biomeRegistryKey.get().getValue();
            // 索引颜色
            Integer rgb = biomeColorMap.get(biomeIdentifier);
            if (rgb == null) {
                LOGGER.warn("无法解析的生物群系: {}", biomeIdentifier.toString());
                return -1;
            }
            return rgb;
        }
        LOGGER.warn("异常的生物群系注册状态");
        return -1;
    }

}
