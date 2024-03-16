package net.sanberdir.legends_lost_worlds.common.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.client.ModCreativeTab;

public class InitItemsLLW {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LLW.MOD_ID);
    public static final RegistryObject<Item> FIRST_BOOK = ITEMS.register("first_book",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
