package pers.liaohaolong.biomesnapshot;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <h3>工具</h3>
 */
public final class BiomeSnapshotUtils {

    @Contract(pure = true)
    public static @NotNull String toHexString(int color) {
        return String.format("%06X", (0xFFFFFF & color));
    }

}
