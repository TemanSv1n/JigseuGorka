package net.svisvi.jigseugorka.block;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class RedUpperSetBlock extends Block {
    public RedUpperSetBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(-1, 3600000));
        this.registerDefaultState(this.stateDefinition.any().setValue(POWER, Integer.valueOf(0)));
    }
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52398_) {
        p_52398_.add(POWER);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public static void updateSignal(BlockState blockstate, LevelAccessor world, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        //return (int) UpperSetEmittedRedstonePowerProcedure.execute(world, x, y, z);
        int ret = getDownSetState(pos, world);
        if (ret == -1){
            world.setBlock(pos, blockstate.setValue(POWER, Integer.valueOf(0)), 3);

        }
        world.setBlock(pos, blockstate.setValue(POWER, Integer.valueOf(ret)), 3);
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
        return ret;
    }
    public static int getDownSetState(BlockPos pos, LevelAccessor world){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int ds = (world.getBlockState(new BlockPos(x, y - 1, z))).getBlock().getStateDefinition().getProperty("level")
                instanceof IntegerProperty _getip1 ? (world.getBlockState(new BlockPos(x, y - 1, z))).getValue(_getip1) : -1;
        int ret;
        if (ds == 0) {ret = -1;}
        else if (ds == 1){ret = 15;}
        else if (ds == 2){ret = 0;}
        else {ret = -1;}
        return ret;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
        //System.out.println("tick");
        //System.out.println(JigseuGorkaModVariables.MapVariables.get(world).Spot_1);
        super.tick(blockstate, world, pos, random);
        world.scheduleTick(pos, this, 2);
    }
    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 15);
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
