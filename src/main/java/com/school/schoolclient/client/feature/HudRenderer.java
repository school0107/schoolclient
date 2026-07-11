package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;

public class HudRenderer {
    private static final int COLOR_WHITE = 0xFFFFFF;
    private static final int COLOR_YELLOW = 0xFFFF00;
    private static final int COLOR_GREEN = 0x00FF00;

    public static void renderHud(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int x = (int) SchoolConfig.hudX;
        int y = (int) SchoolConfig.hudY;
        float scale = SchoolConfig.hudScale;

        context.getMatrices().push();
        context.getMatrices().scale(scale, scale, 1.0f);

        // FPS
        if (SchoolConfig.showFPS) {
            String fpsText = "FPS: " + client.getCurrentFps();
            context.drawTextWithBackground(client.textRenderer, fpsText, x, y, COLOR_YELLOW, 0x000000);
            y += 10;
        }

        // Ping
        if (SchoolConfig.showPing) {
            int ping = client.player.networkHandler.getPlayerListEntry(client.player.getUuid()).getLatency();
            String pingText = "Ping: " + ping + "ms";
            context.drawTextWithBackground(client.textRenderer, pingText, x, y, COLOR_YELLOW, 0x000000);
            y += 10;
        }

        // CPS
        if (SchoolConfig.showCPS) {
            String cpsText = "CPS: L" + HudManager.getLeftCPS() + " R" + HudManager.getRightCPS();
            context.drawTextWithBackground(client.textRenderer, cpsText, x, y, COLOR_GREEN, 0x000000);
            y += 10;
        }

        // Coordinates
        if (SchoolConfig.showCoordinates) {
            ClientPlayerEntity player = client.player;
            String coordText = String.format("X: %.1f Y: %.1f Z: %.1f", 
                    player.getX(), player.getY(), player.getZ());
            context.drawTextWithBackground(client.textRenderer, coordText, x, y, COLOR_WHITE, 0x000000);
            y += 10;
        }

        // Direction
        if (SchoolConfig.showDirection) {
            String direction = getDirection(client.player.getYaw());
            String dirText = "Direction: " + direction;
            context.drawTextWithBackground(client.textRenderer, dirText, x, y, COLOR_WHITE, 0x000000);
        }

        context.getMatrices().pop();
    }

    private static String getDirection(float yaw) {
        yaw = yaw % 360;
        if (yaw < 0) yaw += 360;

        if (yaw >= 315 || yaw < 45) return "South";
        if (yaw >= 45 && yaw < 135) return "West";
        if (yaw >= 135 && yaw < 225) return "North";
        return "East";
    }
}
