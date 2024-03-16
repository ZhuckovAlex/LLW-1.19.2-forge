package net.sanberdir.legends_lost_worlds.client;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
public class ModCreativeTab {
    public static final CreativeModeTab LLW_CREATIVE = new CreativeModeTab("llw_creative") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.CRYING_OBSIDIAN);
        }
    };
}
