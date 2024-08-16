package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import net.minecraft.core.Holder;
import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.felixlotionstein.betterbeginnings.worldgen.ModConfiguredFeatures;

import java.util.List;

import static net.felixlotionstein.betterbeginnings.worldgen.ModConfiguredFeatures.ROCK_BLOCK_KEY;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ROCK_BLOCK_PLACED_KEY = PlacementUtils.createKey("rock_block_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ROCK_BLOCK_PLACED_KEY, configuredFeatures.getOrThrow(ROCK_BLOCK_KEY),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.anyOf(
                                        // Check if any adjacent blocks are WATER or STONE
                                        BlockPredicate.matchesBlocks(new Vec3i(1, -1, 0), Blocks.WATER), // EAST
                                        BlockPredicate.matchesBlocks(new Vec3i(-1, -1, 0), Blocks.WATER), // WEST
                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 1), Blocks.WATER), // SOUTH
                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, -1), Blocks.WATER), // NORTH
                                        BlockPredicate.matchesBlocks(new Vec3i(2, 0, 0), Blocks.STONE), // EAST
                                        BlockPredicate.matchesBlocks(new Vec3i(-2, 0, 0), Blocks.STONE), // WEST
                                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, 2), Blocks.STONE), // SOUTH
                                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, -2), Blocks.STONE) // NORTH
                                )
                        )
                ));
    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BetterBeginnings.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}