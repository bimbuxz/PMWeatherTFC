package com.green_skeleton.pmweather_tfc;

import com.green_skeleton.pmweather_tfc.common.ModRecipes;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(PMWeatherTFC.MOD_ID)
public class PMWeatherTFC {

    public static final String MOD_ID = "pmweather_tfc";

    public PMWeatherTFC(net.neoforged.bus.api.IEventBus modEventBus) {
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
    }
}
