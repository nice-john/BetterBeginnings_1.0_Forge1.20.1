package net.felixlotionstein.betterbeginnings.worldgen;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;

import java.util.List;

public class ModVegetationPlacement {
    public static List<PlacementModifier> commonSurfacePlacement(int rarity, PlacementModifier heightRange) {
        return List.of(RarityFilter.onAverageOnceEvery(rarity), InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }
}
