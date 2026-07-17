package org.mariomastr.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.level.ServerExplosion;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerExplosion.class)
public abstract class ServerExplosionMixin {
	@Shadow
	@Final
	private @Nullable Entity source;

	@ModifyVariable(method = "hurtEntities", at = @At("STORE"), order = 1, name = "knockbackResistance")
	private double knockbackResistance(double knockbackResistance) {
        assert source != null;
		if (source instanceof WindCharge) {
			return 0;
		}
		else {
			return knockbackResistance;
		}
    }
}