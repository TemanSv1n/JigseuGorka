package net.svisvi.jigseugorka.procedures;

import net.minecraft.world.entity.Entity;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;

import net.minecraft.world.level.LevelAccessor;

public class SpotBlockEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		int coeff = 1;
		int var = 0;
		int score_max = 1500;
		if (entity.getTeam().getName() == "RED") {
			var = coeff * 1;
		} else if (entity.getTeam().getName() == "BLUE") {
			var = coeff * -1;
		}
		JigseuGorkaModVariables.MapVariables.get(world).Spot_1 = JigseuGorkaModVariables.MapVariables.get(world).Spot_1 + var;
		JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
	}
}
