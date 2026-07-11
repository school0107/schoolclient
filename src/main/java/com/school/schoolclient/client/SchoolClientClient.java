package com.school.schoolclient.client;

import com.school.schoolclient.SchoolClientMod;
import com.school.schoolclient.client.config.SchoolConfig;
import com.school.schoolclient.client.feature.AnimationManager;
import com.school.schoolclient.client.feature.HudManager;
import com.school.schoolclient.client.feature.ZoomManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class SchoolClientClient implements ClientModInitializer {
    public static KeyBinding ZOOM_KEY;
    public static KeyBinding CONFIG_KEY;

    @Override
    public void onInitializeClient() {
        SchoolClientMod.LOGGER.info("SchoolClient Client initialized!");

        // Load configuration
        SchoolConfig.loadConfig();

        // Initialize key bindings
        ZOOM_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.schoolclient.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.schoolclient"
        ));

        CONFIG_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.schoolclient.config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "category.schoolclient"
        ));

        // Register feature managers
        AnimationManager.init();
        ZoomManager.init();
        HudManager.init();

        // Register client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                ZoomManager.tick();
                HudManager.tick();
            }
        });
    }
}
