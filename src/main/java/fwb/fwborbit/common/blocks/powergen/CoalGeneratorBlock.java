package fwb.fwborbit.common.blocks.powergen;

import fwb.fwborbit.common.entities.powergen.CoalGeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Locale;

public class CoalGeneratorBlock extends HorizontalBlock {

    public CoalGeneratorBlock(final Properties properties) {
        super(properties);
        // Set the default values for our blockstate properties
        this.setDefaultState(this.getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CoalGeneratorTileEntity();
    }

    /**
     * Called on the logical server when a BlockState with a TileEntity is replaced by another BlockState.
     * We use this method to drop all the items from our tile entity's inventory and update comparators near our block.
     */
    @Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CoalGeneratorTileEntity) {
                final ItemStackHandler inventory = ((CoalGeneratorTileEntity) tileEntity).inventory;
                for (int slot = 0 ; slot < inventory.getSlots(); ++slot) {
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
                }
            }
        }
        super.onReplaced(oldState, worldIn, pos, newState, isMoving);
    }

    /**
     * Called when a player right clicks our block.
     * We use this method to open our gui.
     */
    @Override
    public ActionResultType onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof CoalGeneratorTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (CoalGeneratorTileEntity) tileEntity, pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    /**
     * Makes the block face the player when placed
     */
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    /**
     * Called from inside the constructor {@link Block#Block(Properties)} to add all the properties to our blockstate
     */
    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
    }
}