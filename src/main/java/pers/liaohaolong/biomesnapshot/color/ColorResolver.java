package pers.liaohaolong.biomesnapshot.color;

import net.minecraft.server.world.ServerWorld;

@FunctionalInterface
public interface ColorResolver {

    int getColor(ServerWorld world, int x, int z);

    /**
     * 如果{@link #getColor(ServerWorld, int, int)}中存在加载/生成区块的步骤，则本方法必须返回 true，
     * 以启用区块优化机制。
     *
     * @return 是否需要区块优化
     */
    default boolean enableChunkOptimization() {
        return false;
    }

}
