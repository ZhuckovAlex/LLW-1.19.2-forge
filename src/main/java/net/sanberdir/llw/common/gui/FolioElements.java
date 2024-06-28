package net.sanberdir.llw.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.quest.Quest;
import net.sanberdir.llw.common.quest.QuestLoader;
import net.sanberdir.llw.common.quest.QuestRender;
import net.sanberdir.llw.common.quest.QuestSaver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FolioElements extends Screen {
    private static final ResourceLocation WINDOW_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research.png");
    private static final ResourceLocation INSIDE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research_back_1.png");
    public static final int WINDOW_WIDTH = 256;
    public static final int WINDOW_HEIGHT = 230;
    private int screenCenterX, screenCenterY;
    private int textureX, textureY;
    private double scale;
    public static List<Quest> quests = new ArrayList<>();

    public FolioElements(Component component) {
        super(component);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int p_281550_, int p_282878_, float p_282465_) {
        this.renderBackground(guiGraphics);
        renderInside(guiGraphics);

        for (Quest quest : quests) {
            new QuestRender(quest, guiGraphics, getScissorX(224), getScissorY(194), getScissorWidth(224), getScissorHeight(194), screenCenterX, screenCenterY, textureX, textureY);
        }

        renderWindow(guiGraphics);
        super.render(guiGraphics, p_281550_, p_282878_, p_282465_);
    }

    @Override
    public void onClose() {
        QuestSaver.saveQuests(quests, "path/to/quests/directory");
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

    private void renderWindow(GuiGraphics guiGraphics) {
        RenderSystem.enableBlend();
        guiGraphics.blit(WINDOW_TEXTURE, screenCenterX, screenCenterY, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        RenderSystem.disableBlend();
    }

    private void renderInside(GuiGraphics guiGraphics) {
        RenderSystem.enableScissor(getScissorX(WINDOW_WIDTH), getScissorY(WINDOW_HEIGHT), getScissorWidth(WINDOW_WIDTH), getScissorHeight(WINDOW_HEIGHT));
        RenderSystem.setShaderTexture(0, INSIDE_TEXTURE);
        guiGraphics.blit(INSIDE_TEXTURE, (screenCenterX - WINDOW_WIDTH) + textureX, (screenCenterY - WINDOW_HEIGHT) + textureY, WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH * 3, WINDOW_HEIGHT * 3);
        RenderSystem.disableScissor();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // левая кнопка мыши
            for (Quest quest : quests) {
                if (isMouseOverQuest(quest, mouseX, mouseY)) {
                    quest.toggleActive();
                    return true; // Убедимся, что возвращаем true после обработки клика
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOverQuest(Quest quest, double mouseX, double mouseY) {
        int positionX = screenCenterX + textureX + quest.getPosition().getX();
        int positionY = screenCenterY + textureY + quest.getPosition().getY();
        boolean isOver = mouseX >= positionX && mouseX <= positionX + 22 && mouseY >= positionY && mouseY <= positionY + 22;

        return isOver;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (button == 0) { // левая кнопка мыши
            textureX += (int) deltaX;
            textureY += (int) deltaY;

            int maxOffsetX = 256;
            int maxOffsetY = 230;

            textureX = Math.max(-maxOffsetX, Math.min(maxOffsetX, textureX));
            textureY = Math.max(-maxOffsetY, Math.min(maxOffsetY, textureY));

            return true; // Возвращаем true после обработки перетаскивания
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private int getScissorX(int WINDOW_WIDTH) {
        return (int) (((this.width - WINDOW_WIDTH) / 2) * scale);
    }

    private int getScissorY(int WINDOW_HEIGHT) {
        return (int) (minecraft.getWindow().getScreenHeight() - (((this.height - WINDOW_HEIGHT) / 2) + WINDOW_HEIGHT) * scale);
    }

    private int getScissorWidth(int WINDOW_WIDTH) {
        return (int) (WINDOW_WIDTH * scale);
    }

    private int getScissorHeight(int WINDOW_HEIGHT) {
        return (int) (WINDOW_HEIGHT * scale);
    }
}