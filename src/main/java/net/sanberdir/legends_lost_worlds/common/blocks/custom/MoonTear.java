package net.sanberdir.legends_lost_worlds.common.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MoonTear extends RodBlock {
    protected static final float AABB_MIN = 2.0F;
    protected static final float AABB_MAX = 14.0F;
    protected static final VoxelShape Y_AXIS_AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);



    public VoxelShape getShape(BlockState p_154346_, BlockGetter p_154347_, BlockPos p_154348_, CollisionContext p_154349_) {
        switch (p_154346_.getValue(FACING).getAxis()) {
            case X:
            default:
                return X_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
            case Y:
                return Y_AXIS_AABB;
        }
    }

    public BlockState rotate(BlockState p_154354_, Rotation p_154355_) {
        return p_154354_.setValue(FACING, p_154355_.rotate(p_154354_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_154351_, Mirror p_154352_) {
        return p_154351_.setValue(FACING, p_154352_.mirror(p_154351_.getValue(FACING)));
    }

    public boolean isPathfindable(BlockState p_154341_, BlockGetter p_154342_, BlockPos p_154343_, PathComputationType p_154344_) {
        return false;
    }
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public MoonTear(Properties p_154339_) {
        super(p_154339_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));

    }

    public BlockState getStateForPlacement(BlockPlaceContext p_53087_) {
        Direction direction = p_53087_.getClickedFace();
        BlockState blockstate = p_53087_.getLevel().getBlockState(p_53087_.getClickedPos().relative(direction.getOpposite()));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53105_) {
        p_53105_.add(FACING);
    }

    public PushReaction getPistonPushReaction(BlockState p_53112_) {
        return PushReaction.NORMAL;
    }
}