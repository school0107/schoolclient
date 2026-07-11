package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.ZoomManager;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class CameraZoomMixin {

    @Inject(method = "updateTargetedEntity", at = @At("HEAD"))
    private void onUpdateCamera(float tickDelta, CallbackInfo ci) {
        // Áp dụng zoom vào FOV
        if (ZoomManager.isZoomEnabled()) {
            float zoom = ZoomManager.getCurrentZoom();
            // Zoom sẽ được áp dụng thông qua FOV multiplier
        }
    }
}
