package fwb.fwborbit.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;

@Mod(Orbit.MOD_ID)
public final class Orbit
{
    public static final String MOD_ID = "fwborbit";
    public static final Logger LOGGER = LogManager.getLogger();

    public Orbit() {
        LOGGER.debug("Orbit mod loaded");
        Registration.init();
    }
}