package pers.liaohaolong.biomesnapshot.color.resolver.biome;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h3>生物群系颜色解析器</h3>
 */
public class BiomeColorResolver extends AbstractBiomeColorResolver {

    private static final Logger LOGGER = LogManager.getLogger();

    private final HashMap<Identifier, Integer> biomeColorMap = new LinkedHashMap<>();

    private final HashSet<Identifier> unknownBiomeSet = new HashSet<>();

    public BiomeColorResolver() {
        // 以下生物群系颜色来自 https://github.com/toolbox4minecraft/amidst/wiki/Biome-Color-Table
        biomeColorMap.put(new Identifier("ocean"), 0x000070);
        biomeColorMap.put(new Identifier("plains"), 0x8DB360);
        biomeColorMap.put(new Identifier("desert"), 0xFA9418);
        biomeColorMap.put(new Identifier("mountains"), 0x606060);
        biomeColorMap.put(new Identifier("forest"), 0x056621);
        biomeColorMap.put(new Identifier("taiga"), 0x0B6659);
        biomeColorMap.put(new Identifier("swamp"), 0x07F9B2);
        biomeColorMap.put(new Identifier("river"), 0x0000FF);
        biomeColorMap.put(new Identifier("nether_wastes"), 0xBF3B3B);
        biomeColorMap.put(new Identifier("the_end"), 0x8080FF);
        biomeColorMap.put(new Identifier("frozen_ocean"), 0x7070D6);
        biomeColorMap.put(new Identifier("frozen_river"), 0xA0A0FF);
        biomeColorMap.put(new Identifier("snowy_tundra"), 0xFFFFFF);
        biomeColorMap.put(new Identifier("snowy_mountains"), 0xA0A0A0);
        biomeColorMap.put(new Identifier("mushroom_fields"), 0xFF00FF);
        biomeColorMap.put(new Identifier("mushroom_field_shore"), 0xA000FF);
        biomeColorMap.put(new Identifier("beach"), 0xFADE55);
        biomeColorMap.put(new Identifier("desert_hills"), 0xD25F12);
        biomeColorMap.put(new Identifier("wooded_hills"), 0x22551C);
        biomeColorMap.put(new Identifier("taiga_hills"), 0x163933);
        biomeColorMap.put(new Identifier("mountain_edge"), 0x72789A);
        biomeColorMap.put(new Identifier("jungle"), 0x537B09);
        biomeColorMap.put(new Identifier("jungle_hills"), 0x2C4205);
        biomeColorMap.put(new Identifier("jungle_edge"), 0x628B17);
        biomeColorMap.put(new Identifier("deep_ocean"), 0x000030);
        biomeColorMap.put(new Identifier("stone_shore"), 0xA2A284);
        biomeColorMap.put(new Identifier("snowy_beach"), 0xFAF0C0);
        biomeColorMap.put(new Identifier("birch_forest"), 0x307444);
        biomeColorMap.put(new Identifier("birch_forest_hills"), 0x1F5F32);
        biomeColorMap.put(new Identifier("dark_forest"), 0x40511A);
        biomeColorMap.put(new Identifier("snowy_taiga"), 0x31554A);
        biomeColorMap.put(new Identifier("snowy_taiga_hills"), 0x243F36);
        biomeColorMap.put(new Identifier("giant_tree_taiga"), 0x596651);
        biomeColorMap.put(new Identifier("giant_tree_taiga_hills"), 0x454F3E);
        biomeColorMap.put(new Identifier("wooded_mountains"), 0x507050);
        biomeColorMap.put(new Identifier("savanna"), 0xBDB25F);
        biomeColorMap.put(new Identifier("savanna_plateau"), 0xA79D64);
        biomeColorMap.put(new Identifier("badlands"), 0xD94515);
        biomeColorMap.put(new Identifier("wooded_badlands_plateau"), 0xB09765);
        biomeColorMap.put(new Identifier("badlands_plateau"), 0xCA8C65);
        biomeColorMap.put(new Identifier("small_end_islands"), 0x8080FF);
        biomeColorMap.put(new Identifier("end_midlands"), 0x8080FF);
        biomeColorMap.put(new Identifier("end_highlands"), 0x8080FF);
        biomeColorMap.put(new Identifier("end_barrens"), 0x8080FF);
        biomeColorMap.put(new Identifier("warm_ocean"), 0x0000AC);
        biomeColorMap.put(new Identifier("lukewarm_ocean"), 0x000090);
        biomeColorMap.put(new Identifier("cold_ocean"), 0x202070);
        biomeColorMap.put(new Identifier("deep_warm_ocean"), 0x000050);
        biomeColorMap.put(new Identifier("deep_lukewarm_ocean"), 0x000040);
        biomeColorMap.put(new Identifier("deep_cold_ocean"), 0x202038);
        biomeColorMap.put(new Identifier("deep_frozen_ocean"), 0x404090);
        biomeColorMap.put(new Identifier("the_void"), 0x000000);
        biomeColorMap.put(new Identifier("sunflower_plains"), 0xB5DB88);
        biomeColorMap.put(new Identifier("desert_lakes"), 0xFFBC40);
        biomeColorMap.put(new Identifier("gravelly_mountains"), 0x888888);
        biomeColorMap.put(new Identifier("flower_forest"), 0x2D8E49);
        biomeColorMap.put(new Identifier("taiga_mountains"), 0x338E81);
        biomeColorMap.put(new Identifier("swamp_hills"), 0x2FFFDA);
        biomeColorMap.put(new Identifier("ice_spikes"), 0xB4DCDC);
        biomeColorMap.put(new Identifier("modified_jungle"), 0x7BA331);
        biomeColorMap.put(new Identifier("modified_jungle_edge"), 0x8AB33F);
        biomeColorMap.put(new Identifier("tall_birch_forest"), 0x589C6C);
        biomeColorMap.put(new Identifier("tall_birch_hills"), 0x47875A);
        biomeColorMap.put(new Identifier("dark_forest_hills"), 0x687942);
        biomeColorMap.put(new Identifier("snowy_taiga_mountains"), 0x597D72);
        biomeColorMap.put(new Identifier("giant_spruce_taiga"), 0x818E79);
        biomeColorMap.put(new Identifier("giant_spruce_taiga_hills"), 0x6D7766);
        biomeColorMap.put(new Identifier("gravelly_mountains_plus"), 0x789878);
        biomeColorMap.put(new Identifier("shattered_savanna"), 0xE5DA87);
        biomeColorMap.put(new Identifier("shattered_savanna_plateau"), 0xCFC58C);
        biomeColorMap.put(new Identifier("eroded_badlands"), 0xFF6D3D);
        biomeColorMap.put(new Identifier("modified_wooded_badlands_plateau"), 0xD8BF8D);
        biomeColorMap.put(new Identifier("modified_badlands_plateau"), 0xF2B48D);
        biomeColorMap.put(new Identifier("bamboo_jungle"), 0x768E14);
        biomeColorMap.put(new Identifier("bamboo_jungle_hills"), 0x3B470A);
        biomeColorMap.put(new Identifier("soul_sand_valley"), 0x5E3830);
        biomeColorMap.put(new Identifier("crimson_forest"), 0xDD0808);
        biomeColorMap.put(new Identifier("warped_forest"), 0x49907B);
        biomeColorMap.put(new Identifier("basalt_deltas"), 0x403636);
        // 以下生物群系颜色来自 chunkbase
        biomeColorMap.put(new Identifier("grove"), 0xDFECE5);
        biomeColorMap.put(new Identifier("snowy_plains"), 0xFFFFFF);
        biomeColorMap.put(new Identifier("frozen_peaks"), 0xEAFBFB);
        biomeColorMap.put(new Identifier("stony_shore"), 0xA2A284);
        biomeColorMap.put(new Identifier("meadow"), 0x8CA470);
        biomeColorMap.put(new Identifier("old_growth_spruce_taiga"), 0x818E79);
        biomeColorMap.put(new Identifier("old_growth_pine_taiga"), 0x596651);
        biomeColorMap.put(new Identifier("windswept_hills"), 0x606060);
        biomeColorMap.put(new Identifier("windswept_forest"), 0x22551C);
        biomeColorMap.put(new Identifier("old_growth_birch_forest"), 0x589C6C);
        biomeColorMap.put(new Identifier("windswept_savanna"), 0xE5DA87);
        biomeColorMap.put(new Identifier("sparse_jungle"), 0x628B17);
        biomeColorMap.put(new Identifier("windswept_gravelly_hills"), 0x888888);
        biomeColorMap.put(new Identifier("jagged_peaks"), 0xE3ECED);
        biomeColorMap.put(new Identifier("snowy_slopes"), 0xDAF1F1);
        biomeColorMap.put(new Identifier("wooded_badlands"), 0xB09765);
        biomeColorMap.put(new Identifier("stony_peaks"), 0xD1D1D1);
        biomeColorMap.put(new Identifier("dripstone_caves"), 0xC1A58F);
        biomeColorMap.put(new Identifier("lush_caves"), 0xDF9634);
        biomeColorMap.put(new Identifier("deep_dark"), 0x000000); // 1.19
        biomeColorMap.put(new Identifier("mangrove_swamp"), 0x24C48E); // 1.19
        biomeColorMap.put(new Identifier("cherry_grove"), 0xF7B9DC); // 1.19.4
        biomeColorMap.put(new Identifier("pale_garden"), 0x6C6F96); // 1.21.2
    }

    @Override
    public void prepare(ServerCommandSource source) {
        unknownBiomeSet.clear();
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
                unknownBiomeSet.add(biomeIdentifier);
                return -1;
            }
            return rgb;
        }
        LOGGER.error("异常的生物群系注册状态：{}, {}, {}, {}", world.getServer().getVersion(), world.getSeed(), world.getDimension().effects(), pos.toShortString());
        return -1;
    }

    @Override
    public void finish(ServerCommandSource source) {
        if (unknownBiomeSet.isEmpty())
            return;

        source.sendFeedback(() -> Text.translatable("command.biome-snapshot.unknownBiomeList", unknownBiomeSet.stream().map(Identifier::toString).collect(Collectors.joining(", "))), false);
    }

    public HashMap<Identifier, Integer> getBiomeColorMap() {
        return biomeColorMap;
    }

}
