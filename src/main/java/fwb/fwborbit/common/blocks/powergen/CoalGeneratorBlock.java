package fwb.fwborbit.common.blocks.powergen;

import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.init.OrbitTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.apache.logging.log4j.Logger;

public class CoalGeneratorBlock extends HorizontalBlock {

    public static final Logger LOGGER = Orbit.LOGGER;
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public CoalGeneratorBlock(final Properties properties) {
        super(properties);
        // Set the default values for our blockstate properties
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    /**
     * Makes the block face the player when placed
     */
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    /**
     * Called from inside the constructor {@link Block#Block(Properties)} to add all the properties to our blockstate
     */
    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        /* In general, we probably only want to do things on the server side here.
         * This block basically skips the client consuming the action.
         * */
        if (worldIn.isRemote()) {
            return ActionResultType.SUCCESS;
        }

        LOGGER.debug("You clicked on the coal_generator!");
        return ActionResultType.CONSUME;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return OrbitTileEntityTypes.coal_generator.create();
    }

}
