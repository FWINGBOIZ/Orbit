package fwb.fwborbit.common.containers;

import com.sun.org.apache.xpath.internal.operations.Or;
import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.Registration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CoalGeneratorContainer extends Container {
    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public CoalGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(Registration.COAL_GENERATOR_CONTAINER.get(), windowId);
        Orbit.LOGGER.debug("Initializing container");
        tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0,64, 124));
            });
        }
        layoutGui(8, 84, 80, 59);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, Registration.COAL_GENERATOR.get());
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private void layoutCoalGeneratorSlots(int leftCol, int topRow) {
        // Coal generator input slot
        addSlotBox(playerInventory, 36, leftCol, topRow, 1, 18, 1, 18);
    }

    private void layoutGui(int invLeftCol, int invTopRow, int cgLeftCol, int cgTopRow) {
        layoutPlayerInventorySlots(invLeftCol, invTopRow);
        layoutCoalGeneratorSlots(cgLeftCol, cgTopRow);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        Orbit.LOGGER.debug("Index is " + index);
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemStack = stack.copy();
            if (index == 0) {
                Orbit.LOGGER.debug("Index == 0");
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    Orbit.LOGGER.debug("ItemStack Empty");
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemStack);
            }
            else {
                if (index < 28) {
                    Orbit.LOGGER.debug("Index < 28 ");
                    if (!this.mergeItemStack(stack, 27, 37, false)) {
                        Orbit.LOGGER.debug("ItemStack Empty");
                        return ItemStack.EMPTY;
                    }
                }
                else if (index < 37 && !this.mergeItemStack(stack, 0, 28, false)) {
                    Orbit.LOGGER.debug("Index < 37 and ItemStack Empty");
                    return ItemStack.EMPTY;
                }
            }
            if (stack.isEmpty()) {
                Orbit.LOGGER.debug("Stack is empty");
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                Orbit.LOGGER.debug("Stack changed");
                slot.onSlotChanged();
            }
            if (stack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stack);
        }
        return itemStack;
    }
}
