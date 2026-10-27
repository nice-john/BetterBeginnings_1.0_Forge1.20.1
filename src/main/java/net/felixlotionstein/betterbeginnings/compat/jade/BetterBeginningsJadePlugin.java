package net.felixlotionstein.betterbeginnings.compat.jade;

import net.felixlotionstein.betterbeginnings.item.ModItems;
import net.minecraft.resources.Identifier;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.harvest.ToolTier;

/**
 * Jade's harvest hint shows the first tool tier that can harvest the block. Vanilla's
 * copper tools are already in Jade's lists; this adds the stone hatchet ahead of the
 * stone axe so logs show it. Registered as the "jade" entrypoint in fabric.mod.json,
 * so it only loads when Jade is installed.
 */
public class BetterBeginningsJadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.addHarvestPlugin(registry -> registry.insertTierBefore(
                Identifier.fromNamespaceAndPath("jade", "axe"),
                Identifier.withDefaultNamespace("stone_axe"),
                ToolTier.item(ModItems.STONE_HATCHET)));
    }
}
