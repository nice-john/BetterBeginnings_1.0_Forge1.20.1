package net.felixlotionstein.betterbeginnings.worldgen;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import java.util.Map;
import java.util.function.Supplier;
import com.google.common.collect.ImmutableMap;

public class RockFlowerFeature extends Feature<NoneFeatureConfiguration> {
    private static final Supplier<Map<Block, Supplier<? extends Block>>> ROCK_FLOWER_LOOKUP = Suppliers.memoize(() ->
            new ImmutableMap.Builder<Block, Supplier<? extends Block>>()
                    .put(Blocks.STONE, ModBlocks.ROCK_BLOCK)
                    .put(Blocks.GRAVEL, ModBlocks.ROCK_BLOCK)
                    .build()
    );

    public RockFlowerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldGenLevel = context.level();
        BlockPos blockPos = context.origin();
        BlockState blockState = ModBlocks.ROCK_BLOCK.get().defaultBlockState();

        if (blockState.canSurvive(worldGenLevel, blockPos)) {
            worldGenLevel.setBlock(blockPos, blockState, 3);
            return true;
        }
        return false;
    }


}
