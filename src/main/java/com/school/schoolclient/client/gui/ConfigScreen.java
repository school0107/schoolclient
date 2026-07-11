package com.school.schoolclient.client.gui;

import com.school.schoolclient.client.config.SchoolConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private int currentTab = 0; // 0: Animation, 1: Zoom, 2: HUD

    public ConfigScreen(Screen parent) {
        super(Text.literal("School Client Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        this.clearChildren();

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Tab buttons
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Animation"), button -> currentTab = 0)
                .dimensions(centerX - 150, centerY - 100, 100, 20)
                .build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Zoom"), button -> currentTab = 1)
                .dimensions(centerX - 25, centerY - 100, 100, 20)
                .build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("HUD"), button -> currentTab = 2)
                .dimensions(centerX + 100, centerY - 100, 100, 20)
                .build());

        // Back button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Back"), button -> this.onClose())
                .dimensions(centerX - 50, centerY + 100, 100, 20)
                .build());

        // Draw tab content based on currentTab
        switch (currentTab) {
            case 0 -> initAnimationTab(centerX, centerY);
            case 1 -> initZoomTab(centerX, centerY);
            case 2 -> initHudTab(centerX, centerY);
        }
    }

    private void initAnimationTab(int centerX, int centerY) {
        // Animation toggle
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Animation: " + (SchoolConfig.enableAnimation ? "ON" : "OFF")),
                button -> {
                    SchoolConfig.enableAnimation = !SchoolConfig.enableAnimation;
                    SchoolConfig.saveConfig();
                    this.init(this.client, this.width, this.height);
                })
                .dimensions(centerX - 75, centerY - 40, 150, 20)
                .build());

        // Speed increase/decrease
        this.addDrawableChild(ButtonWidget.builder(Text.literal("-"), button -> {
            SchoolConfig.animationSpeed = Math.max(0.5f, SchoolConfig.animationSpeed - 0.1f);
            SchoolConfig.saveConfig();
            this.init(this.client, this.width, this.height);
        }).dimensions(centerX - 75, centerY, 70, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("+"), button -> {
            SchoolConfig.animationSpeed = Math.min(3.0f, SchoolConfig.animationSpeed + 0.1f);
            SchoolConfig.saveConfig();
            this.init(this.client, this.width, this.height);
        }).dimensions(centerX + 5, centerY, 70, 20).build());
    }

    private void initZoomTab(int centerX, int centerY) {
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Zoom: " + (SchoolConfig.enableZoom ? "ON" : "OFF")),
                button -> {
                    SchoolConfig.enableZoom = !SchoolConfig.enableZoom;
                    SchoolConfig.saveConfig();
                    this.init(this.client, this.width, this.height);
                })
                .dimensions(centerX - 75, centerY - 40, 150, 20)
                .build());
    }

    private void initHudTab(int centerX, int centerY) {
        int y = centerY - 40;
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("FPS: " + (SchoolConfig.showFPS ? "ON" : "OFF")),
                button -> {
                    SchoolConfig.showFPS = !SchoolConfig.showFPS;
                    SchoolConfig.saveConfig();
                })
                .dimensions(centerX - 75, y, 150, 20)
                .build());

        y += 25;
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Ping: " + (SchoolConfig.showPing ? "ON" : "OFF")),
                button -> {
                    SchoolConfig.showPing = !SchoolConfig.showPing;
                    SchoolConfig.saveConfig();
                })
                .dimensions(centerX - 75, y, 150, 20)
                .build());

        y += 25;
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("CPS: " + (SchoolConfig.showCPS ? "ON" : "OFF")),
                button -> {
                    SchoolConfig.showCPS = !SchoolConfig.showCPS;
                    SchoolConfig.saveConfig();
                })
                .dimensions(centerX - 75, y, 150, 20)
                .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithBackground(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF, 0x000000);
    }

    @Override
    public void onClose() {
        this.client.setScreen(this.parent);
    }
}
