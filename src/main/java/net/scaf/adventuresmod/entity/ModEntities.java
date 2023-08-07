package net.scaf.adventuresmod.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scaf.adventuresmod.FewAdventuresMod;
import net.scaf.adventuresmod.client.models.vanilla.TempleGuardModel;
import net.scaf.adventuresmod.client.renders.base.RenderAdventuresMob;
import net.scaf.adventuresmod.entity.projectile.BulletEntity;
import net.scaf.adventuresmod.entity.vanilla.TempleGuardEntity;
import net.scaf.adventuresmod.enums.EntityStats;
import net.scaf.adventuresmod.item.ModItems;

@Mod.EventBusSubscriber(modid = FewAdventuresMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FewAdventuresMod.MOD_ID);
    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITIES.register("bullet", () -> EntityType.Builder
            .<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.3125f, 0.3125f).setUpdateInterval(2).setTrackingRange(64).setShouldReceiveVelocityUpdates(true)
            .build(FewAdventuresMod.MOD_ID + ":bullet"));

    public static final RegistryObject<EntityType<TempleGuardEntity>> TEMPLE_GUARD = registerEntity(TempleGuardEntity::new, "temple_guard", 0.6F, 2.0F,0x4095da, 0x407eb9);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, float width, float height) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, MobCategory.MONSTER).sized(width, height).build(new ResourceLocation(FewAdventuresMod.MOD_ID, name).toString()));
    }
    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, float width, float height, MobCategory category) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(new ResourceLocation(FewAdventuresMod.MOD_ID, name).toString()));
    }
    private static <T extends Mob> RegistryObject<EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, float width, float height, int backgroundColor, int highlightColor) {
        return registerEntity(factory, name, width, height, backgroundColor, highlightColor, MobCategory.MONSTER);
    }
    private static <T extends Mob> RegistryObject<EntityType<T>> registerEntity(EntityType.EntityFactory<T> factory, String name, float width, float height, int backgroundColor, int highlightColor, MobCategory category) {
        RegistryObject<EntityType<T>> entity = ENTITIES.register(name, () -> EntityType.Builder.of(factory, category).sized(width, height).build(new ResourceLocation(FewAdventuresMod.MOD_ID, name).toString()));
        ModItems.ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entity, backgroundColor, highlightColor, new Item.Properties()));
        return entity;
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        registerMobAttributes(event, TEMPLE_GUARD, EntityStats.TEMPLE_GUARD);
    }

    private static <T extends Mob> void registerMobAttributes(EntityAttributeCreationEvent event, RegistryObject<EntityType<T>> entity, EntityStats stats) {
        event.put(entity.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, stats.getHealth()).add(Attributes.ATTACK_DAMAGE, stats.getAttackDamage()).add(Attributes.MOVEMENT_SPEED, stats.getMovementSpeed()).add(Attributes.FOLLOW_RANGE, stats.getFollowRange()).add(Attributes.FLYING_SPEED, stats.getMovementSpeed()).build());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TempleGuardModel.LAYER_LOCATION, TempleGuardModel::createBodyLayer);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TEMPLE_GUARD.get(), (EntityRendererProvider.Context context) -> new RenderAdventuresMob<>(context, "temple_guard", new TempleGuardModel<>(context), 0.4F));
    }
}
