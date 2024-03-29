package net.sanberdir.legends_lost_worlds.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.common.quest.Quests;

import java.util.ArrayList;
import java.util.List;

public class FolioElements extends Screen {
    // Textures
    private static final ResourceLocation WINDOW_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research.png");
    private static final ResourceLocation INSIDE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research_back_1.png");
    // Window
    public static final int WINDOW_WIDTH = 256;
    public static final int WINDOW_HEIGHT = 230;
    public static int screenCenterX, screenCenterY;
    private int textureX, textureY;
    // Quests
    private static final List<Quests> quests = new ArrayList<>();
    public FolioElements(Component component) {
        super(component);
    }
    @Override
    public void onClose() {
        quests.clear();
        super.onClose();
    }
    @Override
    protected void init() {
        initQuests();

        screenCenterX = (this.width - WINDOW_WIDTH) / 2;
        screenCenterY = (this.height - WINDOW_HEIGHT) / 2;
        super.init();
    }
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);

        this.renderBackground(poseStack);

        // the calculation of scissor
        assert minecraft != null;
        double scale = minecraft.getWindow().getGuiScale();
        int scissorX = (int) (screenCenterX * scale);
        int scissorY = (int) (minecraft.getWindow().getScreenHeight() - (screenCenterY + WINDOW_HEIGHT) * scale);
        int scissorWidth = (int) (WINDOW_WIDTH * scale);
        int scissorHeight = (int) (WINDOW_HEIGHT * scale);


        // Renders
        renderInside(poseStack, scissorX, scissorY, scissorWidth, scissorHeight);

        for (Quests quests1: quests){
            quests1.renderQuest(poseStack, screenCenterX, screenCenterY, textureX, textureY, scissorX, scissorY, scissorWidth, scissorHeight);
        }

        renderWindow(poseStack);
    }
    private void renderWindow(PoseStack poseStack)
    {
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, WINDOW_TEXTURE);
        blit(poseStack, screenCenterX, screenCenterY, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        RenderSystem.disableBlend();
    }
    private void renderInside(PoseStack poseStack, int scissorX, int scissorY, int scissorWidth, int scissorHeight)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, INSIDE_TEXTURE);
        blit(poseStack, (screenCenterX - WINDOW_WIDTH) + textureX, (screenCenterY - WINDOW_HEIGHT) + textureY, WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH * 3, WINDOW_HEIGHT * 3);
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
    private void initQuests()
    {
        quests.add(new Quests(new ResourceLocation(LLW.MOD_ID, "textures/item/folio_of_the_elements.png"), 0,0));
        quests.add(new Quests(new ResourceLocation(LLW.MOD_ID, "textures/item/focusing_lens.png"), 0,+40));
    }
}
