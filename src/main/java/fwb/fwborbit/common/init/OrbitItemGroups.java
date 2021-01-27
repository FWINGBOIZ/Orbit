package fwb.fwborbit.common.init;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import fwb.fwborbit.common.Orbit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public final class OrbitItemGroups {

    /* Use this when you need an item group for something */
    public static final ItemGroup ORBIT_ITEM_GROUP = new OrbitItemGroup(Orbit.MOD_ID, () -> new ItemStack(OrbitItems.orbit_stick));

    public static class OrbitItemGroup extends ItemGroup {

        /* Below here is functional programming stuff involving suppliers. I don't know, look it up. */
        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        public OrbitItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        @Nonnull
        public ItemStack createIcon() {
            return iconSupplier.get();
        }
    }

}