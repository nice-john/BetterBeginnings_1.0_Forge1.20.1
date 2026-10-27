package net.felixlotionstein.betterbeginnings.util;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Tool requirements live in block tags: a block in #betterbeginnings:requires_correct_tool
 * only drops anything when the held tool's tier can harvest it (set by the
 * needs_stone/copper/iron_tool tags). A weaker tool still breaks it, just slowly and
 * with no drops, so a player can never get trapped.
 */
public final class ToolGating {
    private ToolGating() {}

    public static boolean lacksRequiredTool(Player player, BlockState state) {
        // instabuild covers creative and creative-like modes from other mods.
        return !player.getAbilities().instabuild
                && state.is(ModTags.Blocks.REQUIRES_CORRECT_TOOL)
                && !player.getMainHandItem().isCorrectToolForDrops(state);
    }

    public static Component requirementMessage(BlockState state) {
        if (state.is(BlockTags.LOGS)) return Component.translatable("message.betterbeginnings.need_hatchet");
        if (state.is(BlockTags.NEEDS_IRON_TOOL)) return Component.translatable("message.betterbeginnings.need_iron_tool");
        if (state.is(ModTags.Blocks.NEEDS_COPPER_TOOL)) return Component.translatable("message.betterbeginnings.need_copper_tool");
        return Component.translatable("message.betterbeginnings.need_better_tool");
    }
}
