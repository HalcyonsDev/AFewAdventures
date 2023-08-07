package net.scaf.adventuresmod.entity.vanilla;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.scaf.adventuresmod.entity.base.EntityAdventuresMonster;

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
}
