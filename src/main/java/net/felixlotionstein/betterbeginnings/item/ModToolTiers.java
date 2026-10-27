package net.felixlotionstein.betterbeginnings.item;

import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    // Sits between stone and iron: stone tools can't harvest #needs_copper_tool,
    // copper tools can't harvest #needs_iron_tool.
    public static final Tier COPPER = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL,
            190,
            5f,
            1.5f,
            10,
            () -> Ingredient.of(Items.COPPER_INGOT)
    );
}
