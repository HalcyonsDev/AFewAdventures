package net.scaf.adventuresmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CrystalLampBlock extends Block {
    public static final EnumProperty<CrystalsEnum> LEVEL = EnumProperty.create("level", CrystalsEnum.class);

    public CrystalLampBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {

        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND)  {
            switch (blockState.getValue(LEVEL)) {
                case GLOWSTONE -> {
                    level.setBlockAndUpdate(blockPos, blockState.setValue(LEVEL, CrystalsEnum.EMPTY));
                    dropLevelItemStack(level, blockPos, Items.GLOWSTONE_DUST.getDefaultInstance());
                }

                case REDSTONE -> {
                    level.setBlockAndUpdate(blockPos, blockState.setValue(LEVEL, CrystalsEnum.EMPTY));
                    dropLevelItemStack(level, blockPos, Items.REDSTONE.getDefaultInstance());
                }

                case DIAMOND -> {
                    level.setBlockAndUpdate(blockPos, blockState.setValue(LEVEL, CrystalsEnum.EMPTY));
                    dropLevelItemStack(level, blockPos, Items.DIAMOND.getDefaultInstance());
                }

                case LAPIS_LAZULI -> {
                    level.setBlockAndUpdate(blockPos, blockState.setValue(LEVEL, CrystalsEnum.EMPTY));
                    dropLevelItemStack(level, blockPos, Items.LAPIS_LAZULI.getDefaultInstance());
                }

                case EMERALD -> {
                    level.setBlockAndUpdate(blockPos, blockState.setValue(LEVEL, CrystalsEnum.EMPTY));
                    dropLevelItemStack(level, blockPos, Items.EMERALD.getDefaultInstance());
                }
            }

            Item playerMainHandItem = player.getMainHandItem().getItem();

            if (playerMainHandItem == Items.GLOWSTONE_DUST) {
                player.getMainHandItem().split(1);
                level.setBlock(blockPos, blockState.setValue(LEVEL, CrystalsEnum.GLOWSTONE), 0);
            } else if (playerMainHandItem == Items.REDSTONE) {
                player.getMainHandItem().split(1);
                level.setBlock(blockPos, blockState.setValue(LEVEL, CrystalsEnum.REDSTONE), 0);
            } else if (playerMainHandItem == Items.DIAMOND) {
                player.getMainHandItem().split(1);
                level.setBlock(blockPos, blockState.setValue(LEVEL, CrystalsEnum.DIAMOND), 0);
            } else if (playerMainHandItem == Items.LAPIS_LAZULI) {
                player.getMainHandItem().split(1);
                level.setBlock(blockPos, blockState.setValue(LEVEL, CrystalsEnum.LAPIS_LAZULI), 0);
            } else if (playerMainHandItem == Items.EMERALD) {
                player.getMainHandItem().split(1);
                level.setBlock(blockPos, blockState.setValue(LEVEL, CrystalsEnum.EMERALD), 0);
            }

        }

        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockState, Level level, BlockPos blockPos, Player player, boolean willHarvest, FluidState fluid) {
        dropLevelItemStack(level, blockPos, new ItemStack(blockState.setValue(LEVEL, CrystalsEnum.EMPTY).getBlock()));

        switch (blockState.getValue(LEVEL)) {
            case GLOWSTONE -> dropLevelItemStack(level, blockPos, Items.GLOWSTONE_DUST.getDefaultInstance());
            case REDSTONE -> dropLevelItemStack(level, blockPos, Items.REDSTONE.getDefaultInstance());
            case DIAMOND -> dropLevelItemStack(level, blockPos, Items.DIAMOND.getDefaultInstance());
            case LAPIS_LAZULI -> dropLevelItemStack(level, blockPos, Items.LAPIS_LAZULI.getDefaultInstance());
            case EMERALD -> dropLevelItemStack(level, blockPos, Items.EMERALD.getDefaultInstance());
        }

        return super.onDestroyedByPlayer(blockState, level, blockPos, player, willHarvest, fluid);
    }

    private void dropLevelItemStack(Level level, BlockPos blockPos, ItemStack itemStack) {
        double d = (double)(level.random.nextFloat() * 0.7f) + (double)0.15f;
        double e = (double)(level.random.nextFloat() * 0.7f) + 0.06000000238418579 + 0.6;
        double g = (double)(level.random.nextFloat() * 0.7f) + (double)0.15f;

        Containers.dropItemStack(level, (double)blockPos.getX() + d,
                (double)blockPos.getY() + e, (double)blockPos.getZ() + g, itemStack);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
        p_48725_.add(LEVEL);
    }
}
