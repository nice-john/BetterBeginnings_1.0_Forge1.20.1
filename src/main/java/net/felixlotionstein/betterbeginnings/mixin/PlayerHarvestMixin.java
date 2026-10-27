package net.felixlotionstein.betterbeginnings.mixin;

import net.felixlotionstein.betterbeginnings.util.ToolGating;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerHarvestMixin {

    // A below-tier tool can't harvest the block: it breaks ~3x slower and drops nothing.
    // Vanilla already does this for stone and ores; this extends it to logs.
    @Inject(method = "hasCorrectToolForDrops", at = @At("RETURN"), cancellable = true)
    private void betterbeginnings$requireTier(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && ToolGating.lacksRequiredTool((Player) (Object) this, state)) {
            cir.setReturnValue(false);
        }
    }
}
