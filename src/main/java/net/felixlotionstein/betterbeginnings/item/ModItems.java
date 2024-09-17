package net.felixlotionstein.betterbeginnings.item;

import net.minecraft.world.item.*;
import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.felixlotionstein.betterbeginnings.item.ModToolTiers;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterBeginnings.MODID);

    public static final RegistryObject<Item> STONE_HATCHET = ITEMS.register("stone_hatchet",
            () -> new AxeItem(Tiers.WOOD, 3.0F, -2.2F, new Item.Properties()));  // No change needed

    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModToolTiers.COPPER, 4.5F, -2.9F, new Item.Properties()));  // No change needed

    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModToolTiers.COPPER, 1, -2.8F, new Item.Properties()));  // Changed 4.5F to 4

    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModToolTiers.COPPER, -2, -1.5F, new Item.Properties()));  // Changed -1.5F to -2

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModToolTiers.COPPER, 1.5F, -3.0F, new Item.Properties()));  // No change needed

    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModToolTiers.COPPER, 3, -2.4F, new Item.Properties()));  // Changed 3F to 3


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
