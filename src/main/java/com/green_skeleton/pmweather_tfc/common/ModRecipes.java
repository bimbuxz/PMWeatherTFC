package com.green_skeleton.pmweather_tfc.common;

import com.green_skeleton.pmweather_tfc.PMWeatherTFC;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS,
            PMWeatherTFC.MOD_ID
        );

    public static final RegistryObject<RecipeSerializer<?>>
        EXTINGUISHER_REFILL_SERIALIZER =
        SERIALIZERS.register(
            "extinguisher_refill",
            ExtinguisherRefillSerializer::new
        );
}
