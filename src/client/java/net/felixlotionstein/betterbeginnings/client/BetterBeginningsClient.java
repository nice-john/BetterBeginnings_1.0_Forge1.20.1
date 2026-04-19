package net.felixlotionstein.betterbeginnings.client;

import net.fabricmc.api.ClientModInitializer;
import net.felixlotionstein.betterbeginnings.BetterBeginnings;

public class BetterBeginningsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BetterBeginnings.LOGGER.info("Better Beginnings client initialized");
    }
}
