package pers.liaohaolong.biomesnapshot.color.resolver;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;

@FunctionalInterface
public interface ColorResolver {

    /**
     * 在命令执行的准备阶段调用，调用本方法后会反复调用 {@link #getColor(MinecraftServer, int, int)}。
     */
    default void prepare(CommandSourceStack source) {
    }

    int getColor(MinecraftServer world, int x, int z);

    /**
     * 在反复调用 {@link #getColor(MinecraftServer, int, int)} 后的命令执行的结束阶段调用。
     */
    default void finish(CommandSourceStack source) {
    }

    /**
     * 如果{@link #getColor(MinecraftServer, int, int)}中存在加载/生成区块的步骤，则本方法必须返回 true，
     * 以启用区块优化机制。
     *
     * @return 是否需要区块优化
     */
    default boolean enableChunkOptimization() {
        return false;
    }

}
