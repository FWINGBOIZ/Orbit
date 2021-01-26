package fwb.fwborbit.common.events;

import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.items.OItemTypes;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    public static final Logger LOGGER = Orbit.LOGGER;
    public static final String MOD_ID = Orbit.MOD_ID;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
           OItemTypes.tutorial_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("tutorial_item")),
           OItemTypes.coal_generator_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("coal_generator_item"))
        );
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(

        );
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

}
