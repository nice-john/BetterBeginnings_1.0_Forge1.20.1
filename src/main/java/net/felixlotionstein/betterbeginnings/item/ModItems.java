package net.felixlotionstein.betterbeginnings.item;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, BetterBeginnings.MODID);

    public static final DeferredHolder<Item, Item> STONE_HATCHET = ITEMS.register("stone_hatchet",
            () -> new AxeItem(Tiers.WOOD,
                    new Item.Properties().attributes(AxeItem.createAttributes(Tiers.WOOD, 3.0F, -2.2F))));

    public static final DeferredHolder<Item, Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModToolTiers.COPPER,
                    new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.COPPER, 4.5F, -2.9F))));

    public static final DeferredHolder<Item, Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModToolTiers.COPPER,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.COPPER, 1, -2.8F))));

    public static final DeferredHolder<Item, Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModToolTiers.COPPER,
                    new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.COPPER, -2, -1.5F))));

    public static final DeferredHolder<Item, Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModToolTiers.COPPER,
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.COPPER, 1.5F, -3.0F))));

    public static final DeferredHolder<Item, Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModToolTiers.COPPER,
                    new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.COPPER, 3, -2.4F))));

    public static final DeferredHolder<Item, Item> FIRESTARTER = ITEMS.register("firestarter",
            () -> new FlintAndSteelItem(new Item.Properties().durability(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
