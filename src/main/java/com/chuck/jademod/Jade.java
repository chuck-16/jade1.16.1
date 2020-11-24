package com.chuck.jademod;

import com.chuck.jademod.client.gui.FletchingScreen;
import com.chuck.jademod.entities.EntityJadeArrow;
import com.chuck.jademod.render.entities.JadeArrowRenderer;
import com.chuck.jademod.util.RegisteryHandler;
import com.chuck.jademod.util.StartupCommon;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.chuck.jademod.util.RegisteryHandler.JADE_CRYSTAL_BLOCK;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("jade")
public class Jade
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "jade";

    public Jade() {

        RegisteryHandler.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(StartupCommon.class);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> Jade::registerClientOnlyEvents);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        RenderTypeLookup.setRenderLayer(JADE_CRYSTAL_BLOCK.get(), RenderType.getCutout());
        RenderingRegistry.registerEntityRenderingHandler(StartupCommon.jadeArrow, new jadeArrowRenderFactory());
        ScreenManager.registerFactory(StartupCommon.fletchingContainerType, FletchingScreen::new);
    }

    public static void registerClientOnlyEvents(){
        FMLJavaModLoadingContext.get().getModEventBus().register(StartupCommon.class);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    private static class jadeArrowRenderFactory implements IRenderFactory<EntityJadeArrow> {
        @Override
        public EntityRenderer<? super EntityJadeArrow> createRenderFor(EntityRendererManager manager) {
            return new JadeArrowRenderer(manager);
        }
    }


}
