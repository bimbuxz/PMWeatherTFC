package com.green_skeleton.pmweather_tfc.mixin;

import dev.protomanly.pmweather.seasons.SeasonHandler;
import net.dries007.tfc.util.calendar.Calendars;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.Month;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SeasonHandler.class)
public class SeasonHandlerMixin
{
    @Inject(method = "getDay", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tfcpmweather$getDay(Level level, CallbackInfoReturnable<Integer> cir)
    {
        ICalendar calendar = Calendars.get(level);

        cir.setReturnValue((int) calendar.getTotalCalendarDays());
    }

    @Inject(method = "getDayInMonth", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tfcpmweather$getDayInMonth(Level level, CallbackInfoReturnable<Integer> cir)
    {
        ICalendar calendar = Calendars.get(level);

        cir.setReturnValue(calendar.getCalendarDayOfMonth());
    }

    @Inject(method = "getMonth", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tfcpmweather$getMonth(Level level, CallbackInfoReturnable<Integer> cir)
    {
        ICalendar calendar = Calendars.get(level);

        Month month = ICalendar.getMonthOfYear(
            calendar.getCalendarTicks(),
            calendar.getCalendarDaysInMonth()
        );

        cir.setReturnValue(month.ordinal() + 1);
    }

    @Inject(method = "getYear", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tfcpmweather$getYear(Level level, CallbackInfoReturnable<Integer> cir)
    {
        ICalendar calendar = Calendars.get(level);

        int year = (int) (calendar.getCalendarYear() - 1000);

        cir.setReturnValue(Math.max(0, year));
    }

    @Inject(method = "getSeasonEffectSine", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tfcpmweather$getSeasonEffectSine(Level level, float offset, CallbackInfoReturnable<Float> cir)
    {
        ICalendar calendar = Calendars.get(level);

        float yearFraction = calendar.getCalendarFractionOfYear();

        float month = yearFraction * 12.0F;

        float result = Mth.sin((float)Math.PI * (month - (3.5F + offset)) / 6.0F);

        cir.setReturnValue(result);
    }
}
