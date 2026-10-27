package net.felixlotionstein.betterbeginnings.loot;

import com.mojang.serialization.MapCodec;
import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, BetterBeginnings.MODID);

    static {
        LOOT_MODIFIERS.register("leaf_sticks", () -> LeafStickModifier.CODEC);
    }

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }
}
