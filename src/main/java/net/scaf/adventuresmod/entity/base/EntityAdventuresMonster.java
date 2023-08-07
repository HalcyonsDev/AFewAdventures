package net.scaf.adventuresmod.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.Tags;

public class EntityAdventuresMonster extends Monster {
    protected EntityAdventuresMonster(EntityType<? extends Monster> type, Level world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));

        if(isAggressive()) {
            goalSelector.addGoal(0, new MeleeAttackGoal(this, 1, true));
            targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
            goalSelector.addGoal(3, new PanicGoal(this, 1.25D));
        }
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType type) {
        return !level.getBiome(blockPosition()).is(Tags.Biomes.IS_MUSHROOM);
    }
}
