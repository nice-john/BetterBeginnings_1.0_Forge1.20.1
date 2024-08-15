package net.felixlotionstein.betterbeginnings.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RockBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // Define the VoxelShape for the block in each direction
    private static final VoxelShape SHAPE_NORTH = Block.box(8, 0, 4, 14, 4, 10);
    private static final VoxelShape SHAPE_EAST = Block.box(4, 0, 2, 10, 4, 8);
    private static final VoxelShape SHAPE_SOUTH = Block.box(2, 0, 6, 8, 4, 12);
    private static final VoxelShape SHAPE_WEST = Block.box(6, 0, 8, 12, 4, 14);
// goood north (8, 0, 4, 14, 4, 10);
    /*
        private static final VoxelShape SHAPE_NORTH = Block.box(8, 0, 4, 14, 4, 10);
    private static final VoxelShape SHAPE_EAST = Block.box(4, 0, 2, 10, 4, 8);
    private static final VoxelShape SHAPE_SOUTH = Block.box(2, 0, 6, 8, 4, 12);
    private static final VoxelShape SHAPE_WEST = Block.box(6, 0, 8, 12, 4, 14);
     */
    public RockBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
            case NORTH:
            default:
                return SHAPE_NORTH;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockState blockBelow = world.getBlockState(pos.below());
        return blockBelow.is(Blocks.GRASS_BLOCK) || blockBelow.is(Blocks.STONE) || blockBelow.is(Blocks.GRAVEL);
    }
}