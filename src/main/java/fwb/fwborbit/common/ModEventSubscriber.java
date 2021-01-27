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

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
           setup(new Item(new Item.Properties().group(OrbitItemGroups.MOD_ITEM_GROUP)), "orbit_stick")
       );
    }

    public static <T extends IForgeRegistryEntry<T>> T setup (final T entry, final String name) {
        return setup(entry, new ResourceLocation(Orbit.MOD_ID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}
