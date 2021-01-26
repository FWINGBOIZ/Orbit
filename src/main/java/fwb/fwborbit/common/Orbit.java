package fwb.fwborbit.common;

import fwb.fwborbit.common.blocks.OBlockTypes;
import fwb.fwborbit.common.items.OItemTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Orbit.MOD_ID)
public class Orbit 
{
    public static Orbit instance;
    public static final String MOD_ID = "fwborbit";
    public static final Logger LOGGER = LogManager.getLogger();

    public Orbit() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        LOGGER.debug("Hello from Example Mod!");

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register Deferred Registers (Does not need to be before Configs)
        OBlockTypes.BLOCKS.register(modEventBus);
        //OItemTypes.ITEMS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event2) {

    }

    public void onServerStarting(FMLServerStartingEvent event) {

    }
}
