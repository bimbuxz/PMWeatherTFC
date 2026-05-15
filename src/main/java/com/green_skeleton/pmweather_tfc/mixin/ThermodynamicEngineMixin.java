package com.green_skeleton.pmweather_tfc.mixin;

import dev.protomanly.pmweather.block.entity.RadarBlockEntity;
import dev.protomanly.pmweather.weather.ThermodynamicEngine;
import dev.protomanly.pmweather.weather.WeatherHandler;
import net.dries007.tfc.util.calendar.Calendars;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.climate.Climate;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThermodynamicEngine.class)
public class ThermodynamicEngineMixin {

    @Inject(
        method = "samplePoint",
        at = @At("RETURN"),
        cancellable = true,
        remap = false
    )
    private static void pmweather_tfc$overrideSamplePoint(
        WeatherHandler weatherHandler,
        Vec3 pos,
        Level level,
        RadarBlockEntity radar,
        int advance,
        CallbackInfoReturnable<ThermodynamicEngine.AtmosphericDataPoint> cir
    ) {

        ThermodynamicEngine.AtmosphericDataPoint original = cir.getReturnValue();

        BlockPos bp = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);

        ICalendar calendar = Calendars.get(level);

        float tfcTemp = Climate.getInstantTemperature(level, bp, calendar);

        float tfcRain = Climate.get(level)
            .getAverageRainfall(level, bp);

        float pmwPressure = original.pressure();
        float pmwNoise = original.virtualTemperature() - original.temperature();

        float tempNoise = pmwNoise * 0.2F;

        float temperature = tfcTemp + tempNoise;

        float humidityApprox = Mth.clamp(tfcRain / 900F, 0F, 1F);

        humidityApprox = (float)Math.pow(humidityApprox, 1.25F);

        humidityApprox = Mth.clamp(humidityApprox, 0.08F, 1F);

        float maxDepression = 18F;

        float dewDepression =
            (float)Math.pow(1F - humidityApprox, 1.15F)
                * maxDepression;

        float targetDew = temperature - dewDepression;

        float dew = Mth.lerp(0.08F, targetDew, original.dewpoint());

        dew = Math.min(dew, temperature);

        float pressure = pmwPressure;

        float virtualTemp = ThermodynamicEngine.calcVTemp(
            temperature,
            dew,
            pressure
        );

        ThermodynamicEngine.AtmosphericDataPoint rebuilt =
            new ThermodynamicEngine.AtmosphericDataPoint(
                temperature,
                dew,
                pressure,
                virtualTemp
            );

        cir.setReturnValue(rebuilt);
    }
}
