package net.felixlotionstein.betterbeginnings.item;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BetterBeginnings.MODID);

    // Copper tools are vanilla in 26.x, so the mod only adds the stone hatchet.
    public static final DeferredItem<Item> STONE_HATCHET = ITEMS.registerItem("stone_hatchet",
            Item::new, props -> props.axe(ToolMaterial.STONE, 2.0F, -2.2F));

    public static final DeferredItem<FlintAndSteelItem> FIRESTARTER = ITEMS.registerItem("firestarter",
            FlintAndSteelItem::new, props -> props.durability(1));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
