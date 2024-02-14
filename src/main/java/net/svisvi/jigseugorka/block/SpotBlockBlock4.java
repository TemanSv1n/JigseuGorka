
package net.svisvi.jigseugorka.block;

import net.minecraft.world.level.LevelAccessor;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;

public class SpotBlockBlock4 extends SpotBlockBlock{
	public String name() {return"Церковь Летова";}
	//public String state = "green"; //-1 blue --0 green-- 1 red


	public double spot(LevelAccessor world){
		return JigseuGorkaModVariables.MapVariables.get(world).Spot_4;
	}
	public void spotModify(LevelAccessor world, int calc){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_4 += calc;

	}
	public void scoreAnnul(LevelAccessor world){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_4 = 0;
		JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
	}

	public SpotBlockBlock4() {
		super();
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));

	}
}
