package net.scaf.adventuresmod.events;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.scaf.adventuresmod.FewAdventuresMod;
import net.scaf.adventuresmod.entity.vanilla.TempleGuardEntity;

import static net.minecraft.world.entity.SpawnPlacements.Type.ON_GROUND;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
import static net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation.REPLACE;
import static net.scaf.adventuresmod.entity.ModEntities.*;

@Mod.EventBusSubscriber(modid = FewAdventuresMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpawnEvents {
    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent e) {
        registerSpawn(e, TEMPLE_GUARD.get(), TempleGuardEntity::templeGuardSpawnRule);
    }

    public static void registerSpawn(SpawnPlacementRegisterEvent e, EntityType<? extends Entity> type) {
        e.register(type, ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnEvents::always, REPLACE);
    }
    public static <T extends Entity> void registerSpawn(SpawnPlacementRegisterEvent e, EntityType<T> type, SpawnPlacements.SpawnPredicate<T> predicate) {
        e.register(type, ON_GROUND, MOTION_BLOCKING_NO_LEAVES, predicate, REPLACE);
    }


    public static boolean always(EntityType<? extends Entity> e, ServerLevelAccessor l, MobSpawnType t, BlockPos p, RandomSource r) {
        return true;
    }
}
