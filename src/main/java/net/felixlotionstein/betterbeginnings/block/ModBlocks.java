package net.felixlotionstein.betterbeginnings.block;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.custom.RockBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final Block ROCK_BLOCK = new RockBlock(BlockBehaviour.Properties.copy(Blocks.GRAVEL)
            .noOcclusion().sound(SoundType.STONE).instabreak());

    public static void register() {
        registerBlockWithItem("rock_block", ROCK_BLOCK);
    }

    private static void registerBlockWithItem(String name, Block block) {
        ResourceLocation id = new ResourceLocation(BetterBeginnings.MODID, name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
    }
}
