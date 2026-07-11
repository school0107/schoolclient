package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

@Environment(EnvType.CLIENT)
public class HudRenderer {

    /**
     * Vẽ HUD trên màn hình
     */
    public static void renderHud(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || !HudManager.isHudEnabled()) {
            return;
        }

        int y = SchoolConfig.hudY;
        int textColor = SchoolConfig.hudColor;
        float scale = SchoolConfig.hudScale;

        context.getMatrices().push();
        context.getMatrices().scale(scale, scale, 1.0f);

        // Hiển thị FPS
        if (SchoolConfig.showFPS) {
            String fpsText = "FPS: " + HudManager.getFPS();
            context.drawTextWithBackground(client.textRenderer, fpsText, SchoolConfig.hudX, y, textColor, 0);
            y += 12;
        }

        // Hiển thị Ping
        if (SchoolConfig.showPing) {
            String pingText = "Ping: " + HudManager.getPing() + "ms";
            context.drawTextWithBackground(client.textRenderer, pingText, SchoolConfig.hudX, y, textColor, 0);
            y += 12;
        }

        // Hiển thị CPS
        if (SchoolConfig.showCPS) {
            String cpsText = "CPS: L" + HudManager.getLeftCPS() + " R" + HudManager.getRightCPS();
            context.drawTextWithBackground(client.textRenderer, cpsText, SchoolConfig.hudX, y, textColor, 0);
            y += 12;
        }

        // Hiển thị Tọa độ
        if (SchoolConfig.showCoordinates) {
            String coordText = String.format("XYZ: %.1f / %.1f / %.1f",
                    HudManager.getPlayerX(),
                    HudManager.getPlayerY(),
                    HudManager.getPlayerZ());
            context.drawTextWithBackground(client.textRenderer, coordText, SchoolConfig.hudX, y, textColor, 0);
            y += 12;
        }

        // Hiển thị Hướng
        if (SchoolConfig.showDirection) {
            String directionText = "Hướng: " + HudManager.getDirection();
            context.drawTextWithBackground(client.textRenderer, directionText, SchoolConfig.hudX, y, textColor, 0);
        }

        context.getMatrices().pop();
    }
}
