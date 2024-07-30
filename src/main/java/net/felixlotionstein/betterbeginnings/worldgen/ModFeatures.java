package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.felixlotionstein.betterbeginnings.worldgen.RockFlowerFeature;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.felixlotionstein.betterbeginnings.worldgen.ModConfiguredFeatures.ROCK_BLOCK_KEY;
import static net.minecraft.data.worldgen.features.FeatureUtils.register;

public class ModFeatures {
        public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BetterBeginnings.MODID);

        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
            register(context, ROCK_BLOCK_KEY, FEATURES.register("rock_block", RockFlowerFeature::new));
        }
        }


}
