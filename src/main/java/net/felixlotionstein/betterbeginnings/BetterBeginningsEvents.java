package net.felixlotionstein.betterbeginnings;

import net.felixlotionstein.betterbeginnings.util.ToolGating;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;

@EventBusSubscriber(modid = BetterBeginnings.MODID)
public class BetterBeginningsEvents {

    // A below-tier tool can't harvest the block: it breaks ~3x slower and drops nothing.
    // Vanilla already does this for stone and ores; this extends it to logs.
    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (ToolGating.lacksRequiredTool(event.getEntity(), event.getTargetBlock())) {
            event.setCanHarvest(false);
        }
    }

    // The block still breaks (no drops); just tell the player why. This event fires on
    // both sides, so only the server sends the message.
    @SubscribeEvent
    public static void onBlockBreak(BreakBlockEvent event) {
        BlockState state = event.getState();
        Player player = event.getPlayer();
        if (!event.getLevel().isClientSide() && Config.SEND_MESSAGES.get()
                && ToolGating.lacksRequiredTool(player, state)) {
            player.sendSystemMessage(ToolGating.requirementMessage(state));
        }
    }
}
