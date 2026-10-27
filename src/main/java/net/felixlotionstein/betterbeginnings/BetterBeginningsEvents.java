package net.felixlotionstein.betterbeginnings;

import net.felixlotionstein.betterbeginnings.util.ToolGating;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterBeginnings.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BetterBeginningsEvents {

    // A below-tier tool can't harvest the block: it breaks ~3x slower and drops nothing.
    // Vanilla already does this for stone and ores; this extends it to logs.
    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (ToolGating.lacksRequiredTool(event.getEntity(), event.getTargetBlock())) {
            event.setCanHarvest(false);
        }
    }

    // The block still breaks (no drops); just tell the player why.
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        Player player = event.getPlayer();
        if (Config.SEND_MESSAGES.get() && ToolGating.lacksRequiredTool(player, state)) {
            player.sendSystemMessage(ToolGating.requirementMessage(state));
        }
    }
}
