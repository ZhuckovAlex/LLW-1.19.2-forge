package net.sanberdir.legends_lost_worlds.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class FolioElements extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("legends_lost_worlds", "textures/gui/gui_research_back_1.png");
    public FolioElements(Component component) {
        super(component);
        this.viewportX = (TEXTURE_WIDTH - VIEWPORT_WIDTH) / 2.0;
        this.viewportY = (TEXTURE_HEIGHT - VIEWPORT_HEIGHT) / 2.0;
    }
    private static final int TEXTURE_WIDTH = 800;
    private static final int TEXTURE_HEIGHT = 600;
    // Ограничивающая область для отображения части текстуры
    private static final int VIEWPORT_WIDTH = 400;
    private static final int VIEWPORT_HEIGHT = 300;
    // Координаты верхнего левого угла области просмотра в текстуре
    private double viewportX = 0;
    private double viewportY = 0;
    private double lastMouseX;
    private double lastMouseY;
    private boolean isDragging;


    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack); // Рендер фона
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // Расчет координат для центрирования области просмотра на экране
        int viewportScreenX = (this.width - VIEWPORT_WIDTH) / 2;
        int viewportScreenY = (this.height - VIEWPORT_HEIGHT) / 2;

        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        blit(matrixStack, viewportScreenX, viewportScreenY, (int) viewportX, (int) viewportY, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.isDragging = true;
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            this.isDragging = false;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isDragging) {
            // Обновляем положение области просмотра в пределах текстуры
            viewportX = Math.max(0, Math.min(viewportX + deltaX, TEXTURE_WIDTH - VIEWPORT_WIDTH));
            viewportY = Math.max(0, Math.min(viewportY + deltaY, TEXTURE_HEIGHT - VIEWPORT_HEIGHT));
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
