package net.felixlotionstein.betterbeginnings.worldgen;

import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, BetterBeginnings.MODID);
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ROCK_FEATURE = FEATURES.register("rock_block",
            () -> new RockFlowerFeature());

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
