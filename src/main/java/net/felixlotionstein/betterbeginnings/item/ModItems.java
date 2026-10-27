package net.felixlotionstein.betterbeginnings.item;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class ModItems {
    // Copper tools are vanilla in 26.x, so the mod only adds the stone hatchet.
    public static final Item STONE_HATCHET = register("stone_hatchet", Item::new,
            new Item.Properties().axe(ToolMaterial.STONE, 2.0F, -2.2F));

    public static final Item FIRESTARTER = register("firestarter", FlintAndSteelItem::new,
            new Item.Properties().durability(1));

    /** Loads this class, which registers the items above. */
    public static void register() {
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterBeginnings.MODID, name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(properties.setId(key)));
    }
}
