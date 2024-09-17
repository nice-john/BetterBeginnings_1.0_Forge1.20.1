package net.felixlotionstein.betterbeginnings;

import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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
            // Check if the tool is not an axe or the custom stone hatchet
            if (!(tool.is(ItemTags.AXES) || tool.is(ModItems.STONE_HATCHET.get()))) {
                event.setNewSpeed(0.2F); // Slow down the breaking speed if the tool is not an axe
            }
        }
        if (state.is(ModTags.Blocks.NEEDS_COPPER_TOOL)) {
            // Check if the tool is not an axe or the custom stone hatchet
            if (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE)) {
                event.setNewSpeed(0.4F); // Slow down the breaking speed if the tool is not an axe

            }
        }
    }



    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        ItemStack tool = event.getPlayer().getMainHandItem();
        BlockPos pos = event.getPos();
        Level world = (Level) event.getLevel();
        Player player = event.getPlayer(); // Get the player who triggered the event

        // Check if the block is a log
        if (state.is(BlockTags.LOGS)) {
            // Check if the tool is not an axe or the custom stone hatchet
            if (!(tool.is(ItemTags.AXES) || tool.is(ModItems.STONE_HATCHET.get()))) {
                // Prevent drops by setting the block to air without triggering drops
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                event.setCanceled(true); // Cancel the event to prevent any other side effects

                // Send a message to the player
                player.sendSystemMessage(Component.literal("You need the right tool to get wood!"));
            }
        }
        // Check if the block is a log
        if (state.is(ModTags.Blocks.NEEDS_COPPER_TOOL)) {
            // Check if the tool is not an axe or the custom stone hatchet
            if (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE)) {
                // Prevent drops by setting the block to air without triggering drops
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                event.setCanceled(true); // Cancel the event to prevent any other side effects

                // Send a message to the player
                player.sendSystemMessage(Component.literal("You need a copper tool to mine this!"));
                player.sendSystemMessage(Component.literal("You can craft cobblestone using four rocks!"));
            }
        }
    }
}

