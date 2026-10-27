package net.felixlotionstein.betterbeginnings.item;

import net.felixlotionstein.betterbeginnings.util.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ModToolTiers {
    // Sits between stone and iron: stone tools can't harvest #needs_copper_tool,
    // copper tools can't harvest #needs_iron_tool.
    public static final Tier COPPER = new Tier() {
        @Override public int getUses() { return 190; }
        @Override public float getSpeed() { return 5f; }
        @Override public float getAttackDamageBonus() { return 1.5f; }
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL; }
        @Override public int getEnchantmentValue() { return 10; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.COPPER_INGOT); }
    };
}
