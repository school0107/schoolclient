package com.school.schoolclient.mixin;

import com.school.schoolclient.client.feature.AnimationManager;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public class AnimationMixin {

    @Inject(method = "getHandSwingProgress", at = @At("RETURN"), cancellable = true)
    private void onGetHandSwingProgress(float tickDelta, CallbackInfoReturnable<Float> cir) {
        // Áp dụng animation modifier
        float original = cir.getReturnValue();
        float modified = AnimationManager.applyLegacySwingAnimation(original);
        cir.setReturnValue(modified);
    }
}
