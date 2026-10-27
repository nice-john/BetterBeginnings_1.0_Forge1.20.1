package net.felixlotionstein.betterbeginnings.util;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        /** Blocks that only copper-tier tools or better can harvest. */
        public static final TagKey<Block> NEEDS_COPPER_TOOL = tag("needs_copper_tool");
        /** Blocks that drop nothing unless the held tool's tier can harvest them. */
        public static final TagKey<Block> REQUIRES_CORRECT_TOOL = tag("requires_correct_tool");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BetterBeginnings.MODID, name));
        }
    }
}
