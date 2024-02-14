
package net.svisvi.jigseugorka.block;

import net.minecraft.world.level.LevelAccessor;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;

public class SpotBlockBlock5 extends SpotBlockBlock{
	public String name() {return"Дом культуры";}
	//public String state = "green"; //-1 blue --0 green-- 1 red


	public double spot(LevelAccessor world){
		return JigseuGorkaModVariables.MapVariables.get(world).Spot_5;
	}
	public void spotModify(LevelAccessor world, int calc){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_5 += calc;

	}
	public void scoreAnnul(LevelAccessor world){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_5 = 0;
		JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
	}

	public SpotBlockBlock5() {
		super();
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));

	}
}
