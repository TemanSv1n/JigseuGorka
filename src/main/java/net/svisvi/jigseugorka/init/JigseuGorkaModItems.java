
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.jigseugorka.init;

import net.svisvi.jigseugorka.item.*;
import net.svisvi.jigseugorka.JigseuGorkaMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class JigseuGorkaModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, JigseuGorkaMod.MODID);
	public static final RegistryObject<Item> SLEDGE_HAMMER_MULTI_TOOL = REGISTRY.register("sledge_hammer_multi_tool", () -> new SledgeHammerMultiToolItem());
	public static final RegistryObject<Item> SPOT_BLOCK = block(JigseuGorkaModBlocks.SPOT_BLOCK, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> SPOT_BLOCK2 = block(JigseuGorkaModBlocks.SPOT_BLOCK2, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> SPOT_BLOCK3 = block(JigseuGorkaModBlocks.SPOT_BLOCK3, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> SPOT_BLOCK4 = block(JigseuGorkaModBlocks.SPOT_BLOCK4, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> SPOT_BLOCK5 = block(JigseuGorkaModBlocks.SPOT_BLOCK5, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> SPOT_BLOCK_GLOBAL = block(JigseuGorkaModBlocks.SPOT_BLOCK_GLOBAL, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> RED_UPPER_SET = block(JigseuGorkaModBlocks.RED_UPPER_SET, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> BLUE_UPPER_SET = block(JigseuGorkaModBlocks.BLUE_UPPER_SET, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> TEAPOT_DRILL = REGISTRY.register("teapot_drill", () -> new TeapotDrillItem());
	public static final RegistryObject<Item> DRILL_FUEL = REGISTRY.register("drill_fuel", () -> new DrillFuelItem());
	public static final RegistryObject<Item> LADLE_SPOON = REGISTRY.register("ladle_spoon", () -> new LadleSpoonItem());
	public static final RegistryObject<Item> VACUUM_CLEANER = REGISTRY.register("vacuum_cleaner", () -> new VacuumCleanerItem());
	public static final RegistryObject<Item> ANNULATOR = REGISTRY.register("annulator", () -> new AnnulatorItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
