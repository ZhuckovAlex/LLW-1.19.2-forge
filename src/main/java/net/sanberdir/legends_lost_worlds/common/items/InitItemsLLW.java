package net.sanberdir.legends_lost_worlds.common.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.client.ModCreativeTab;
import net.sanberdir.legends_lost_worlds.common.blocks.InitBlocksLLW;
import net.sanberdir.legends_lost_worlds.common.items.custom.FolioOfTheElements;

public class InitItemsLLW {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LLW.MOD_ID);


    public static final RegistryObject<Item> MOON_TEAR_ITEM = ITEMS.register("moon_tear_item",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CRYSTAL_FORMATION = ITEMS.register("crystal_formation",
            () -> new ItemNameBlockItem(InitBlocksLLW.CRYSTAL_FORMATION.get(),(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE))));
    public static final RegistryObject<Item> FOLIO_OF_THE_ELEMENTS = ITEMS.register("folio_of_the_elements",
            () -> new FolioOfTheElements(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE).stacksTo(1)));
    public static final RegistryObject<Item> SALIS_MIRACULA = ITEMS.register("salis_miracula",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> INK_AND_PEN = ITEMS.register("ink_and_pen",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE)));
    public static final RegistryObject<Item> FOCUSING_LENS = ITEMS.register("focusing_lens",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
