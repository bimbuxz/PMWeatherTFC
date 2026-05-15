package com.green_skeleton.pmweather_tfc;

import com.green_skeleton.pmweather_tfc.common.ModRecipes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(PMWeatherTFC.MOD_ID)
public class PMWeatherTFC {

    public static final String MOD_ID = "pmweather_tfc";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PMWeatherTFC()
    {
        IEventBus bus = FMLJavaModLoadingContext
            .get()
            .getModEventBus();

        ModRecipes.SERIALIZERS.register(bus);
    }
        // Здесь будет инициализация моста, конфигов и т.д.
    }
