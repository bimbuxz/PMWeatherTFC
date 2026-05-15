package com.green_skeleton.pmweather_tfc.common;

import com.green_skeleton.pmweather_tfc.PMWeatherTFC;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
        DeferredRegister.create(
            BuiltInRegistries.RECIPE_SERIALIZER,
            PMWeatherTFC.MOD_ID
        );

    public static final DeferredHolder<
        RecipeSerializer<?>,
        ExtinguisherRefillSerializer
        > EXTINGUISHER_REFILL_SERIALIZER =
        RECIPE_SERIALIZERS.register(
            "extinguisher_refill",
            ExtinguisherRefillSerializer::new
        );
}
