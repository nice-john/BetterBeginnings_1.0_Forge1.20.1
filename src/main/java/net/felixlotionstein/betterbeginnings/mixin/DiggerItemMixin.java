package net.felixlotionstein.betterbeginnings.mixin;

import net.felixlotionstein.betterbeginnings.item.ModToolTiers;
import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 1.20.1 tiers are plain integer levels, so there's no level between stone (1) and iron (2)
 * for copper. This adds the missing copper tier to vanilla's harvest check:
 * - #needs_copper_tool blocks need a copper tool or an iron-level tool or better.
 * - Copper tools (ours, and other mods' in #copper_tier_tools) mine #needs_copper_tool blocks
 *   and iron ores, but nothing else that needs iron or diamond, whatever level their mod
 *   gave them. Overgeared's Fabric copper is iron level, which would skip our progression.
 */
@Mixin(DiggerItem.class)
public abstract class DiggerItemMixin {
    @Shadow @Final private TagKey<Block> blocks;

    @Inject(method = "isCorrectToolForDrops", at = @At("RETURN"), cancellable = true)
    private void betterbeginnings$requireCopperTier(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        DiggerItem item = (DiggerItem) (Object) this;
        boolean copperTier = item.getTier() == ModToolTiers.COPPER
                || ((Item) item).builtInRegistryHolder().is(ModTags.Items.COPPER_TIER_TOOLS);

        if (copperTier) {
            if (!state.is(blocks)) return;
            if (state.is(ModTags.Blocks.NEEDS_COPPER_TOOL) || state.is(BlockTags.IRON_ORES)) {
                cir.setReturnValue(true);
            } else if (state.is(BlockTags.NEEDS_IRON_TOOL) || state.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                cir.setReturnValue(false);
            }
        } else if (cir.getReturnValue() && state.is(ModTags.Blocks.NEEDS_COPPER_TOOL) && item.getTier().getLevel() < 2) {
            cir.setReturnValue(false);
        }
    }
}
