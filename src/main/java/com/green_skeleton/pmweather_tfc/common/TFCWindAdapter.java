package com.green_skeleton.pmweather_tfc.common;

import net.dries007.tfc.util.climate.Climate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class TFCWindAdapter {

    public static Vec3 getClimateWind(Level level, BlockPos pos) {

        Vec2 tfc = Climate.get(level).getWind(level, pos);
        return new Vec3(tfc.x, 0.0, tfc.y);
    }

    public static Vec3 mix(Vec3 pmwWind, Vec3 tfcWind) {
        return pmwWind.add(tfcWind.scale(0.2));
    }
}
