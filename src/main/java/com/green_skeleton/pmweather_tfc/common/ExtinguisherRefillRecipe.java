package com.green_skeleton.pmweather_tfc.common;

import dev.protomanly.pmweather.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;

public class ExtinguisherRefillRecipe extends CustomRecipe
{
    public ExtinguisherRefillRecipe(CraftingBookCategory category)
    {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level)
    {
        boolean foundBucket = false;
        boolean foundExtinguisher = false;

        for (int i = 0; i < input.size(); i++)
        {
            ItemStack stack = input.getItem(i);

            if (stack.isEmpty())
                continue;

            if (isLimewaterBucket(stack))
            {
                if (foundBucket)
                    return false;

                foundBucket = true;
            }

            else if (stack.is(ModItems.FIRE_EXTINGUISHER.get()))
            {
                if (foundExtinguisher)
                    return false;

                foundExtinguisher = true;
            }

            else
            {
                return false;
            }
        }

        return foundBucket && foundExtinguisher;
    }


    private static final ResourceLocation LIMEWATER =
        ResourceLocation.parse("tfc:limewater");

    private boolean isLimewaterBucket(ItemStack stack)
    {
        if (stack.isEmpty())
            return false;

        var handler = stack.getCapability(Capabilities.FluidHandler.ITEM);

        if (handler == null)
            return false;

        var fluid = handler.getFluidInTank(0);

        return !fluid.isEmpty()
            && fluid.getFluid().builtInRegistryHolder().key().location().equals(LIMEWATER);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider)
    {
        for (int i = 0; i < input.size(); i++)
        {
            ItemStack stack = input.getItem(i);

            if (stack.is(ModItems.FIRE_EXTINGUISHER.get()))
            {
                ItemStack result = stack.copy();

                result.setDamageValue(0);

                return result;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.EXTINGUISHER_REFILL_SERIALIZER.get();
    }
}
