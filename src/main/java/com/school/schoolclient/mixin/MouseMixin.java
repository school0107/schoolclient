package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.HudManager;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        // Theo dõi nhấp chuột trái (button = 0)
        if (button == 0 && action == 1) {
            HudManager.recordLeftClick();
        }
        // Theo dõi nhấp chuột phải (button = 1)
        else if (button == 1 && action == 1) {
            HudManager.recordRightClick();
        }
    }
}
