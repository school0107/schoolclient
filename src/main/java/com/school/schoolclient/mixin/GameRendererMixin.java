package com.school.schoolclient.mixin;

import com.school.schoolclient.client.SchoolClientClient;
import com.school.schoolclient.client.gui.ConfigScreen;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class GameRendererMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onClientTick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        // Kiểm tra phím N để mở Config Screen
        if (SchoolClientClient.CONFIG_KEY.wasPressed()) {
            client.setScreen(new ConfigScreen(client.currentScreen));
        }
    }
}
