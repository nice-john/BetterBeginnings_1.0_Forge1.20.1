package net.felixlotionstein.betterbeginnings;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec.BooleanValue SEND_MESSAGES;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.comment("Gameplay settings").push("gameplay");

        SEND_MESSAGES = COMMON_BUILDER
                .comment("Set to false if you don't want the mod to send messages to the player.")
                .define("sendMessages", true);

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
