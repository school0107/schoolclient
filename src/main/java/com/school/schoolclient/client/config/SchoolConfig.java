package com.school.schoolclient.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SchoolConfig {
    private static final Path CONFIG_PATH = Paths.get("config/schoolclient.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Animation Settings
    public static boolean enableLegacyAnimations = false;
    public static float animationSpeed = 1.0f;

    // Zoom Settings
    public static boolean enableZoom = true;
    public static float maxZoom = 8.0f;
    public static float zoomSensitivity = 0.1f;

    // HUD Settings
    public static boolean showCoordinates = true;
    public static boolean showDirection = true;
    public static boolean showCPS = true;
    public static boolean showFPS = true;
    public static boolean showPing = true;
    public static int hudX = 10;
    public static int hudY = 10;
    public static float hudScale = 1.0f;
    public static int hudColor = 0xFFFFFF;

    public static void loadConfig() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String content = Files.readString(CONFIG_PATH);
                ConfigData data = GSON.fromJson(content, ConfigData.class);
                applyConfig(data);
            } else {
                saveConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            ConfigData data = new ConfigData();
            String json = GSON.toJson(data);
            Files.writeString(CONFIG_PATH, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void applyConfig(ConfigData data) {
        enableLegacyAnimations = data.enableLegacyAnimations;
        animationSpeed = data.animationSpeed;
        enableZoom = data.enableZoom;
        maxZoom = data.maxZoom;
        zoomSensitivity = data.zoomSensitivity;
        showCoordinates = data.showCoordinates;
        showDirection = data.showDirection;
        showCPS = data.showCPS;
        showFPS = data.showFPS;
        showPing = data.showPing;
        hudX = data.hudX;
        hudY = data.hudY;
        hudScale = data.hudScale;
        hudColor = data.hudColor;
    }

    public static class ConfigData {
        public boolean enableLegacyAnimations = SchoolConfig.enableLegacyAnimations;
        public float animationSpeed = SchoolConfig.animationSpeed;
        public boolean enableZoom = SchoolConfig.enableZoom;
        public float maxZoom = SchoolConfig.maxZoom;
        public float zoomSensitivity = SchoolConfig.zoomSensitivity;
        public boolean showCoordinates = SchoolConfig.showCoordinates;
        public boolean showDirection = SchoolConfig.showDirection;
        public boolean showCPS = SchoolConfig.showCPS;
        public boolean showFPS = SchoolConfig.showFPS;
        public boolean showPing = SchoolConfig.showPing;
        public int hudX = SchoolConfig.hudX;
        public int hudY = SchoolConfig.hudY;
        public float hudScale = SchoolConfig.hudScale;
        public int hudColor = SchoolConfig.hudColor;
    }
}
