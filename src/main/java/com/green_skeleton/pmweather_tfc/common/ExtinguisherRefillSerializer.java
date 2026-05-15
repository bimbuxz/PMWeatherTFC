package com.green_skeleton.pmweather_tfc.common;

import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ExtinguisherRefillSerializer
    extends SimpleCraftingRecipeSerializer<ExtinguisherRefillRecipe>
{
    public ExtinguisherRefillSerializer()
    {
        super(ExtinguisherRefillRecipe::new);
    }
}
