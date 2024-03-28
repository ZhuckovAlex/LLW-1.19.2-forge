package net.sanberdir.legends_lost_worlds.common.quest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.common.gui.FolioElements;

public class Quests {
    private static final ResourceLocation QUEST_TYPE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/quest_type_icons.png");
    private final int WINDOW_WIDTH = FolioElements.getWindowWidth();
    private final int WINDOW_HEIGHT = FolioElements.getWindowHeight();
    private static ResourceLocation QUEST_ICON_TEXTURE;
    private static int xPos, yPos;
    public Quests(ResourceLocation QUEST_ICON_TEXTURE)
    {
        Quests.QUEST_ICON_TEXTURE = QUEST_ICON_TEXTURE;
    }
    public void renderQuest(PoseStack poseStack, int xPos, int yPos, int textureX, int textureY, int scissorX, int scissorY, int scissorWidth, int scissorHeight) {
        renderQuestType(poseStack, xPos, yPos, textureX, textureY, scissorX, scissorY, scissorWidth, scissorHeight);
        renderIcon(poseStack, xPos, yPos, textureX, textureY, scissorX, scissorY, scissorWidth, scissorHeight);
    }
    private void renderQuestType(PoseStack poseStack, int xPos, int yPos, int textureX, int textureY, int scissorX, int scissorY, int scissorWidth, int scissorHeight)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, QUEST_TYPE_TEXTURE);
        Screen.blit(poseStack, (xPos + WINDOW_WIDTH / 2 + textureX) - 3, (yPos + WINDOW_WIDTH / 2 + textureY) - 16, 0, 0, 22, 22, 86, 54);
        RenderSystem.disableScissor();
    }
    private void renderIcon(PoseStack poseStack, int xPos, int yPos, int textureX, int textureY, int scissorX, int scissorY, int scissorWidth, int scissorHeight)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, QUEST_ICON_TEXTURE);
        Screen.blit(poseStack, xPos + WINDOW_WIDTH / 2 + textureX, (yPos + WINDOW_HEIGHT / 2) + textureY, 0, 0, 16, 16, 16, 16);
        RenderSystem.disableScissor();
    }
}
