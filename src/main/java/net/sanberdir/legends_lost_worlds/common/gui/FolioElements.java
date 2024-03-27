package net.sanberdir.legends_lost_worlds.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.sanberdir.legends_lost_worlds.LLW;

public class FolioElements extends Screen {
    private static final ResourceLocation WINDOW_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research.png");
    private static final ResourceLocation INSIDE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research_back_1.png");
    private int textureX, textureY;

    public FolioElements(Component component) {
        super(component);
    }
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
        final int WINDOW_WIDTH = 256;
        final int WINDOW_HEIGHT = 230;
        final int xPos = (this.width - WINDOW_WIDTH) / 2;
        final int yPos = (this.height - WINDOW_HEIGHT) / 2;

        this.renderBackground(poseStack);

        double scale = minecraft.getWindow().getGuiScale();
        int scissorX = (int) (xPos * scale);
        int scissorY = (int) (minecraft.getWindow().getScreenHeight() - (yPos + WINDOW_HEIGHT) * scale); // Переворачиваем Y
        int scissorWidth = (int) (WINDOW_WIDTH * scale);
        int scissorHeight = (int) (WINDOW_HEIGHT * scale);


        renderInside(poseStack, xPos, yPos, WINDOW_WIDTH, WINDOW_HEIGHT, scissorX, scissorY, scissorWidth, scissorHeight);
        renderQuestIcon(poseStack, xPos, yPos, scissorX, scissorY, scissorWidth, scissorHeight, WINDOW_WIDTH, WINDOW_HEIGHT);
        renderWindow(poseStack, xPos, yPos, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void renderWindow(PoseStack poseStack, int xPox, int yPox, int WINDOW_WIDTH, int WINDOW_HEIGHT)
    {
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, WINDOW_TEXTURE);
        blit(poseStack, xPox, yPox, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        RenderSystem.disableBlend();
    }
    private void renderInside(PoseStack poseStack, int xPos, int yPos, int WINDOW_WIDTH, int WINDOW_HEIGHT, int scissorX, int scissorY, int scissorWidth, int scissorHeight)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, INSIDE_TEXTURE);
        blit(poseStack, (xPos - WINDOW_WIDTH) + textureX, (yPos - WINDOW_HEIGHT) + textureY, WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH * 3, WINDOW_HEIGHT * 3);
        RenderSystem.disableScissor();
    }
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        textureX += (int) deltaX;
        textureY += (int) deltaY;

        int maxOffsetX = 256;
        int maxOffsetY = 230;

        textureX = Math.max(-maxOffsetX, Math.min(maxOffsetX, textureX));
        textureY = Math.max(-maxOffsetY, Math.min(maxOffsetY, textureY));

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    private final ResourceLocation QUEST_ICON_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/item/folio_of_the_elements.png");
    private void renderQuestIcon(PoseStack poseStack, int xPos, int yPos, int scissorX, int scissorY, int scissorWidth, int scissorHeight, int WINDOW_WIDTH, int WINDOW_HEIGHT) {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, QUEST_ICON_TEXTURE);
        blit(poseStack, xPos + WINDOW_WIDTH / 2 + textureX, (yPos + WINDOW_HEIGHT / 2) + textureY, 0, 0, 16, 16, 16, 16);
        RenderSystem.disableScissor();
    }
}
