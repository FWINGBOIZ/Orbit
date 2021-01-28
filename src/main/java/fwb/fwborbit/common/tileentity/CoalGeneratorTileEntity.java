package fwb.fwborbit.common.tileentity;

import javax.annotation.Nonnull;

import fwb.fwborbit.common.Registration;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class CoalGeneratorTileEntity extends TileEntity {

    public static final int FUEL_SLOT=0;

    public CoalGeneratorTileEntity() {
        super(Registration.COAL_GENERATOR_TILE.get());
    }

    public ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            // return !stack.isEmpty() && CoalGeneratorTileEntity.isFuel(stack);
            return true;
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            CoalGeneratorTileEntity.this.markDirty();
        }
    };
}