package com.school.schoolclient.client.event;

import com.school.schoolclient.client.feature.AnimationManager;
import com.school.schoolclient.client.feature.HudManager;
import com.school.schoolclient.client.feature.ZoomManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class TickHandler {

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            AnimationManager.tick();
            ZoomManager.tick();
            HudManager.tick();
        });
    }
}
