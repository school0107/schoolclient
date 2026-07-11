package com.school.schoolclient.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

public class SchoolConfig {
    // Animation Settings
    public static boolean enableAnimation = true;
    public static float animationSpeed = 1.5f;

    // Zoom Settings
    public static boolean enableZoom = true;
    public static float maxZoom = 10.0f;

    // HUD Settings
    public static boolean showFPS = true;
    public static boolean showPing = true;
    public static boolean showCPS = true;
    public static boolean showCoordinates = true;
    public static boolean showDirection = true;

    // HUD Position
    public static double hudX = 10;
    public static double hudY = 10;
    public static float hudScale = 1.0f;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
    private static final File CONFIG_FILE = CONFIG_DIR.resolve("schoolclient.json").toFile();

    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                applyConfig(data);
            } catch (Exception e) {
                e.printStackTrace();
                saveConfig();
            }
        } else {
            saveConfig();
        }
    }

    public static void saveConfig() {
        try {
            if (!CONFIG_FILE.getParentFile().exists()) {
                CONFIG_FILE.getParentFile().mkdirs();
            }

            ConfigData data = new ConfigData();
            data.enableAnimation = enableAnimation;
            data.animationSpeed = animationSpeed;
            data.enableZoom = enableZoom;
            data.maxZoom = maxZoom;
            data.showFPS = showFPS;
            data.showPing = showPing;
            data.showCPS = showCPS;
            data.showCoordinates = showCoordinates;
            data.showDirection = showDirection;
            data.hudX = hudX;
            data.hudY = hudY;
            data.hudScale = hudScale;

            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                GSON.toJson(data, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void applyConfig(ConfigData data) {
        enableAnimation = data.enableAnimation;
        animationSpeed = data.animationSpeed;
        enableZoom = data.enableZoom;
        maxZoom = data.maxZoom;
        showFPS = data.showFPS;
        showPing = data.showPing;
        showCPS = data.showCPS;
        showCoordinates = data.showCoordinates;
        showDirection = data.showDirection;
        hudX = data.hudX;
        hudY = data.hudY;
        hudScale = data.hudScale;
    }

    public static class ConfigData {
        public boolean enableAnimation = true;
        public float animationSpeed = 1.5f;
        public boolean enableZoom = true;
        public float maxZoom = 10.0f;
        public boolean showFPS = true;
        public boolean showPing = true;
        public boolean showCPS = true;
        public boolean showCoordinates = true;
        public boolean showDirection = true;
        public double hudX = 10;
        public double hudY = 10;
        public float hudScale = 1.0f;
    }
}
