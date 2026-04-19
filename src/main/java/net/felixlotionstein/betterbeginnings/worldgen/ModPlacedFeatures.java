package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

import static net.felixlotionstein.betterbeginnings.worldgen.ModConfiguredFeatures.ROCK_BLOCK_KEY;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ROCK_BLOCK_PLACED_KEY = PlacementUtils.createKey("rock_block_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ROCK_BLOCK_PLACED_KEY, configuredFeatures.getOrThrow(ROCK_BLOCK_KEY),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.anyOf(
                                        BlockPredicate.matchesBlocks(new Vec3i(1, -1, 0), Blocks.WATER),
                                        BlockPredicate.matchesBlocks(new Vec3i(-1, -1, 0), Blocks.WATER),
                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 1), Blocks.WATER),
                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, -1), Blocks.WATER),
                                        BlockPredicate.matchesBlocks(new Vec3i(2, 0, 0), Blocks.STONE),
                                        BlockPredicate.matchesBlocks(new Vec3i(-2, 0, 0), Blocks.STONE),
                                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, 2), Blocks.STONE),
                                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, -2), Blocks.STONE)
                                )
                        )
                ));
    }
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(BetterBeginnings.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
