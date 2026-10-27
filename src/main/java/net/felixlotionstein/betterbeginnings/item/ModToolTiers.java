package net.felixlotionstein.betterbeginnings.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ModToolTiers {
    // Same harvest level as stone (1), so copper can't harvest #needs_iron_tool blocks.
    // DiggerItemMixin is what lets copper (and only copper or better) harvest #needs_copper_tool.
    public static final Tier COPPER = new Tier() {
        @Override public int getUses() { return 190; }
        @Override public float getSpeed() { return 5f; }
        @Override public float getAttackDamageBonus() { return 1.5f; }
        @Override public int getLevel() { return 1; }
        @Override public int getEnchantmentValue() { return 10; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.COPPER_INGOT); }
    };
}
