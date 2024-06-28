package net.sanberdir.llw.common.quest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.gui.FolioElements;
import org.lwjgl.opengl.GL11;


public class QuestRender {
    private final ResourceLocation QUEST_TYPE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/quest_type_icons.png");

    public QuestRender(Quest quest, GuiGraphics guiGraphics, int scissorX, int scissorY, int scissorWidth, int scissorHeight, int screenCenterX, int screenCenterY, int textureX, int textureY) {
        int positionX = screenCenterX + textureX + quest.getPosition().getX();
        int positionY = screenCenterY + textureY + quest.getPosition().getY();

        renderQuestType(guiGraphics, quest, positionX, positionY, scissorX, scissorY, scissorWidth, scissorHeight);
        renderIcon(quest, positionX, positionY, scissorX, scissorY, scissorWidth, scissorHeight, guiGraphics);
    }

    private void renderQuestType(GuiGraphics guiGraphics, Quest quest, int positionX, int positionY, int scissorX, int scissorY, int scissorWidth, int scissorHeight) {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        // Очищаем область перед отрисовкой новой текстуры
        RenderSystem.clearColor(0.0F, 0.0F, 0.0F, 0.0F);
        RenderSystem.clear(GL11.GL_DEPTH_BUFFER_BIT, Minecraft.ON_OSX);

        if (quest.isActive()) {
            guiGraphics.blit(QUEST_TYPE_TEXTURE, positionX - 2, positionY - 1, 1, 33, 22, 54, 86, 54);
        } else {
            guiGraphics.blit(QUEST_TYPE_TEXTURE, positionX - 2, positionY - 1, 1, 1, 22, 22, 86, 54);
        }
        RenderSystem.disableScissor();
    }

    private void renderIcon(Quest quest, int x, int y, int scissorX, int scissorY, int scissorWidth, int scissorHeight, GuiGraphics guiGraphics) {
        ItemRenderer renderItem = Minecraft.getInstance().getItemRenderer();
        String[] id = quest.getTexture().split(":");
        ResourceLocation itemId = new ResourceLocation(id[0], id[1]);

        Item item = ForgeRegistries.ITEMS.getValue(itemId);
        ItemStack itemStack = new ItemStack(item);

        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        // Очищаем область перед отрисовкой новой текстуры
        RenderSystem.clearColor(0.0F, 0.0F, 0.0F, 0.0F);
        RenderSystem.clear(GL11.GL_DEPTH_BUFFER_BIT, Minecraft.ON_OSX);

        guiGraphics.renderItem(itemStack, x, y);
        RenderSystem.disableScissor();
    }
}