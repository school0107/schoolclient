package com.school.schoolclient.client.feature;

public class HudManager {
    private static int leftClicks = 0;
    private static int rightClicks = 0;
    private static long lastResetTime = System.currentTimeMillis();
    private static final long RESET_INTERVAL = 1000; // 1 second

    public static void init() {
        // Khởi tạo HUD Manager
    }

    public static void tick() {
        // Reset CPS mỗi giây
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTime >= RESET_INTERVAL) {
            leftClicks = 0;
            rightClicks = 0;
            lastResetTime = currentTime;
        }
    }

    public static void recordLeftClick() {
        leftClicks++;
    }

    public static void recordRightClick() {
        rightClicks++;
    }

    public static int getLeftCPS() {
        return leftClicks;
    }

    public static int getRightCPS() {
        return rightClicks;
    }
}
