package fwb.fwborbit.common.entities.powergen;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import fwb.fwborbit.common.blocks.OBlockTypes;
import fwb.fwborbit.common.entities.OTileEntityTypes;
import fwb.fwborbit.common.gui.powergen.CoalGeneratorContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.Optional;

public class CoalGeneratorTileEntity extends TileEntity implements INamedContainerProvider {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public final ItemStackHandler inventory = new ItemStackHandler(2) {
        @Override
        public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
            switch (slot) {
                case INPUT_SLOT:
                    return isInput(stack);
                case OUTPUT_SLOT:
                    return isOutput(stack);
                default:
                    return false;
            }
        }

        @Override
        protected void onContentsChanged(final int slot) {
            super.onContentsChanged(slot);
            // Mark the tile entity as having changed whenever its inventory changes.
            // "markDirty" tells vanilla that the chunk containing the tile entity has
            // changed and means the game will save the chunk to disk later.
            CoalGeneratorTileEntity.this.markDirty();
        }
    };

    public CoalGeneratorTileEntity() {
        super(OTileEntityTypes.COAL_GENERATOR.get());
    }

    @SubscribeEvent
    public static void registerTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {

    }

    /**
     * @return If the stack is not empty and has a smelting recipe associated with it
     */
    private boolean isInput(final ItemStack stack) {
        return true;
    }

    /**
     * @return If the stack's item is equal to the result of smelting our input
     */
    private boolean isOutput(final ItemStack stack) {
        return true;
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(OBlockTypes.COAL_GENERATOR.get().getTranslationKey());
    }

    /**
     * Called from {@link NetworkHooks#openGui}
     * (which is called from {@link ElectricFurnaceBlock#onBlockActivated} on the logical server)
     *
     * @return The logical-server-side Container for this TileEntity
     */
    @Nonnull
    @Override
    public Container createMenu(final int windowId, final PlayerInventory inventory, final PlayerEntity player) {
        return new CoalGeneratorContainer(windowId, inventory, this);
    }
}
