package net.felixlotionstein.betterbeginnings;

import com.mojang.logging.LogUtils;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.felixlotionstein.betterbeginnings.worldgen.ModFeatures;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BetterBeginnings.MODID)
public class BetterBeginnings
{
    public static final String MODID = "betterbeginnings";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterBeginnings(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModFeatures.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModBlocks.ROCK_BLOCK.get());
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.STONE_HATCHET.get());
            event.accept(ModItems.COPPER_AXE.get());
            event.accept(ModItems.COPPER_PICKAXE.get());
            event.accept(ModItems.COPPER_SHOVEL.get());
            event.accept(ModItems.COPPER_HOE.get());
            event.accept(ModItems.FIRESTARTER.get());
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.COPPER_SWORD.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
