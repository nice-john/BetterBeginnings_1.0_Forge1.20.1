package net.felixlotionstein.betterbeginnings;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraft.server.ReloadableServerResources;

@Mod.EventBusSubscriber(modid = "betterbeginnings", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RecipeEventHandler implements IConditionBuilder {

    @SubscribeEvent
    public static void onAddReloadListener(AddReloadListenerEvent event) {
        event.addListener(new SimplePreparableReloadListener<Object>() {
            @Override
            protected Object prepare(net.minecraft.server.packs.resources.ResourceManager pResourceManager, net.minecraft.server.packs.resources.PreparableReloadListener.PreparationBarrier pPreparationBarrier, net.minecraft.server.ReloadableServerResources pReloadableServerResources) {
                return null; // No need for any preparation
            }

            @Override
            protected void apply(Object pObject, net.minecraft.server.packs.resources.ResourceManager pResourceManager, net.minecraft.server.ReloadableServerResources pReloadableServerResources) {
                // Access the Recipe Manager
                RecipeManager recipeManager = pReloadableServerResources.getRecipeManager();

                // Check if the config allows loading the recipe
                if (Config.SEND_MESSAGES.get()) {
                    // If the config is true, we do nothing (recipes load as usual)
                    return;
                } else {
                    // If the config is false, we need to remove or hide the specific recipe(s)
                    ResourceLocation recipeToRemove = new ResourceLocation("yourmodid", "my_custom_recipe");
                    recipeManager.byName(recipeToRemove).ifPresent(recipe -> {
                        recipeManager.getRecipes().remove(recipeToRemove);
                    });
                }
            }
        });
    }
}
