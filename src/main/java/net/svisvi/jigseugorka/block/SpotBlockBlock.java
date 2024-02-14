
package net.svisvi.jigseugorka.block;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;
import net.minecraftforge.registries.ForgeRegistries;
import net.svisvi.jigseugorka.init.JigseuGorkaModGameRules;
import net.svisvi.jigseugorka.init.JigseuGorkaModItems;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;
import net.svisvi.jigseugorka.procedures.SpotBlockEntityWalksOnTheBlockProcedure;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class SpotBlockBlock extends Block {
	//	@Override
//	public void setLevel(LevelAccessor world){
//		this.world = world;
//	}
	public static int scoreMax(LevelAccessor world) {
		return world.getLevelData().getGameRules().getInt(JigseuGorkaModGameRules.MAX_SPOT_TRESHOLD); //1500
	}

	//public static String abas = {JigseuGorkaModVariables.MapVariables.get(world).Spot_1};
	public String name() {return"Золотая горка";}
	public String state = "green"; //-1 blue --0 green-- 1 red

	public double spot(LevelAccessor world){
		return JigseuGorkaModVariables.MapVariables.get(world).Spot_1;
	}
	public void spotModify(LevelAccessor world, int calc){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_1 += calc;

	}
	public void scoreAnnul(LevelAccessor world){
		JigseuGorkaModVariables.MapVariables.get(world).Spot_1 = 0;
		JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
	}

	public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 2);

	public SpotBlockBlock() {
		super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.METAL).strength(-1, 3600000));
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));

	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LEVEL);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(LEVEL, 0);
	}



	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState blockstate, Entity entity) {
		super.stepOn(world, pos, blockstate, entity);
		int calc = calculate(world,entity);
		if (this.spot(world) + calc > (-1 * scoreMax(world)) | this.spot(world) + calc < scoreMax(world)){
			this.spotModify(world, calc);
			JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
		}

	}
	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
		//System.out.println("tick");
		//System.out.println(JigseuGorkaModVariables.MapVariables.get(world).Spot_1);
		super.tick(blockstate, world, pos, random);
		this.colorLogic(world, blockstate, pos, this.spot(world));
		world.scheduleTick(pos, this, 2);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 15);
	}

	@Override
	public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand hand, BlockHitResult p_60508_) {
		if ((p_60506_ instanceof ServerPlayer _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == JigseuGorkaModItems.ANNULATOR.get()) {
			this.scoreAnnul(p_60504_);

		}
		return super.use(p_60503_, p_60504_, p_60505_, p_60506_, hand, p_60508_);
	}

	public void colorLogic(LevelAccessor world, BlockState blockState, BlockPos pos, double color){
		if (color == 0){
			world.setBlock(pos, blockState.setValue(LEVEL, 0), 3);
		} else if (color < 0){
			world.setBlock(pos, blockState.setValue(LEVEL, 2), 3); //blue
			this.broadcast(world, pos,"blue");
		} else if (color > 0){
			world.setBlock(pos, blockState.setValue(LEVEL, 1), 3); //red
			this.broadcast(world, pos,"red");
		}
	}



	public static int calculate(LevelAccessor world, Entity entity) {
		int coeff = 1;
		int var = 0;
		if ((entity.getTeam() != null ? entity.getTeam().getName() : "SUS").equals("RED")) {
			var = coeff * 1;
		} else if ((entity.getTeam() != null ? entity.getTeam().getName() : "SUS").equals("BLUE")) {
			var = coeff * -1;
		}

		//System.out.println(var);
		//System.out.println(entity.getTeam().getName());
		return var;
	}

	public void broadcast(LevelAccessor world, BlockPos blockPos, String color_code){
		if (!(this.state.equals(color_code))){
			String cmd = String.format("title @a subtitle {\"text\":\"Точка %s захвачена\",\"color\":\"%s\"}", this.name(), color_code);
			if (world instanceof ServerLevel _level) {
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"title @a times 20 60 20");
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						cmd);
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"title @a title {\"text\":\"\"}");

			final Vec3 _center = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(64 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof LivingEntity _entity && !_entity.level.isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 120, 1, false, false));
			}


				//title @a times 20 60 20
				//sub
				//title @a title {"text":""}
				if (!_level.isClientSide()) {
					_level.playSound(null, blockPos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.hurt")), SoundSource.NEUTRAL, 1, -1);
				} else {
					_level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.hurt")), SoundSource.NEUTRAL, 1, -1, false);
				}

			}


		}
		this.state=color_code;


	}
}
