package net.scaf.adventuresmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.scaf.adventuresmod.FewAdventuresMod;
import net.scaf.adventuresmod.block.ModBlocks;

@Mod.EventBusSubscriber(modid = FewAdventuresMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab ADVENTURES_ITEMS_TAB;
    public static CreativeModeTab ADVENTURES_BLOCKS_TAB;
    public static CreativeModeTab ADVENTURES_SWORDS_TAB;
    public static CreativeModeTab ADVENTURES_GUNS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {

        ADVENTURES_SWORDS_TAB = event.registerCreativeModeTab(new ResourceLocation(FewAdventuresMod.MOD_ID, "adventures_swords_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.FROSTMOURNE.get()))
                        .title(Component.translatable("creativemodetab.adventures_swords_tab")));

        ADVENTURES_BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(FewAdventuresMod.MOD_ID, "adventures_blocks_tab"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.ANCIENT_TEMPLE_BRICKS.get()))
                        .title(Component.translatable("creativemodetab.adventures_blocks_tab")));

        ADVENTURES_ITEMS_TAB = event.registerCreativeModeTab(new ResourceLocation(FewAdventuresMod.MOD_ID, "adventures_items_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.GREEN_REFRACTING_CRYSTAL.get()))
                        .title(Component.translatable("creativemodetab.adventures_items_tab")));

        ADVENTURES_GUNS_TAB = event.registerCreativeModeTab(new ResourceLocation(FewAdventuresMod.MOD_ID, "adventures_guns_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.RIFLE.get()))
                        .title(Component.translatable("creativemodetab.adventures_guns_tab")));
    }
}
