package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.AnimationManager;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HeldItemRenderer.class)
public class AnimationMixin {

    @Inject(method = "getSwingProgress", at = @At("RETURN"), cancellable = true)
    private void onSwingAnimation(float tickDelta, CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValue();
        float modified = AnimationManager.applyLegacySwingAnimation(original);
        cir.setReturnValue(modified);
    }

    @Inject(method = "updateHeldItemStacks", at = @At("HEAD"))
    private void onUpdateAnimation(CallbackInfoReturnable<Void> cir) {
        // Cập nhật tốc độ hoạt ảnh
        AnimationManager.tick();
    }
}
