package net.felixlotionstein.betterbeginnings.mixin;

import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerDestroySpeedMixin {

    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void betterbeginnings$modifyDestroySpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        Player self = (Player) (Object) this;
        ItemStack tool = self.getMainHandItem();

        if (state.is(BlockTags.LOGS) && !(tool.getItem() instanceof AxeItem)) {
            cir.setReturnValue(0.5F);
            return;
        }
        if ((state.is(ModTags.Blocks.NEEDS_COPPER_TOOL) || state.is(Blocks.STONE))
                && (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE))) {
            cir.setReturnValue(0.5F);
            return;
        }
        if (state.is(Blocks.COAL_ORE)
                && (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE) || tool.is(ModItems.COPPER_PICKAXE))) {
            cir.setReturnValue(0.5F);
        }
    }
}
