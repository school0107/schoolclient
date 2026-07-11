package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.HudManager;
import com.school.schoolclient.client.feature.HudRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void onRenderHead(float tickDelta, long nanoTime, boolean tick, CallbackInfo ci) {
        // Chuẩn bị trước khi render
        HudManager.tick();
    }
}
