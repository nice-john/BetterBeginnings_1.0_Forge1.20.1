package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.felixlotionstein.betterbeginnings.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BetterBeginnings.MODID);

    public static final RegistryObject<Feature<BlockStateConfiguration>> ROCK_FEATURE = FEATURES.register("rock_feature",
            () -> new Feature<>(BlockStateConfiguration.CODEC) {
                @Override
                public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
                    BlockPos pos = context.origin();
                    LevelAccessor world = context.level();
                    if (world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) {
                        return world.setBlock(pos, ModBlocks.ROCK_BLOCK.get().defaultBlockState(), 2);
                    }
                    return false;
                }
            });

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_BLOCK_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BetterBeginnings.MODID, "rock_block"));

    public static Holder.Reference<ConfiguredFeature<?, ?>> ROCK_CONFIGURED_FEATURE;

    public static void registerConfiguredFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {
        ROCK_CONFIGURED_FEATURE = context.register(ROCK_BLOCK_KEY, new ConfiguredFeature<>(ROCK_FEATURE.get(), new BlockStateConfiguration(ModBlocks.ROCK_BLOCK.get().defaultBlockState())));
    }
}
