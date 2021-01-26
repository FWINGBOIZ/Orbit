package fwb.fwborbit.common.gui.powergen;

import fwb.fwborbit.common.blocks.OBlockTypes;
import fwb.fwborbit.common.entities.powergen.CoalGeneratorTileEntity;
import fwb.fwborbit.common.gui.OContainerTypes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CoalGeneratorContainer extends Container {
    public final CoalGeneratorTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    /**
     * Logical client side constructor
     * Calls the logical server-side constructor with the TileEntity at the pos in the PacketBuffer
     * @param windowId
     * @param playerInventory
     * @param data
     */
    public CoalGeneratorContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    /**
     * Constructor called logical server-side and logical client-side from {@link #CoalGeneratorContainer(int, PlayerInventory, PacketBuffer)}
     * @param windowId
     * @param playerInventory
     * @param tileEntity
     */
    public CoalGeneratorContainer(final int windowId, final PlayerInventory playerInventory, final CoalGeneratorTileEntity tileEntity) {
        super(OContainerTypes.COAL_GENERATOR.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
    }

    /**
     * Checks if block clicked on by player is a Coal Generator
     * @param playerInventory
     * @param data
     * @return position of coal generator
     */
    private static CoalGeneratorTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof CoalGeneratorTileEntity)
            return (CoalGeneratorTileEntity) tileAtPos;
        throw new IllegalStateException("Tile entity is not correct! Tile position: " + tileAtPos);
    }

    @Override
    public boolean canInteractWith(@Nonnull final PlayerEntity player) {
        return isWithinUsableDistance(canInteractWithCallable, player, OBlockTypes.COAL_GENERATOR.get());
    }
}
