package com.school.schoolclient.client.event;

import com.school.schoolclient.client.feature.ZoomManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class MouseScrollHandler {

    public static void init() {
        // Xử lý scroll event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Scroll sẽ được xử lý thông qua mixin hoặc event
        });
    }

    public static void handleScroll(double amount) {
        ZoomManager.handleMouseScroll(amount);
    }
}
