package net.svisvi.jigseugorka.item;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class AnnulatorItem extends Item {
    public AnnulatorItem() {
        super(new Item.Properties().tab(null).stacksTo(64).rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        BlockPos pos = context.getClickedPos();
        BlockState bs = context.getPlayer().level.getBlockState(pos);
        bs.use(context.getPlayer().level, context.getPlayer(), context.getHand(), BlockHitResult.miss(new Vec3(pos.getX(), pos.getY(), pos.getZ()), Direction.UP, pos));
        return InteractionResult.SUCCESS;
    }
}
