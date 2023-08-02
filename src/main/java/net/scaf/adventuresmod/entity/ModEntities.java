package net.scaf.adventuresmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scaf.adventuresmod.FewAdventuresMod;
import net.scaf.adventuresmod.entity.custom.BulletEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FewAdventuresMod.MOD_ID);
    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITIES.register("bullet", () -> EntityType.Builder
            .<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.3125f, 0.3125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
            .build(FewAdventuresMod.MOD_ID + ":bullet"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
