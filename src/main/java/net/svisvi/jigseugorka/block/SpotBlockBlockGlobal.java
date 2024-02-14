
package net.svisvi.jigseugorka.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;

public class SpotBlockBlockGlobal extends SpotBlockBlock{
	public String name() {return"Общий счетчик";}
	//public String state = "green"; //-1 blue --0 green-- 1 red


	public double spot(LevelAccessor world){
		return JigseuGorkaModVariables.MapVariables.get(world).Points;
	}
	public void spotModify(LevelAccessor world, int calc){
		JigseuGorkaModVariables.MapVariables.get(world).Points += calc;

	}
	public void scoreAnnul(LevelAccessor world){
		JigseuGorkaModVariables.MapVariables.get(world).Points = 0;
		JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
	}

	@Override
	public void broadcast(LevelAccessor world, BlockPos blockPos, String color_code) {
	}

	public SpotBlockBlockGlobal() {
		super();
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));

	}
}
