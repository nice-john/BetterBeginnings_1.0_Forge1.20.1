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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = BetterBeginnings.MODID, bus = EventBusSubscriber.Bus.GAME)
public class BetterBeginningsEvents {

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        BlockState state = event.getState();
        ItemStack tool = event.getEntity().getMainHandItem();

        if (state.is(BlockTags.LOGS)) {
            if (!(tool.getItem() instanceof AxeItem)) {
                event.setNewSpeed(0.2F);
            }
        }
        if (state.is(ModTags.Blocks.NEEDS_COPPER_TOOL) || state.is(Blocks.STONE)) {
            if (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE)) {
                event.setNewSpeed(0.4F);
            }
        }
        if (state.is(Blocks.COAL_ORE)) {
            if (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE) || tool.is(ModItems.COPPER_PICKAXE.get())) {
                event.setNewSpeed(0.4F);
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

        if (state.is(BlockTags.LOGS)) {
            if (!(tool.getItem() instanceof AxeItem)) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                event.setCanceled(true);

                if (Config.SEND_MESSAGES.get()) {
                    player.sendSystemMessage(Component.literal("You need at least a stone hatchet to chop wood!"));
                }
            }
        }
        if (state.is(Blocks.STONE) || state.is(Blocks.IRON_ORE)) {
            if (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE)) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                event.setCanceled(true);

                if (Config.SEND_MESSAGES.get()) {
                    player.sendSystemMessage(Component.literal("You need a copper tool to mine this!"));
                }
            }
        }
        if (state.is(Blocks.COAL_ORE)) {
            if (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE) || tool.is(ModItems.COPPER_PICKAXE.get())) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                event.setCanceled(true);

                if (Config.SEND_MESSAGES.get()) {
                    player.sendSystemMessage(Component.literal("You need an iron tool to mine this!"));
                }
            }
        }
        if (state.is(BlockTags.LEAVES)) {
            int count = world.random.nextInt(3);

            if (count > 1) {
                ItemStack drop = new ItemStack(Items.STICK, 1);
                Block.popResource(world, pos, drop);
            }
        }
    }
}
