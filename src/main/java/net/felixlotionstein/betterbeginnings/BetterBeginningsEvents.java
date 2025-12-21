package net.felixlotionstein.betterbeginnings;

import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterBeginnings.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BetterBeginningsEvents {

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        BlockState state = event.getState();
        ItemStack tool = event.getEntity().getMainHandItem();

        // Check if the block is a log
        if (state.is(BlockTags.LOGS)) {
            // Slow down wood cutting if the player is not using an AxeItem (or your custom hatchet if it extends AxeItem)
            if (!(tool.getItem() instanceof AxeItem)) {
                event.setNewSpeed(0.2F);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        ItemStack tool = event.getPlayer().getMainHandItem();
        BlockPos pos = event.getPos();
        Level world = (Level) event.getLevel();
        Player player = event.getPlayer();

        // Logic for Logs: Prevent item drops if no axe is used
        if (state.is(BlockTags.LOGS)) {
            if (!(tool.getItem() instanceof AxeItem)) {
                // Remove the block but prevent it from dropping the log item
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                event.setCanceled(true);

                if (Config.SEND_MESSAGES.get()) {
                    player.sendSystemMessage(Component.literal("You need the right tool to get wood!"));
                }
            }
        }

        // Logic for Leaves: Chance to drop sticks when broken
        if (state.is(BlockTags.LEAVES)) {
            // 1 in 3 chance to drop a stick
            int count = world.random.nextInt(3);

            if (count > 1) {
                ItemStack drop = new ItemStack(Items.STICK, 1);
                Block.popResource(world, pos, drop);
            }
        }
    }
}