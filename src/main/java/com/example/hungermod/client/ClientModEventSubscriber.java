package com.example.hungermod.client;

import com.example.hungermod.HungerMod;
import com.example.hungermod.client.render.entity.MockingJayRenderer;
import com.example.hungermod.client.render.entity.TrackerJackerRenderer;
import com.example.hungermod.client.render.entity.TurkeyRenderer;
import com.example.hungermod.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = HungerMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(HungerMod.MODID + " Client Mod Event Subscriber");

    /**
     * We need to register our renderers on the client because rendering code does not exist on the server
     * and trying to use it on a dedicated server will crash the game.
     * <p>
     * This method will be called by Forge when it is time for the mod to do its client-side setup
     * This method will always be called after the Registry events.
     * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
     */
    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {


        // Register Entity Renderers
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TRACKER_JACKER.get(), TrackerJackerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.TURKEY.get(), TurkeyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MOCKING_JAY.get(), MockingJayRenderer::new);
        LOGGER.debug("Registered Entity Renderers");

    }

}
