package net.sanberdir.llw.common.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.blocks.custom.MoonTear;
import net.sanberdir.llw.common.items.LLWItems;
import net.sanberdir.llw.common.sounds.ModSoundsLLW;

import java.util.function.Supplier;

public class LLWBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, LLW.MOD_ID);
    public static final RegistryObject<Block> MOON_TEAR = registerBlock("moon_tear",
            () -> new MoonTear(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .sound(ModSoundsLLW.MOON_TEAR_SOUNDS).randomTicks().noOcclusion().strength(3, 12).lightLevel((p_50884_) -> {
                        return 8;
                    }).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_FORMATION = BLOCKS.register("crystal_formation",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(1.5F, 6.0F)
                    .sound(ModSoundsLLW.MOON_TEAR_SOUNDS).strength(3, 12).lightLevel((p_50884_) -> {
                        return 10;
                    }).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_CAVE_STONE = registerBlock("crystal_cave_stone",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .sound(SoundType.NETHER_GOLD_ORE).strength(1.7f, 3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_CAVE_BRICKS = registerBlock("crystal_cave_bricks",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .sound(SoundType.NETHER_GOLD_ORE).strength(1.7f, 3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_CAVE_BRICKS_SLAB = registerBlock("crystal_cave_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .sound(SoundType.NETHER_GOLD_ORE).strength(1.7f, 3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_CAVE_BRICKS_STAIRS = registerBlock("crystal_cave_bricks_stairs",
            () -> new StairBlock(LLWBlocks.CRYSTAL_CAVE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                    .sound(SoundType.NETHER_GOLD_ORE).strength(1.7f, 3).requiresCorrectToolForDrops()));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return LLWItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
