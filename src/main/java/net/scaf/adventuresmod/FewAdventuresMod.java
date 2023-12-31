package net.scaf.adventuresmod;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.scaf.adventuresmod.block.ModBlocks;
import net.scaf.adventuresmod.effect.ModEffects;
import net.scaf.adventuresmod.entity.ModEntities;
import net.scaf.adventuresmod.item.ModCreativeModeTabs;
import net.scaf.adventuresmod.item.ModItems;
import net.scaf.adventuresmod.sound.ModSounds;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FewAdventuresMod.MOD_ID)
public class FewAdventuresMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "adventuresmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public FewAdventuresMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTabs.ADVENTURES_ITEMS_TAB) {
            event.accept(ModItems.GREEN_REFRACTING_CRYSTAL);
            event.accept(ModItems.SEA_AMULET);
            event.accept(ModItems.BLOOD_SCROLL);
            event.accept(ModItems.AQUARIUM_RING);

            event.accept(ModItems.ANCIENT_KEY);
            event.accept(ModItems.ANCIENT_RUNE);

            event.accept(ModItems.ASTRAL_STONE);
            event.accept(ModItems.ASTRAL_CLUSTER);
            event.accept(ModItems.MAGIC_DUST);
            event.accept(ModItems.MYTHRILE_CLUSTER);

            event.accept(ModItems.FLASK_OF_BLASE_POWDER);

            event.accept(ModItems.SHARD_OF_MICA);

            event.accept(ModItems.REJUVENATING_FRUIT);
        }

        if (event.getTab() == ModCreativeModeTabs.ADVENTURES_TOOLS_TAB) {
            event.accept(ModItems.TINKERER_PICKAXE);
            event.accept(ModItems.BLAZE_PICKAXE);
            event.accept(ModItems.MYTHRITE_SHOVEL);
            event.accept(ModItems.MYTHRILE_SHOVEL);
            event.accept(ModItems.MYTHRITE_PICKAXE);
            event.accept(ModItems.MYTHRILE_PICKAXE);
            event.accept(ModItems.MYTHRITE_AXE);
            event.accept(ModItems.MYTHRILE_AXE);
        }

        if (event.getTab() == ModCreativeModeTabs.ADVENTURES_BLOCKS_TAB) {
            event.accept(ModBlocks.ANCIENT_TEMPLE_BRICKS);
            event.accept(ModBlocks.ANCIENT_TEMPLE_STONE);
            event.accept(ModBlocks.ANCIENT_TEMPLE_KEYHOLE);

            event.accept(ModBlocks.MYTHRILE_BLOCK);
            event.accept(ModBlocks.MYTHRILE_ORE);

            event.accept(ModBlocks.CRYSTAL_LAMP);

            event.accept(ModBlocks.MICA_BLOCK);
            event.accept(ModBlocks.MICA);
        }

        if (event.getTab() == ModCreativeModeTabs.ADVENTURES_SWORDS_TAB) {
            event.accept(ModItems.EVE);
            event.accept(ModItems.DARK_BLADE);
            event.accept(ModItems.FROSTMOURNE);

            event.accept(ModItems.MYTHRITE_SWORD);
            event.accept(ModItems.MYTHRILE_SWORD);

            event.accept(ModItems.KNIFE);
            event.accept(ModItems.SUNRISE);
        }

        if (event.getTab() == ModCreativeModeTabs.ADVENTURES_GUNS_TAB) {
            event.accept(ModItems.SIMPLE_BULLET);
            event.accept(ModItems.RIFLE);
            event.accept(ModItems.UGLINESS);
            event.accept(ModItems.BEAUTY);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }

    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
