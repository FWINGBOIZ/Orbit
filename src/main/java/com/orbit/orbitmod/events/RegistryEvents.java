package com.orbit.orbitmod.events;

import com.orbit.orbitmod.Main;
import com.orbit.orbitmod.lists.ItemList;

import org.apache.logging.log4j.Logger;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    public static final Logger LOGGER = Main.LOGGER;
    public static final String MOD_ID = Main.MOD_ID;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
           ItemList.tutorial_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("tutorial_item")) 
        );
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

}
