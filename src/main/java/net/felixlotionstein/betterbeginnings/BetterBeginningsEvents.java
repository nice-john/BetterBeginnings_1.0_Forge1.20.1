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

    private static final String MSG_NEED_HATCHET = "message.betterbeginnings.need_hatchet";
    private static final String MSG_NEED_COPPER = "message.betterbeginnings.need_copper_tool";
    private static final String MSG_NEED_IRON = "message.betterbeginnings.need_iron_tool";

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

        if (state.is(BlockTags.LOGS) && !(tool.getItem() instanceof AxeItem)) {
            denyBreak(event, player, MSG_NEED_HATCHET);
            return;
        }

        if ((state.is(Blocks.STONE) || state.is(Blocks.IRON_ORE))
                && (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE))) {
            denyBreak(event, player, MSG_NEED_COPPER);
            return;
        }

        if (state.is(Blocks.COAL_ORE)
                && (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE) || tool.is(ModItems.COPPER_PICKAXE.get()))) {
            denyBreak(event, player, MSG_NEED_IRON);
            return;
        }

        if (state.is(BlockTags.LEAVES)) {
            int count = world.random.nextInt(3);

            if (count > 1) {
                ItemStack drop = new ItemStack(Items.STICK, 1);
                Block.popResource(world, pos, drop);
            }
        }
    }

    private static void denyBreak(BlockEvent.BreakEvent event, Player player, String translationKey) {
        event.setCanceled(true);

        if (Config.SEND_MESSAGES.get()) {
            player.displayClientMessage(Component.translatable(translationKey), true);
        }
    }
}
