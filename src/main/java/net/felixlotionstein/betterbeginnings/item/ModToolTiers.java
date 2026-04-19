package net.felixlotionstein.betterbeginnings.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class ModToolTiers {
    public static final Tier COPPER = new Tier() {
        @Override public int getUses() { return 190; }
        @Override public float getSpeed() { return 5f; }
        @Override public float getAttackDamageBonus() { return 1.5f; }
        @Override public int getLevel() { return 2; }
        @Override public int getEnchantmentValue() { return 10; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(Items.COPPER_INGOT); }
    };
}
