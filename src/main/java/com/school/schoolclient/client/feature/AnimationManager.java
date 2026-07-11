package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AnimationManager {
    private static float currentAnimationSpeed = SchoolConfig.animationSpeed;

    public static void init() {
    }

    public static void setLegacyAnimations(boolean enabled) {
        SchoolConfig.enableLegacyAnimations = enabled;
        SchoolConfig.saveConfig();
    }

    public static void setAnimationSpeed(float speed) {
        SchoolConfig.animationSpeed = Math.max(0.1f, Math.min(2.0f, speed));
        currentAnimationSpeed = SchoolConfig.animationSpeed;
        SchoolConfig.saveConfig();
    }

    public static float getAnimationSpeed() {
        return currentAnimationSpeed;
    }

    public static boolean isLegacyAnimationsEnabled() {
        return SchoolConfig.enableLegacyAnimations;
    }

    /**
     * Áp dụng hiệu ứng hoạt ảnh kiếm 1.7 (cũ)
     */
    public static float applyLegacySwingAnimation(float swingProgress) {
        if (!SchoolConfig.enableLegacyAnimations) {
            return swingProgress;
        }
        // Animation swing 1.7 style - smoother arc
        float f = swingProgress * (float) Math.PI;
        return -((float) Math.cos(f + 1.5707963267948966D)) * 0.5f + 0.5f;
    }

    /**
     * Điều chỉnh tốc độ hoạt ảnh
     */
    public static float modifyAnimationTick(float tick) {
        return tick * currentAnimationSpeed;
    }
}
