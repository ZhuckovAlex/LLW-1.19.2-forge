package net.sanberdir.legends_lost_worlds.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class FolioElements extends Screen { // Объявление класса FolioElements, который расширяет класс Screen

    // Путь к текстуре фона GUI
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("legends_lost_worlds", "textures/gui/gui_research_back_1.png");

    // Конструктор класса, принимающий компонент
    public FolioElements(Component component) {
        super(component); // Вызов конструктора суперкласса Screen с передачей компонента
        this.viewportX = (TEXTURE_WIDTH - VIEWPORT_WIDTH) / 2.0; // Инициализация начальной координаты X области просмотра
        this.viewportY = (TEXTURE_HEIGHT - VIEWPORT_HEIGHT) / 2.0; // Инициализация начальной координаты Y области просмотра
    }

    // Ширина и высота текстуры фона GUI
    private static final int TEXTURE_WIDTH = 1200;
    private static final int TEXTURE_HEIGHT = 800;

    // Размеры области просмотра
    private static final int VIEWPORT_WIDTH = 400;
    private static final int VIEWPORT_HEIGHT = 300;

    // Координаты верхнего левого угла области просмотра в текстуре
    private double viewportX = 0;
    private double viewportY = 0;

    // Последние известные координаты мыши и флаг перетаскивания
    private double lastMouseX;
    private double lastMouseY;
    private boolean isDragging;

    // Метод для отрисовки GUI
    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack); // Рендер фона GUI
        super.render(matrixStack, mouseX, mouseY, partialTicks); // Вызов метода render суперкласса

        // Получение текущих размеров экрана/окна
        int screenWidth = this.width;
        int screenHeight = this.height;

        // Устанавливаем область просмотра равной размеру экрана
        int viewportWidth = screenWidth;
        int viewportHeight = screenHeight;

        // Установка координат для центрирования области просмотра (в данном случае, область просмотра занимает весь экран, так что координаты будут (0,0))
        int viewportScreenX = 0;
        int viewportScreenY = 0;

        // Устанавливаем текстуру фона
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        // Рисуем текстуру фона GUI, адаптированную под весь экран
        blit(matrixStack, viewportScreenX, viewportScreenY, (int) viewportX, (int) viewportY, viewportWidth, viewportHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    // Метод для обработки события клика мышью
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Проверка, была ли нажата левая кнопка мыши
            this.isDragging = true; // Установка флага перетаскивания в true
            this.lastMouseX = mouseX; // Сохранение текущей позиции X мыши
            this.lastMouseY = mouseY; // Сохранение текущей позиции Y мыши
        }
        return super.mouseClicked(mouseX, mouseY, button); // Возврат результата выполнения метода mouseClicked суперкласса
    }

    // Метод для обработки события отпускания кнопки мыши
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) { // Проверка, была ли отпущена левая кнопка мыши
            this.isDragging = false; // Установка флага перетаскивания в false
        }
        return super.mouseReleased(mouseX, mouseY, button); // Возврат результата выполнения метода mouseReleased суперкласса
    }

    // Метод для обработки события перетаскивания мышью
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isDragging) { // Проверка, происходит ли в данный момент перетаскивание
            // Инвертирование направления изменения координат
            deltaX *= -1; // Инвертирование изменения координаты X
            deltaY *= -1; // Инвертирование изменения координаты Y

            // Обновление положения области просмотра в пределах текстуры
            viewportX = Math.max(0, Math.min(viewportX + deltaX, TEXTURE_WIDTH - VIEWPORT_WIDTH)); // Обновление координаты X
            viewportY = Math.max(0, Math.min(viewportY + deltaY, TEXTURE_HEIGHT - VIEWPORT_HEIGHT)); // Обновление координаты Y
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY); // Возврат результата выполнения метода mouseDragged суперкласса
    }

    // Метод, указывающий, является ли экран приостановленным (не является ли это экраном паузы)
    @Override
    public boolean isPauseScreen() {
        return false; // Возврат false, поскольку этот экран не является экраном паузы
    }
}
