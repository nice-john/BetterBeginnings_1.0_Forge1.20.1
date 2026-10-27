package net.felixlotionstein.betterbeginnings.block;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.custom.RockBlock;
import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BetterBeginnings.MODID);

    public static final DeferredBlock<RockBlock> ROCK_BLOCK = BLOCKS.registerBlock("rock_block", RockBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL).noOcclusion().sound(SoundType.STONE).instabreak());

    public static final DeferredItem<BlockItem> ROCK_BLOCK_ITEM = ModItems.ITEMS.registerSimpleBlockItem("rock_block", ROCK_BLOCK);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
