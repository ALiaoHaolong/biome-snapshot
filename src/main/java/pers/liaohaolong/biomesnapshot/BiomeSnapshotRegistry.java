package pers.liaohaolong.biomesnapshot;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import pers.liaohaolong.biomesnapshot.color.resolver.ColorResolver;
import pers.liaohaolong.biomesnapshot.command.argument.ColorResolverArgumentType;

import static pers.liaohaolong.biomesnapshot.BiomeSnapshot.MOD_ID;
import static pers.liaohaolong.biomesnapshot.color.resolver.ColorResolvers.*;

/**
 * <h3>注册表</h3>
 */
public class BiomeSnapshotRegistry {

    /**
     * 注册命令参数类型
     */
    public static void registerArgumentType() {
        ArgumentTypeRegistry.registerArgumentType(
                Identifier.of(MOD_ID, "color_resolver"),
                ColorResolverArgumentType.class,
                ConstantArgumentSerializer.of(ColorResolverArgumentType::colorResolver)
        );
    }

}
