package fwb.fwborbit.common.gui;

import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.gui.powergen.CoalGeneratorContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * TODO: Switch from using DeferredRegister to Register as it is more flexible.
 * Currently using DeferredRegister as a POC
 */
public final class OContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Orbit.MOD_ID);

    public static final RegistryObject<ContainerType<CoalGeneratorContainer>> COAL_GENERATOR = CONTAINER_TYPES.register("coal_generator", () ->
            IForgeContainerType.create(CoalGeneratorContainer::new)
    );
}
