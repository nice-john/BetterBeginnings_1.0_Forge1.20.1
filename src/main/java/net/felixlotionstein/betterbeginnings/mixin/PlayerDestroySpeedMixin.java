package net.felixlotionstein.betterbeginnings.mixin;

import net.felixlotionstein.betterbeginnings.util.ToolGating;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerDestroySpeedMixin {

    // Visual resistance: the wrong tool chips away slowly before the break is refused.
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void betterbeginnings$modifyDestroySpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        if (ToolGating.isBlocked((Player) (Object) this, state)) {
            cir.setReturnValue(0.5F);
        }
    }
}
