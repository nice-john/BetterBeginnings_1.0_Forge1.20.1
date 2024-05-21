package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ROCK_BLOCK_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BetterBeginnings.MODID, "rock_block_placed"));

    public static Holder<PlacedFeature> ROCK_PLACED_FEATURE;

    public static void registerPlacedFeatures(BootstapContext<PlacedFeature> context) {
        ROCK_PLACED_FEATURE = context.register(ROCK_BLOCK_PLACED_KEY, new PlacedFeature(
                ModConfiguredFeatures.ROCK_CONFIGURED_FEATURE,
                List.of(
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                        BiomeFilter.biome()
                )
        ));
    }
}
