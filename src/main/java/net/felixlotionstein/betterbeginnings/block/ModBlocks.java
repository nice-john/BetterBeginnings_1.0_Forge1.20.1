package net.felixlotionstein.betterbeginnings.block;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.custom.RockBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static final Block ROCK_BLOCK = registerWithItem("rock_block", RockBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL).noOcclusion().sound(SoundType.STONE).instabreak());

    /** Loads this class, which registers the blocks above. */
    public static void register() {
    }

    private static <T extends Block> T registerWithItem(String name, Function<BlockBehaviour.Properties, T> factory,
                                                        BlockBehaviour.Properties properties) {
        Identifier id = Identifier.fromNamespaceAndPath(BetterBeginnings.MODID, name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        T block = Registry.register(BuiltInRegistries.BLOCK, blockKey, factory.apply(properties.setId(blockKey)));
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
        Registry.register(BuiltInRegistries.ITEM, itemKey,
                new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));
        return block;
    }
}
