package com.green_skeleton.pmweather_tfc.common;

import com.google.gson.JsonObject;
import com.green_skeleton.pmweather_tfc.common.ExtinguisherRefillRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ExtinguisherRefillSerializer
    implements RecipeSerializer<ExtinguisherRefillRecipe>
{
    @Override
    public ExtinguisherRefillRecipe fromJson(ResourceLocation id, JsonObject json)
    {
        return new ExtinguisherRefillRecipe(id, CraftingBookCategory.MISC);
    }

    @Override
    public ExtinguisherRefillRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf)
    {
        return new ExtinguisherRefillRecipe(id, CraftingBookCategory.MISC);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, ExtinguisherRefillRecipe recipe)
    {

    }
}
