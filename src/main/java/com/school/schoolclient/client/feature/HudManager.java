package com.school.schoolclient.client.feature;

import com.school.schoolclient.client.config.SchoolConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class HudManager {
    private static int leftClickCount = 0;
    private static int rightClickCount = 0;
    private static long lastClickTime = 0;
    private static long lastCpsResetTime = 0;
    private static final long CPS_RESET_INTERVAL = 1000; // 1 giây

    public static void init() {
    }

    public static void tick() {
        // Đặt lại CPS mỗi giây
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCpsResetTime >= CPS_RESET_INTERVAL) {
            lastCpsResetTime = currentTime;
            leftClickCount = 0;
            rightClickCount = 0;
        }
    }

    /**
     * Ghi nhận bấm chuột trái
     */
    public static void recordLeftClick() {
        leftClickCount++;
    }

    /**
     * Ghi nhận bấm chuột phải
     */
    public static void recordRightClick() {
        rightClickCount++;
    }

    /**
     * Lấy CPS bấm chuột trái
     */
    public static int getLeftCPS() {
        return leftClickCount;
    }

    /**
     * Lấy CPS bấm chuột phải
     */
    public static int getRightCPS() {
        return rightClickCount;
    }

    /**
     * Lấy FPS hiện tại
     */
    public static int getFPS() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.getCurrentFps();
    }

    /**
     * Lấy Ping (độ trễ mạng)
     */
    public static int getPing() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.getNetworkHandler() != null) {
            return client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()).getLatency();
        }
        return 0;
    }

    /**
     * Lấy tọa độ X của người chơi
     */
    public static double getPlayerX() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            return client.player.getX();
        }
        return 0;
    }

    /**
     * Lấy tọa độ Y của người chơi
     */
    public static double getPlayerY() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            return client.player.getY();
        }
        return 0;
    }

    /**
     * Lấy tọa độ Z của người chơi
     */
    public static double getPlayerZ() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            return client.player.getZ();
        }
        return 0;
    }

    /**
     * Lấy hướng của người chơi (0=S, 1=SW, 2=W, 3=NW, 4=N, 5=NE, 6=E, 7=SE)
     */
    public static String getDirection() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            float yaw = client.player.getYaw() % 360;
            if (yaw < 0) yaw += 360;

            if (yaw < 22.5 || yaw >= 337.5) return "N (Bắc)";
            else if (yaw < 67.5) return "NE (Đông Bắc)";
            else if (yaw < 112.5) return "E (Đông)";
            else if (yaw < 157.5) return "SE (Đông Nam)";
            else if (yaw < 202.5) return "S (Nam)";
            else if (yaw < 247.5) return "SW (Tây Nam)";
            else if (yaw < 292.5) return "W (Tây)";
            else return "NW (Tây Bắc)";
        }
        return "N/A";
    }

    /**
     * Kiểm tra HUD có được bật hay không
     */
    public static boolean isHudEnabled() {
        return SchoolConfig.showCoordinates || SchoolConfig.showDirection ||
               SchoolConfig.showCPS || SchoolConfig.showFPS || SchoolConfig.showPing;
    }
}
