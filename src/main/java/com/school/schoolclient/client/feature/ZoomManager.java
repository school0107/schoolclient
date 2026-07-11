package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ZoomManager {
    private static float currentZoom = 1.0f;
    private static float targetZoom = 1.0f;
    private static final float ZOOM_MIN = 1.0f;
    private static final float ZOOM_MAX = 10.0f;
    private static final float ZOOM_SPEED = 0.1f;

    public static void init() {
        // Khởi tạo Zoom Manager
    }

    public static void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Kiểm tra phím Ctrl+C để reset zoom
        if (isKeyPressed(GLFW.GLFW_KEY_C) && isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL)) {
            targetZoom = 1.0f;
        }

        // Mượt mà zoom từ current sang target
        if (Math.abs(currentZoom - targetZoom) > 0.01f) {
            currentZoom += (targetZoom - currentZoom) * 0.1f;
        } else {
            currentZoom = targetZoom;
        }

        // Clamp zoom value
        currentZoom = Math.max(ZOOM_MIN, Math.min(ZOOM_MAX, currentZoom));
    }

    public static void handleMouseScroll(double amount) {
        if (!SchoolConfig.enableZoom) return;

        targetZoom += (float) amount * ZOOM_SPEED;
        targetZoom = Math.max(ZOOM_MIN, Math.min(ZOOM_MAX, targetZoom));
    }

    public static float getCurrentZoom() {
        return currentZoom;
    }

    public static boolean isZoomEnabled() {
        return SchoolConfig.enableZoom && currentZoom > 1.0f;
    }

    private static boolean isKeyPressed(int key) {
        long window = MinecraftClient.getInstance().getWindow().getHandle();
        return GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
    }
}
