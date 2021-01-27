package fwb.fwborbit.common.blocks;

import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.blocks.powergen.CoalGeneratorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * TODO: Switch from using DeferredRegister to Register as it is more flexible.
 * Currently using DeferredRegister as a POC
 */
public final class OBlockTypes {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Orbit.MOD_ID);
    //public static final DeferredRegister<BlockItem> BLOCK_ITEMS = new DeferredRegister<BlockItem>(ForgeRegistries.BLOCKS, Orbit.MOD_ID)

    public static final RegistryObject<Block> COAL_GENERATOR =  BLOCKS.register("coal_generator", () ->
            new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))
    );
}
