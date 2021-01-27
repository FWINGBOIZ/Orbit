package fwb.fwborbit.common;

import fwb.fwborbit.common.init.OrbitBlocks;
import fwb.fwborbit.common.init.OrbitItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.Logger;


@EventBusSubscriber(modid=Orbit.MOD_ID, bus=EventBusSubscriber.Bus.MOD)
public class OrbitEventSubscriber {
    public static final Logger LOGGER = Orbit.LOGGER;
    /**
     * This is an event listener for the RegisterItems event.
     * When the event is received it registers all items in the call to registerAll.
     * When registering a new item to the mod, it has to be here or won't register.
     * @param event
     * @see fwb.fwborbit.common.init.OrbitItems
     */
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            setup(new Item(new Item.Properties().group(OrbitItemGroups.ORBIT_ITEM_GROUP)), "orbit_stick"),
            setup(new BlockItem(OrbitBlocks.coal_generator, new Item.Properties().group(OrbitItemGroups.ORBIT_ITEM_GROUP)), OrbitBlocks.coal_generator.getRegistryName())
        );
        LOGGER.debug("Registered Items");
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.GRAY)), "coal_generator")
        );
        LOGGER.debug("Registered Blocks");
    }

    @SubscribeEvent
    public static void onRegisterBlockItems(RegistryEvent.Register<Item> event) {}

    /**
     * Overloads the setup method to allow a string Name as input.
     * Uses the Orbit Registry as default when used this way.
     * Calls setup and returns the entry arg with modified registry name it passes back.
     *
     * @param <T>   The generic type you are registering for (Eg. Item)
     * @param entry An object (with properties) of the generic type you are registering
     * @param name  The registry name of the object
     * @return      An object of the generic type you are registering
     */
    public static <T extends IForgeRegistryEntry<T>> T setup (final T entry, final String name) {
        return setup(entry, new ResourceLocation(Orbit.MOD_ID, name));
    }

    /**
     * Sets the registry name on the entry param and returns the entry.
     * Overloaded to allow a String resource name instead of registryName param.
     * Returns the entry arg after the registry name has been set.
     *
     * @param <T>   The generic type you are registering for (Eg. Item)
     * @param entry An object (with properties) of the generic type you are registering
     * @param registryName A ResourceLocation of your registry and a name
     * @return An object of the generic type you are registering
     * @see net.minecraft.util.ResourceLocation
     */
    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}
