package net.felixlotionstein.betterbeginnings.util;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BetterBeginnings.MODID, name));
        }
    }

    public static class Blocks {
        /** Blocks that only copper-tier tools or better can harvest. */
        public static final TagKey<Block> NEEDS_COPPER_TOOL = tag("needs_copper_tool");
        /** Blocks copper-tier tools can't harvest (the copper tier's "incorrect" tag). */
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = tag("incorrect_for_copper_tool");
        /** Blocks that can't be broken at all unless the held tool's tier can harvest them. */
        public static final TagKey<Block> REQUIRES_CORRECT_TOOL = tag("requires_correct_tool");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(BetterBeginnings.MODID, name));
        }
    }
}
