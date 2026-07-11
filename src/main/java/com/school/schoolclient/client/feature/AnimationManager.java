package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;

public class AnimationManager {
    private static float swingProgress = 0.0f;
    private static boolean swingInProgress = false;

    public static void init() {
        // Khởi tạo Animation Manager
    }

    public static void tick() {
        // Cập nhật animation mỗi tick
    }

    public static float applyLegacySwingAnimation(float progress) {
        if (!SchoolConfig.enableAnimation) {
            return progress;
        }

        // Áp dụng hoạt ảnh 1.7 style
        float speed = SchoolConfig.animationSpeed;
        return progress * speed;
    }

    public static void setSwingProgress(float progress) {
        swingProgress = progress;
    }

    public static float getSwingProgress() {
        return swingProgress;
    }
}
