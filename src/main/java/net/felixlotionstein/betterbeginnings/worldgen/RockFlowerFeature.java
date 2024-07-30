package net.felixlotionstein.betterbeginnings.worldgen;

import java.util.Map;
import java.util.function.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class RockFlowerFeature extends Feature<NoneFeatureConfiguration>
{
    private static final Supplier<Map<Block, Supplier<? extends Block>>> ROCK_FLOWER_LOOKUP = Suppliers.memoize(() -> new ImmutableMap.Builder<Block, Supplier<? extends Block>>()
            .put(Blocks.STONE, ModBlocks.ROCK_BLOCK)
            .put(Blocks.GRAVEL, ModBlocks.ROCK_BLOCK)
            .build()
    );

    public RockFlowerFeature(Codec<SimpleBlockConfiguration> codec)
    {
        super(NoneFeatureConfiguration.CODEC);
    }


    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        final WorldGenLevel level = context.level();
        final BlockPos pos = context.origin();

        final BlockState stateAt = level.getBlockState(pos);
        final BlockState stateDown = level.getBlockState(pos.below());
        if (stateAt.isAir() && stateDown.is(Blocks.STONE))
        {
            for (int y = 1; y <= 8; y++)
            {
                final BlockPos stonePos = pos.below(y);
                final BlockState stoneState = level.getBlockState(stonePos);
                if (ROCK_FLOWER_LOOKUP.get().containsKey(stoneState.getBlock()))
                {
                    final Block looseRockBlock = ROCK_FLOWER_LOOKUP.get().get(stoneState.getBlock()).get();
                    level.setBlock(pos, looseRockBlock.defaultBlockState(), 3);
                    return true;
                }
            }
        }
        return false;
    }
}