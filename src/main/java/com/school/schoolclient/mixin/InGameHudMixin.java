package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.HudRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void onHudRender(net.minecraft.client.gui.DrawContext context, float tickDelta, CallbackInfo ci) {
        HudRenderer.renderHud(context, tickDelta);
    }
}
