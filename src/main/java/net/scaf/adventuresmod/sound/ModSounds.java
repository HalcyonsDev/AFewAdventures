package net.scaf.adventuresmod.sound;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scaf.adventuresmod.FewAdventuresMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FewAdventuresMod.MOD_ID);
    public static RegistryObject<SoundEvent> gun = SOUNDS.register("item.gun.shoot",
            () -> SoundEvent.createVariableRangeEvent(FewAdventuresMod.resourceLocation("item.gun.shoot")));

    public static RegistryObject<SoundEvent> shotgun = SOUNDS.register("item.shotgun.shoot",
            () -> SoundEvent.createVariableRangeEvent(FewAdventuresMod.resourceLocation("item.shotgun.shoot")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
