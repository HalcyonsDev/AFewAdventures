package net.scaf.adventuresmod;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.scaf.adventuresmod.entity.ModEntities;
import net.scaf.adventuresmod.item.ModItems;
import net.scaf.adventuresmod.item.gun.BulletItem;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = FewAdventuresMod.MOD_ID, value = Dist.CLIENT)
public class ClientStuff {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        //Same renderer as potions
        event.registerEntityRenderer(ModEntities.BULLET.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void clientStuff(final FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.SIMPLE_BULLET.get(), FewAdventuresMod.resourceLocation("shot"), (stack, world, entity, someint) -> BulletItem.isShot(stack) ? 1 : 0);
    }
}
