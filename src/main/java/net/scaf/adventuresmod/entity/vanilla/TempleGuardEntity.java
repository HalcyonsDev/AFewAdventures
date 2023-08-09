package net.scaf.adventuresmod.entity.vanilla;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.scaf.adventuresmod.entity.base.EntityAdventuresMonster;

import java.util.Random;

public class TempleGuardEntity extends EntityAdventuresMonster {

    public TempleGuardEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return 1.74F;
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    public static boolean templeGuardSpawnRule(EntityType<? extends Mob> typeIn, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, RandomSource randomIn) {
        return worldIn.getLightEmission(pos) < 8 && pos.getY() < 0;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_21385_, int p_21386_, boolean p_21387_) {
        if (!this.level.isClientSide) {
            Random random = new Random();
            int result = random.nextInt(0, 2);

            if (result == 0) {
                ItemStack droppedItem = new ItemStack(Items.BONE, 2);
                this.spawnAtLocation(droppedItem);
            } else {
                ItemStack boneItem = new ItemStack(Items.BONE, 1);
                ItemStack mossBlock = new ItemStack(Blocks.MOSS_BLOCK, 1);

                this.spawnAtLocation(boneItem);
                this.spawnAtLocation(mossBlock);
            }

        }
    }
}
