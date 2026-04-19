package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import static net.minecraft.core.registries.Registries.CONFIGURED_FEATURE;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_BLOCK_KEY = registerKey("rock_block");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, ROCK_BLOCK_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(64, 5, 11, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                .add(ModBlocks.ROCK_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH), 1)
                                .add(ModBlocks.ROCK_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH), 1)
                                .add(ModBlocks.ROCK_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST), 1)
                                .add(ModBlocks.ROCK_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST), 1)
                                .build())))));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(BetterBeginnings.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
