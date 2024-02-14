
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.jigseugorka.init;

import net.svisvi.jigseugorka.block.*;
import net.svisvi.jigseugorka.JigseuGorkaMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class JigseuGorkaModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, JigseuGorkaMod.MODID);
	public static final RegistryObject<Block> SPOT_BLOCK = REGISTRY.register("spot_block", () -> new SpotBlockBlock());
	public static final RegistryObject<Block> SPOT_BLOCK2 = REGISTRY.register("spot_block2", () -> new SpotBlockBlock2());
	public static final RegistryObject<Block> SPOT_BLOCK3 = REGISTRY.register("spot_block3", () -> new SpotBlockBlock3());
	public static final RegistryObject<Block> SPOT_BLOCK4 = REGISTRY.register("spot_block4", () -> new SpotBlockBlock4());
	public static final RegistryObject<Block> SPOT_BLOCK5 = REGISTRY.register("spot_block5", () -> new SpotBlockBlock5());
	public static final RegistryObject<Block> SPOT_BLOCK_GLOBAL = REGISTRY.register("spot_block_global", () -> new SpotBlockBlockGlobal());
	public static final RegistryObject<Block> RED_UPPER_SET = REGISTRY.register("red_upper_set", () -> new RedUpperSetBlock());
	public static final RegistryObject<Block> BLUE_UPPER_SET = REGISTRY.register("blue_upper_set", () -> new BlueUpperSetBlock());
}
