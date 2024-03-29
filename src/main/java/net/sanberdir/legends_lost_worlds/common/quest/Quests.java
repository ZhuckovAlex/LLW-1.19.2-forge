package net.sanberdir.legends_lost_worlds.common.quest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.common.gui.FolioElements;

public class Quests {
    private static final ResourceLocation QUEST_TYPE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/quest_type_icons.png");
    private ResourceLocation QUEST_ICON_TEXTURE;
    private static int screenCenterX, screenCenterY;
    private int xPos, yPos;
    public Quests(ResourceLocation QUEST_ICON_TEXTURE, int xPos, int yPos)
    {
        this.QUEST_ICON_TEXTURE = QUEST_ICON_TEXTURE;
        this.yPos = yPos;
        this.xPos = xPos;
    }
    public Quests(ResourceLocation QUEST_ICON_TEXTURE, Quests parent)
    {

    }
    public void renderQuest(PoseStack poseStack, int screenCenterX, int screenCenterY, int textureX, int textureY, int scissorX, int scissorY, int scissorWidth, int scissorHeight) {
        Quests.screenCenterX = ((screenCenterX + FolioElements.WINDOW_WIDTH / 2) - 8);
        Quests.screenCenterY = ((screenCenterY + FolioElements.WINDOW_HEIGHT / 2) - 8);
        Quests.screenCenterX += xPos;
        Quests.screenCenterY += yPos;
        renderQuestType(poseStack, textureX, textureY, scissorX, scissorY, scissorWidth, scissorHeight);
        renderIcon(poseStack, textureX, textureY, scissorX, scissorY, scissorWidth, scissorHeight);
    }
    private void renderQuestType(PoseStack poseStack, int textureX, int textureY, int scissorX, int scissorY, int scissorWidth, int scissorHeight)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, QUEST_TYPE_TEXTURE);
        Screen.blit(poseStack, screenCenterX - 3 + textureX, screenCenterY - 2 + textureY, 0, 0, 22, 22, 86, 54);
        RenderSystem.disableScissor();
    }
    private void renderIcon(PoseStack poseStack, int textureX, int textureY, int scissorX, int scissorY, int scissorWidth, int scissorHeight)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, QUEST_ICON_TEXTURE);
        Screen.blit(poseStack, screenCenterX + textureX, screenCenterY + textureY, 0, 0, 16, 16, 16, 16);
        RenderSystem.disableScissor();
    }
}
