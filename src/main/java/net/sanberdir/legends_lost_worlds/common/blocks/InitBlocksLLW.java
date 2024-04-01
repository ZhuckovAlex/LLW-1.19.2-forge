package net.sanberdir.legends_lost_worlds.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.client.ModCreativeTab;
import net.sanberdir.legends_lost_worlds.common.blocks.custom.MoonTear;
import net.sanberdir.legends_lost_worlds.common.items.InitItemsLLW;
import net.sanberdir.legends_lost_worlds.common.sounds.CustomSoundEvents;

import java.util.function.Supplier;

public class InitBlocksLLW {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, LLW.MOD_ID);

    public static final RegistryObject<Block> MOON_TEAR = registerBlock("moon_tear",
            () -> new MoonTear(BlockBehaviour.Properties.of(Material.STONE)
                    .sound(CustomSoundEvents.MOON_TEAR_SOUNDS).randomTicks().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).strength(3, 12).lightLevel((p_50884_) -> {
                        return 0;
                    }).requiresCorrectToolForDrops()), ModCreativeTab.LLW_CREATIVE);
    public static final RegistryObject<Block> CRYSTAL_CAVE_STONE = registerBlock("crystal_cave_stone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .sound(SoundType.NETHER_GOLD_ORE).strength(1.7f, 3).requiresCorrectToolForDrops()), ModCreativeTab.LLW_CREATIVE);
    public static final RegistryObject<Block> CRYSTAL_FORMATION = registerBlockWithoutBlockItem("crystal_formation",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .sound(CustomSoundEvents.MOON_TEAR_SOUNDS).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).strength(3, 12).lightLevel((p_50884_) -> {
                        return 3;
                    }).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return InitItemsLLW.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
        return true;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
