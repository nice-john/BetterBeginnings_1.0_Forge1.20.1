package net.felixlotionstein.betterbeginnings.item;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class ModItems {
    public static final Item STONE_HATCHET = new AxeItem(Tiers.WOOD,
            new Item.Properties().attributes(AxeItem.createAttributes(Tiers.WOOD, 3.0F, -2.2F)));

    public static final Item COPPER_AXE = new AxeItem(ModToolTiers.COPPER,
            new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.COPPER, 4.5F, -2.9F)));

    public static final Item COPPER_PICKAXE = new PickaxeItem(ModToolTiers.COPPER,
            new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.COPPER, 1, -2.8F)));

    public static final Item COPPER_HOE = new HoeItem(ModToolTiers.COPPER,
            new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.COPPER, -2, -1.5F)));

    public static final Item COPPER_SHOVEL = new ShovelItem(ModToolTiers.COPPER,
            new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.COPPER, 1.5F, -3.0F)));

    public static final Item COPPER_SWORD = new SwordItem(ModToolTiers.COPPER,
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.COPPER, 3, -2.4F)));

    public static final Item FIRESTARTER = new FlintAndSteelItem(new Item.Properties().durability(1));

    public static void register() {
        reg("stone_hatchet", STONE_HATCHET);
        reg("copper_axe", COPPER_AXE);
        reg("copper_pickaxe", COPPER_PICKAXE);
        reg("copper_hoe", COPPER_HOE);
        reg("copper_shovel", COPPER_SHOVEL);
        reg("copper_sword", COPPER_SWORD);
        reg("firestarter", FIRESTARTER);
    }

    private static void reg(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(BetterBeginnings.MODID, name), item);
    }
}
