package net.svisvi.jigseugorka.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlueUpperSetBlock extends RedUpperSetBlock {
    public BlueUpperSetBlock() {
        super();
    }

    @Override
    public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        Level world = (Level) blockAccess;
        //return (int) UpperSetEmittedRedstonePowerProcedure.execute(world, x, y, z);
        int ret = getDownSetState(pos, world);
        if (ret == -1){
            return 0;

        }
        return Math.abs(ret-15);
    }




//    public BlockEntity newBlockEntity(BlockPos p_153118_, BlockState p_153119_) {
//        return new DaylightDetectorBlockEntity(p_153118_, p_153119_);
//    }

//    @Nullable
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153109_, BlockState p_153110_, BlockEntityType<T> p_153111_) {
//        return !p_153109_.isClientSide && p_153109_.dimensionType().hasSkyLight() ? createTickerHelper(p_153111_, BlockEntityType.DAYLIGHT_DETECTOR, RedUpperSetBlock::tickEntity) : null;
//    }
//
//    private static void tickEntity(Level p_153113_, BlockPos p_153114_, BlockState p_153115_, DaylightDetectorBlockEntity p_153116_) {
//        if (p_153113_.getGameTime() % 20L == 0L) {
//            updateSignal(p_153115_, p_153113_, p_153114_);
//        }
//
//    }

}
