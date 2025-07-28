package pers.liaohaolong.biomesnapshot.command.argument;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EnumArgumentType;

/**
 * <h3>Snapshot Mode 参数类型</h3>
 *
 * @see net.minecraft.command.argument.HeightmapArgumentType
 * @see net.minecraft.command.argument.BlockMirrorArgumentType
 * @see net.minecraft.command.argument.BlockRotationArgumentType
 */
public class SnapshotModeArgumentType extends EnumArgumentType<SnapshotMode> {

    protected SnapshotModeArgumentType() {
        super(SnapshotMode.CODEC, SnapshotMode::values);
    }

    public static SnapshotModeArgumentType snapshotMode() {
        return new SnapshotModeArgumentType();
    }

    public static SnapshotMode getSnapshotMode(CommandContext<?> context, String id) {
        return context.getArgument(id, SnapshotMode.class);
    }

}
