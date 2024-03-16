package net.sanberdir.legends_lost_worlds.client;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.sanberdir.legends_lost_worlds.common.items.InitItemsLLW;

public class ModCreativeTab {
    public static final CreativeModeTab LLW_CREATIVE = new CreativeModeTab("llw") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(InitItemsLLW.FIRST_BOOK.get());
        }
    };
}
