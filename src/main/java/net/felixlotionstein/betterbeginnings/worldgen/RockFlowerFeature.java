package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RockFlowerFeature extends Feature<NoneFeatureConfiguration> {

    public RockFlowerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockState rockBlockState = ModBlocks.ROCK_BLOCK.get().defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random));

        if (world.isEmptyBlock(origin)) {
            world.setBlock(origin, rockBlockState, 2);
            return true;
        }
        return false;
    }
}
