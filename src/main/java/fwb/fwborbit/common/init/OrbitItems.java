package fwb.fwborbit.common.init;

import fwb.fwborbit.common.Orbit;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

/**
 * This is a magic thing. (The ObjectHolder)
 * If an item is registered under our mod's registry,
 * and it has the same name as a public static final
 * attribute on this class, the item will be injected here
 * replacing the null value.
* */
@ObjectHolder(Orbit.MOD_ID)
public final class OrbitItems {

   public static final Item orbit_stick = null;
}
