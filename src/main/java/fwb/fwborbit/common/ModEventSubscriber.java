package fwb.fwborbit.common;

import fwb.fwborbit.common.init.OrbitItemGroups;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid=Orbit.MOD_ID, bus=EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    /**
     * This is an event listener for the RegisterItems event.
     * When the event is recieved it registers all items in the call to registerAll.
     * When registering a new item to the mod, it has to be here or won't rgister.
     * @param event
     * @see OrbitItems
     */
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
           setup(new Item(new Item.Properties().group(OrbitItemGroups.MOD_ITEM_GROUP)), "orbit_stick")
       );
    }

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
