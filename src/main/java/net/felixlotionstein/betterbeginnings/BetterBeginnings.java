package net.felixlotionstein.betterbeginnings;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.felixlotionstein.betterbeginnings.util.ToolGating;
import net.felixlotionstein.betterbeginnings.worldgen.ModBiomeModifications;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.slf4j.Logger;

public class BetterBeginnings implements ModInitializer {
    public static final String MODID = "betterbeginnings";
    public static final Logger LOGGER = LogUtils.getLogger();

    /** Chance that a broken leaves block also drops a stick. */
    private static final float LEAF_STICK_CHANCE = 0.33F;

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        ModBiomeModifications.register();

        registerCreativeTabAdditions();
        registerBlockBreakHandler();
        registerLeafSticks();

        LOGGER.info("Better Beginnings initialized");
    }

    private void registerCreativeTabAdditions() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output ->
                output.accept(ModBlocks.ROCK_BLOCK));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(ModItems.STONE_HATCHET);
            output.accept(ModItems.FIRESTARTER);
        });
    }

    // Adds a stick to the drops of any #minecraft:leaves block. It runs as part of loot
    // generation, so nothing drops in creative.
    private void registerLeafSticks() {
        LootTableEvents.MODIFY_DROPS.register((table, context, drops) -> {
            BlockState state = context.getOptional(LootContextParams.BLOCK_STATE);
            if (state != null && state.is(BlockTags.LEAVES) && context.getRandom().nextFloat() < LEAF_STICK_CHANCE) {
                drops.add(new ItemStack(Items.STICK));
            }
        });
    }

    private void registerBlockBreakHandler() {
        // The block still breaks (no drops); just tell the player why.
        PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, blockEntity) -> {
            if (Config.SEND_MESSAGES && ToolGating.lacksRequiredTool(player, state)) {
                player.sendSystemMessage(ToolGating.requirementMessage(state));
            }
            return true;
        });
    }
}
