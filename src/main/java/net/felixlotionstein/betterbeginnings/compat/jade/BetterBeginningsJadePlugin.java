package net.felixlotionstein.betterbeginnings.compat.jade;

import com.mojang.logging.LogUtils;
import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.addon.harvest.ToolHandler;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

import java.util.List;

/**
 * Jade's harvest hint shows the first tool in its list that can harvest the block, and
 * its lists only hold vanilla tools, so stone (which needs copper here) showed an iron
 * pickaxe. This slots our tools in at their tier. Jade only loads this when installed.
 */
@WailaPlugin
public class BetterBeginningsJadePlugin implements IWailaPlugin {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // The tool lists aren't part of Jade's API, so a Jade update must never crash the game.
        try {
            insertBefore("pickaxe", Items.IRON_PICKAXE, ModItems.COPPER_PICKAXE.get());
            insertBefore("axe", Items.STONE_AXE, ModItems.STONE_HATCHET.get());
            insertBefore("axe", Items.IRON_AXE, ModItems.COPPER_AXE.get());
            insertBefore("shovel", Items.IRON_SHOVEL, ModItems.COPPER_SHOVEL.get());
            insertBefore("hoe", Items.IRON_HOE, ModItems.COPPER_HOE.get());
        } catch (RuntimeException | LinkageError e) {
            LOGGER.warn("Couldn't add Better Beginnings tools to Jade's harvest tool hints", e);
        }
    }

    private static void insertBefore(String handlerName, Item vanillaTool, Item ourTool) {
        ToolHandler handler = HarvestToolProvider.TOOL_HANDLERS.get(ResourceLocation.fromNamespaceAndPath("jade", handlerName));
        if (handler == null) return;
        List<ItemStack> tools = handler.getTools();
        int index = tools.size();
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).is(vanillaTool)) {
                index = i;
                break;
            }
        }
        tools.add(index, ourTool.getDefaultInstance());
    }
}
