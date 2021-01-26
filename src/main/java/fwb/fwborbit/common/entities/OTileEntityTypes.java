package fwb.fwborbit.common.entities;

import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.blocks.OBlockTypes;
import fwb.fwborbit.common.entities.powergen.CoalGeneratorTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * TODO: Switch from using DeferredRegister to Register as it is more flexible.
 * Currently using DeferredRegister as a POC
 */
public final class OTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Orbit.MOD_ID);

    public static final RegistryObject<TileEntityType<CoalGeneratorTileEntity>> COAL_GENERATOR = TILE_ENTITY_TYPES.register("coal_generator", () ->
            TileEntityType.Builder.create(CoalGeneratorTileEntity::new, OBlockTypes.COAL_GENERATOR.get()).build(null)
    );
}