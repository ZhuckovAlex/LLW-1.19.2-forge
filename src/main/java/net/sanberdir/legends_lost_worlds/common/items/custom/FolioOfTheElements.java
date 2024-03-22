package net.sanberdir.legends_lost_worlds.common.items.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sanberdir.legends_lost_worlds.common.gui.FolioElements;

public class FolioOfTheElements extends Item {
    public FolioOfTheElements(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        Minecraft.getInstance().setScreen(new FolioElements(Component.literal("Achievements")));
        return super.use(level, player, interactionHand);
    }

}
