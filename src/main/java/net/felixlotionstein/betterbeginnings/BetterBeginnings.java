package net.felixlotionstein.betterbeginnings;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.felixlotionstein.betterbeginnings.worldgen.ModBiomeModifications;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.slf4j.Logger;

public class BetterBeginnings implements ModInitializer {
    public static final String MODID = "betterbeginnings";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        ModBiomeModifications.register();

        registerCreativeTabAdditions();
        registerBlockBreakHandler();
        registerLeafLootModification();

        LOGGER.info("Better Beginnings initialized");
    }

    private void registerCreativeTabAdditions() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries ->
                entries.accept(ModBlocks.ROCK_BLOCK));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(ModItems.STONE_HATCHET);
            entries.accept(ModItems.COPPER_AXE);
            entries.accept(ModItems.COPPER_PICKAXE);
            entries.accept(ModItems.COPPER_SHOVEL);
            entries.accept(ModItems.COPPER_HOE);
            entries.accept(ModItems.FIRESTARTER);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries ->
                entries.accept(ModItems.COPPER_SWORD));
    }

    private void registerLeafLootModification() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (!source.isBuiltin()) return;
            if (id.getNamespace().equals("minecraft")
                    && id.getPath().startsWith("blocks/")
                    && id.getPath().endsWith("_leaves")) {
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK))
                        .when(LootItemRandomChanceCondition.randomChance(0.33f))
                );
            }
        });
    }

    private void registerBlockBreakHandler() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            ItemStack tool = player.getMainHandItem();

            if (state.is(BlockTags.LOGS) && !(tool.getItem() instanceof AxeItem)) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                if (Config.SEND_MESSAGES) {
                    player.sendSystemMessage(Component.literal("You need at least a stone hatchet to get wood!"));
                }
                return false;
            }
            if ((state.is(Blocks.STONE) || state.is(Blocks.IRON_ORE))
                    && (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE))) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                if (Config.SEND_MESSAGES) {
                    player.sendSystemMessage(Component.literal("You need a copper tool to mine this!"));
                }
                return false;
            }
            if (state.is(Blocks.COAL_ORE)
                    && (tool.is(Items.STONE_PICKAXE) || tool.is(Items.WOODEN_PICKAXE) || tool.is(ModItems.COPPER_PICKAXE))) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                if (Config.SEND_MESSAGES) {
                    player.sendSystemMessage(Component.literal("You need an iron tool to mine this!"));
                }
                return false;
            }
            return true;
        });
    }
}
