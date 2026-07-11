package com.school.schoolclient.client.event;

import com.school.schoolclient.client.gui.ConfigScreen;
import com.school.schoolclient.client.SchoolClientClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class KeyBindingHandler {

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (SchoolClientClient.CONFIG_KEY.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ConfigScreen(MinecraftClient.getInstance().currentScreen));
            }
        });
    }
}
