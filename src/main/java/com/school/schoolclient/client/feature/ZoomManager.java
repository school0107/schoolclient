package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ZoomManager {
    private static float currentZoom = 1.0f;
    private static float targetZoom = 1.0f;
    private static final float ZOOM_SMOOTHNESS = 0.1f;

    public static void init() {
    }

    public static void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Làm mịn quá trình phóng to
        currentZoom += (targetZoom - currentZoom) * ZOOM_SMOOTHNESS;
    }

    /**
     * Bắt đầu phóng to
     */
    public static void startZoom() {
        if (SchoolConfig.enableZoom) {
            targetZoom = SchoolConfig.maxZoom;
        }
    }

    /**
     * Dừng phóng to
     */
    public static void stopZoom() {
        targetZoom = 1.0f;
    }

    /**
     * Điều chỉnh độ nhạy chuột khi phóng to
     */
    public static float getMouseSensitivity() {
        return 1.0f / currentZoom;
    }

    /**
     * Lấy giá trị phóng to hiện tại
     */
    public static float getCurrentZoom() {
        return currentZoom;
    }

    /**
     * Đặt độ phóng to tối đa
     */
    public static void setMaxZoom(float zoom) {
        SchoolConfig.maxZoom = Math.max(1.0f, Math.min(16.0f, zoom));
        SchoolConfig.saveConfig();
    }

    /**
     * Thay đổi độ nhạy zoom
     */
    public static void setZoomSensitivity(float sensitivity) {
        SchoolConfig.zoomSensitivity = Math.max(0.01f, Math.min(1.0f, sensitivity));
        SchoolConfig.saveConfig();
    }

    public static boolean isZoomEnabled() {
        return SchoolConfig.enableZoom;
    }
}
