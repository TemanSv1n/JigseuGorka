
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.jigseugorka.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JigseuGorkaModGameRules {
    public static final GameRules.Key<GameRules.IntegerValue> MAX_SPOT_TRESHOLD =
            GameRules.register("maxSpotTreshold", GameRules.Category.MISC, GameRules.IntegerValue.create(1500));
}
