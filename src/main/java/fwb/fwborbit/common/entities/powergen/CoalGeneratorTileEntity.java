package fwb.fwborbit.common.entities.powergen;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import fwb.fwborbit.common.entities.OTileEntityTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CoalGeneratorTileEntity extends TileEntity {

    public CoalGeneratorTileEntity() {
        super(OTileEntityTypes.COAL_GENERATOR.get());
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {

    }
}
