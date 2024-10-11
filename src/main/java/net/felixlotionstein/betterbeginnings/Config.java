package net.felixlotionstein.betterbeginnings;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.BooleanValue SEND_MESSAGES;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        // Configuration category: Gameplay
        COMMON_BUILDER.comment("Gameplay settings").push("gameplay");

        // Config for sending messages
        SEND_MESSAGES = COMMON_BUILDER
                .comment("Set to false if you don't want the mod to send messages to the player.")
                .define("sendMessages", true);

        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}

