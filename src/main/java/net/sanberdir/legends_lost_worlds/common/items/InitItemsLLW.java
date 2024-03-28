package net.sanberdir.legends_lost_worlds.common.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.client.ModCreativeTab;
import net.sanberdir.legends_lost_worlds.common.items.custom.FolioOfTheElements;

public class InitItemsLLW {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LLW.MOD_ID);
    public static final RegistryObject<Item> FOLIO_OF_THE_ELEMENTS = ITEMS.register("folio_of_the_elements",
            () -> new FolioOfTheElements(new Item.Properties().tab(ModCreativeTab.LLW_CREATIVE).stacksTo(1)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
