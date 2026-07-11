package com.school.schoolclient.client.gui;

import com.school.schoolclient.client.config.SchoolConfig;
import com.school.schoolclient.client.feature.AnimationManager;
import com.school.schoolclient.client.feature.ZoomManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
    private final Screen parent;
    private int currentTab = 0; // 0 = Animation, 1 = Zoom, 2 = HUD

    private TextFieldWidget maxZoomField;
    private TextFieldWidget animSpeedField;
    private TextFieldWidget hudXField;
    private TextFieldWidget hudYField;
    private TextFieldWidget hudScaleField;

    public ConfigScreen(Screen parent) {
        super(Text.literal("SchoolClient - Cài Đặt"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        
        // Nút chuyển tab
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Hoạt ảnh"), button -> currentTab = 0)
                .dimensions(10, 10, 80, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Phóng to"), button -> currentTab = 1)
                .dimensions(100, 10, 80, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("HUD"), button -> currentTab = 2)
                .dimensions(190, 10, 80, 20).build());

        // Nút đóng
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Đóng"), button -> this.close())
                .dimensions(this.width - 90, 10, 80, 20).build());

        // Tab Animation
        if (currentTab == 0) {
            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.enableLegacyAnimations ? "✓ Hoạt ảnh 1.7" : "Hoạt ảnh 1.7"),
                    button -> {
                        SchoolConfig.enableLegacyAnimations = !SchoolConfig.enableLegacyAnimations;
                        AnimationManager.setLegacyAnimations(SchoolConfig.enableLegacyAnimations);
                    }).dimensions(20, 50, 150, 20).build());

            animSpeedField = new TextFieldWidget(this.textRenderer, 20, 80, 100, 20, Text.literal("Tốc độ"));
            animSpeedField.setText(String.valueOf(SchoolConfig.animationSpeed));
            this.addSelectableChild(animSpeedField);
            this.setInitialFocus(animSpeedField);

            this.addDrawableChild(ButtonWidget.builder(Text.literal("Lưu"), button -> {
                try {
                    float speed = Float.parseFloat(animSpeedField.getText());
                    AnimationManager.setAnimationSpeed(speed);
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu không phải số
                }
            }).dimensions(130, 80, 50, 20).build());
        }

        // Tab Zoom
        if (currentTab == 1) {
            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.enableZoom ? "✓ Bật Zoom" : "Bật Zoom"),
                    button -> {
                        SchoolConfig.enableZoom = !SchoolConfig.enableZoom;
                        SchoolConfig.saveConfig();
                    }).dimensions(20, 50, 150, 20).build());

            maxZoomField = new TextFieldWidget(this.textRenderer, 20, 80, 100, 20, Text.literal("Zoom Max"));
            maxZoomField.setText(String.valueOf(SchoolConfig.maxZoom));
            this.addSelectableChild(maxZoomField);

            this.addDrawableChild(ButtonWidget.builder(Text.literal("Lưu"), button -> {
                try {
                    float zoom = Float.parseFloat(maxZoomField.getText());
                    ZoomManager.setMaxZoom(zoom);
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu không phải số
                }
            }).dimensions(130, 80, 50, 20).build());
        }

        // Tab HUD
        if (currentTab == 2) {
            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.showFPS ? "✓ FPS" : "FPS"),
                    button -> {
                        SchoolConfig.showFPS = !SchoolConfig.showFPS;
                        SchoolConfig.saveConfig();
                    }).dimensions(20, 50, 70, 20).build());

            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.showPing ? "✓ Ping" : "Ping"),
                    button -> {
                        SchoolConfig.showPing = !SchoolConfig.showPing;
                        SchoolConfig.saveConfig();
                    }).dimensions(100, 50, 70, 20).build());

            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.showCPS ? "✓ CPS" : "CPS"),
                    button -> {
                        SchoolConfig.showCPS = !SchoolConfig.showCPS;
                        SchoolConfig.saveConfig();
                    }).dimensions(180, 50, 70, 20).build());

            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.showCoordinates ? "✓ Tọa độ" : "Tọa độ"),
                    button -> {
                        SchoolConfig.showCoordinates = !SchoolConfig.showCoordinates;
                        SchoolConfig.saveConfig();
                    }).dimensions(20, 80, 70, 20).build());

            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal(SchoolConfig.showDirection ? "✓ Hướng" : "Hướng"),
                    button -> {
                        SchoolConfig.showDirection = !SchoolConfig.showDirection;
                        SchoolConfig.saveConfig();
                    }).dimensions(100, 80, 70, 20).build());

            hudScaleField = new TextFieldWidget(this.textRenderer, 20, 120, 100, 20, Text.literal("Kích thước"));
            hudScaleField.setText(String.valueOf(SchoolConfig.hudScale));
            this.addSelectableChild(hudScaleField);

            this.addDrawableChild(ButtonWidget.builder(Text.literal("Lưu"), button -> {
                try {
                    float scale = Float.parseFloat(hudScaleField.getText());
                    SchoolConfig.hudScale = Math.max(0.5f, Math.min(2.0f, scale));
                    SchoolConfig.saveConfig();
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu không phải số
                }
            }).dimensions(130, 120, 50, 20).build());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        // Vẽ tiêu đề tab
        context.drawCenteredTextWithBackground(this.textRenderer, 
                Text.literal("SchoolClient - Menu Cài Đặt (Nhấn N để mở/đóng)"),
                this.width / 2, 25, 0xFFFFFF, 0);
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
