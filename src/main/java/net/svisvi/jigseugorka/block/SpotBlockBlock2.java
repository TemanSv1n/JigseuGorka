
package net.svisvi.jigseugorka.block;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.svisvi.jigseugorka.init.JigseuGorkaModGameRules;
import net.svisvi.jigseugorka.init.JigseuGorkaModItems;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SpotBlockBlock2 extends SpotBlockBlock{
	public String name() {return"Красный НИИ";}
	//public String state = "green"; //-1 blue --0 green-- 1 red


	public double spot(LevelAccessor world){
		return JigseuGorkaModVariables.MapVariables.get(world).Spot_2;
	}
	public void spotModify(LevelAccessor world, int calc){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_2 += calc;

	}
	public void scoreAnnul(LevelAccessor world){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_2 = 0;
		JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
	}

	public SpotBlockBlock2() {
		super();
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));

	}
}
