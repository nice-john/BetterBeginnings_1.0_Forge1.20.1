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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = BetterBeginnings.MODID, bus = EventBusSubscriber.Bus.GAME)
public class BetterBeginningsEvents {

    // A below-tier tool can't harvest the block: it breaks ~3x slower and drops nothing.
    // Vanilla already does this for stone and ores; this extends it to logs.
    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (ToolGating.lacksRequiredTool(event.getEntity(), event.getTargetBlock())) {
            event.setCanHarvest(false);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        Player player = event.getPlayer();

        // The block still breaks (no drops); just tell the player why.
        if (ToolGating.lacksRequiredTool(player, state)) {
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
