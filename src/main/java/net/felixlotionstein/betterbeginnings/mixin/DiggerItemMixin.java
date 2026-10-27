package net.felixlotionstein.betterbeginnings.mixin;

import net.felixlotionstein.betterbeginnings.item.ModToolTiers;
import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 1.20.1 tiers are plain integer levels, so there's no level between stone (1) and iron (2)
 * for copper. This adds the missing rule to vanilla's harvest check: #needs_copper_tool blocks
 * need the copper tier or an iron-level tier or better.
 */
@Mixin(DiggerItem.class)
public abstract class DiggerItemMixin {

    @Inject(method = "isCorrectToolForDrops", at = @At("RETURN"), cancellable = true)
    private void betterbeginnings$requireCopperTier(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() || !state.is(ModTags.Blocks.NEEDS_COPPER_TOOL)) return;
        Tier tier = ((DiggerItem) (Object) this).getTier();
        if (tier != ModToolTiers.COPPER && tier.getLevel() < 2) {
            cir.setReturnValue(false);
        }
    }
}
