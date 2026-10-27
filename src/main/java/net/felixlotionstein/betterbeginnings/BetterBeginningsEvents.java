package net.felixlotionstein.betterbeginnings;

import net.felixlotionstein.betterbeginnings.util.ToolGating;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterBeginnings.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BetterBeginningsEvents {

    // Visual resistance: the wrong tool chips away slowly before the break is refused.
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        BlockState state = event.getState();
        if (ToolGating.isBlocked(event.getEntity(), state)) {
            event.setNewSpeed(state.is(BlockTags.LOGS) ? 0.2F : 0.4F);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        Player player = event.getPlayer();

        if (ToolGating.isBlocked(player, state)) {
            // Canceling leaves the block in place and drops nothing.
            event.setCanceled(true);
            if (Config.SEND_MESSAGES.get()) {
                player.sendSystemMessage(Component.literal(ToolGating.requirementMessage(state)));
            }
            return;
        }

        if (state.is(BlockTags.LEAVES)) {
            Level world = (Level) event.getLevel();
            BlockPos pos = event.getPos();
            if (world.random.nextInt(3) > 1) {
                Block.popResource(world, pos, new ItemStack(Items.STICK, 1));
            }
        }
    }
}
