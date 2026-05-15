package com.green_skeleton.pmweather_tfc.mixin;

import com.green_skeleton.pmweather_tfc.common.TFCWindAdapter;
import dev.protomanly.pmweather.weather.WindEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WindEngine.class)
public class WindEngineMixin {

    @Inject(method = "getWind(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/Level;ZZZZZ)Lnet/minecraft/world/phys/Vec3;", at = @At("RETURN"), cancellable = true, remap = false)
    private static void injectTFCWind(
        Vec3 position,
        Level level,
        boolean ignoreStorms,
        boolean ignoreTornadoes,
        boolean windCheck,
        boolean windAnyway,
        boolean forParticles,
        CallbackInfoReturnable<Vec3> cir
    ) {
        Vec3 pmwWind = cir.getReturnValue();

        Vec3 tfcWind2D = TFCWindAdapter.getClimateWind(level, new BlockPos(
            (int) position.x,
            (int) position.y,
            (int) position.z
        ));

        Vec3 tfcWind = new Vec3(tfcWind2D.x, 0, tfcWind2D.z);

        cir.setReturnValue(pmwWind.add(tfcWind.scale(0.2)));

    }
}
