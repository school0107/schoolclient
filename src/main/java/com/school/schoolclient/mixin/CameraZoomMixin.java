package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.ZoomManager;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraZoomMixin {

    @Inject(method = "update", at = @At("TAIL"))
    private void onCameraUpdate(CallbackInfo ci) {
        // Cập nhật zoom khi camera update
        if (ZoomManager.isZoomEnabled()) {
            // Áp dụng zoom vào camera
        }
    }
}
