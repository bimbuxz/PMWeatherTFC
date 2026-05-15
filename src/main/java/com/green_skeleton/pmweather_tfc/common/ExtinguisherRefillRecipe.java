package com.green_skeleton.pmweather_tfc.common;

import dev.protomanly.pmweather.item.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ExtinguisherRefillRecipe extends CustomRecipe
{
    public ExtinguisherRefillRecipe(ResourceLocation id, CraftingBookCategory category)
    {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level)
    {
        boolean foundBucket = false;
        boolean foundExtinguisher = false;

        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);

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

    private boolean isLimewaterBucket(ItemStack stack)
    {
        String itemId = stack.getItemHolder()
            .unwrapKey()
            .get()
            .location()
            .toString();

        if (!itemId.contains("bucket"))
            return false;

        CompoundTag tag = stack.getTag();

        if (tag == null || !tag.contains("fluid"))
            return false;

        CompoundTag fluid = tag.getCompound("fluid");

        String fluidName = fluid.getString("FluidName");

        return fluidName.equals("tfc:limewater");
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess access)
    {
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);

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
