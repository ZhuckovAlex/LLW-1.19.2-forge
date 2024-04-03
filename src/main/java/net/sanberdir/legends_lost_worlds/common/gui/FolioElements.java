package net.sanberdir.legends_lost_worlds.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.sanberdir.legends_lost_worlds.LLW;
import net.sanberdir.legends_lost_worlds.common.quest.Quest;
import net.sanberdir.legends_lost_worlds.common.quest.QuestLoader;
import net.sanberdir.legends_lost_worlds.common.quest.QuestRender;

import java.util.ArrayList;
import java.util.List;

public class FolioElements extends Screen {
    // Textures
    private static final ResourceLocation WINDOW_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research.png");
    private static final ResourceLocation INSIDE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research_back_1.png");
    // Window
    public static final int WINDOW_WIDTH = 256;
    public static final int WINDOW_HEIGHT = 230;
    private int screenCenterX, screenCenterY;
    private int textureX, textureY;
    private double scale;
    // Quests
    public static List<Quest> quests = new ArrayList<>();
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
        screenCenterX = (this.width - WINDOW_WIDTH) / 2;
        screenCenterY = (this.height - WINDOW_HEIGHT) / 2;

        scale = minecraft.getWindow().getGuiScale();

        QuestLoader.loadQuests();
        super.init();
    }
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
        this.renderBackground(poseStack);

        // Renders
        renderInside(poseStack);

        for (Quest quest: quests){
            new QuestRender(quest, poseStack, getScissorX(224), getScissorY(194), getScissorWidth(224), getScissorHeight(194), screenCenterX, screenCenterY, textureX, textureY);
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
    private void renderInside(PoseStack poseStack)
    {
        RenderSystem.enableScissor(getScissorX(WINDOW_WIDTH), getScissorY(WINDOW_HEIGHT), getScissorWidth(WINDOW_WIDTH), getScissorHeight(WINDOW_HEIGHT));
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

    private int getScissorX(int WINDOW_WIDTH)
    {
        return (int) (((this.width - WINDOW_WIDTH) / 2) * scale);
    }

    private int getScissorY(int WINDOW_HEIGHT)
    {
        return (int) (minecraft.getWindow().getScreenHeight() - (((this.height - WINDOW_HEIGHT) / 2) + WINDOW_HEIGHT) * scale);
    }

    private int getScissorWidth(int WINDOW_WIDTH)
    {
        return (int) (WINDOW_WIDTH * scale);
    }

    private int getScissorHeight(int WINDOW_HEIGHT)
    {
        return (int) (WINDOW_HEIGHT * scale);
    }
}
