package net.felixlotionstein.betterbeginnings.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Decides whether a player may break a block. The rules live in block tags:
 * anything in #betterbeginnings:requires_correct_tool can only be broken by a tool
 * whose tier can harvest it (set by the needs_stone/copper/iron_tool tags).
 */
public final class ToolGating {
    private ToolGating() {}

    public static boolean isBlocked(Player player, BlockState state) {
        return !player.isCreative()
                && state.is(ModTags.Blocks.REQUIRES_CORRECT_TOOL)
                && !player.getMainHandItem().isCorrectToolForDrops(state);
    }

    public static String requirementMessage(BlockState state) {
        if (state.is(BlockTags.LOGS)) return "You need at least a stone hatchet to get wood!";
        if (state.is(BlockTags.NEEDS_IRON_TOOL)) return "You need an iron tool to mine this!";
        if (state.is(ModTags.Blocks.NEEDS_COPPER_TOOL)) return "You need a copper tool to mine this!";
        return "You need a better tool to mine this!";
    }
}
