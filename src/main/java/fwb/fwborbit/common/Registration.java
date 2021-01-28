package fwb.fwborbit.common;

import fwb.fwborbit.common.blocks.powergen.CoalGeneratorBlock;
import fwb.fwborbit.common.tileentity.CoalGeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Orbit.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Orbit.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Orbit.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> ORBIT_STICK = ITEMS.register("orbit_stick", () -> new Item(new Item.Properties().group(OrbitItemGroups.ORBIT_ITEM_GROUP)));

    public static final RegistryObject<Block> COAL_GENERATOR = BLOCKS.register("coal_generator",
            () -> new CoalGeneratorBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY)));
    public static final RegistryObject<Item> COAL_GENERATOR_ITEM = ITEMS.register("coal_generator",
            () -> new BlockItem(COAL_GENERATOR.get(), new Item.Properties().group(OrbitItemGroups.ORBIT_ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<CoalGeneratorTileEntity>> COAL_GENERATOR_TILE = TILES.register("coal_generator",
            () -> TileEntityType.Builder.create(CoalGeneratorTileEntity::new, COAL_GENERATOR.get()).build(null));
}
