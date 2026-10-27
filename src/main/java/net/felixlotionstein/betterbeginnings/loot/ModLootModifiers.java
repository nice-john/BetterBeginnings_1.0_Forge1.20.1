package net.felixlotionstein.betterbeginnings.loot;

import com.mojang.serialization.Codec;
import net.felixlotionstein.betterbeginnings.BetterBeginnings;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, BetterBeginnings.MODID);

    static {
        LOOT_MODIFIERS.register("leaf_sticks", LeafStickModifier.CODEC);
    }

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }
}
