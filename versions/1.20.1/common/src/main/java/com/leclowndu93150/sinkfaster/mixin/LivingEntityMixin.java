package com.leclowndu93150.sinkfaster.mixin;

import com.leclowndu93150.sinkfaster.SinkFasterConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "goDownInWater", at = @At("HEAD"), cancellable = true)
    private void sinkfaster$modifyGoDownInWater(CallbackInfo ci) {
        SinkFasterConfig config = SinkFasterConfig.get();
        if (!config.enabled) return;

        LivingEntity self = (LivingEntity) (Object) this;
        if (config.onlyPlayers && !(self instanceof Player)) return;

        double sinkSpeed = -0.04 * config.sinkSpeedMultiplier;
        self.setDeltaMovement(self.getDeltaMovement().add(0.0, sinkSpeed, 0.0));
        ci.cancel();
    }

    @Inject(method = "getFluidFallingAdjustedMovement", at = @At("HEAD"), cancellable = true)
    private void sinkfaster$modifyFluidFalling(double gravity, boolean isFalling, Vec3 deltaMovement, CallbackInfoReturnable<Vec3> cir) {
        SinkFasterConfig config = SinkFasterConfig.get();
        if (!config.enabled) return;
        if (config.onlyWhenSneaking) return;

        LivingEntity self = (LivingEntity) (Object) this;
        if (config.onlyPlayers && !(self instanceof Player)) return;
        if (self.isNoGravity() || self.isSprinting() || ((LivingEntityAccessor) self).sinkfaster$isJumping()) return;
        if (!self.isInWater()) return;

        double multiplier = config.sinkSpeedMultiplier;
        double adjustedGravity = gravity * multiplier;

        double d1;
        if (isFalling && Math.abs(deltaMovement.y - 0.005) >= 0.003 && Math.abs(deltaMovement.y - adjustedGravity / 16.0) < 0.003) {
            d1 = -0.003;
        } else {
            d1 = deltaMovement.y - adjustedGravity / 16.0;
        }

        cir.setReturnValue(new Vec3(deltaMovement.x, d1, deltaMovement.z));
    }
}
