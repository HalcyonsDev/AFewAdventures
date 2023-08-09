package net.scaf.adventuresmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier MYTHRILE = new ForgeTier(3,2400,1.5f,
            2f,5, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.MYTHRILE_INGOT.get()));

    public static final ForgeTier MYTHRITE = new ForgeTier(3,2400,1.5f,
            2f,5, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.MYTHRITE_INGOT.get()));
}
