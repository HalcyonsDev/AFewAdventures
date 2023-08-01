package net.scaf.adventuresmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PoweredBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scaf.adventuresmod.FewAdventuresMod;
import net.scaf.adventuresmod.block.custom.*;
import net.scaf.adventuresmod.item.ModItems;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FewAdventuresMod.MOD_ID);

    public static final RegistryObject<Block> ANCIENT_TEMPLE_BRICKS = registerBlock("ancient_temple_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.DECORATION).requiresCorrectToolForDrops().strength(1.5F, 6.0F).isRedstoneConductor(ModBlocks::never)));

    public static final RegistryObject<Block> ANCIENT_TEMPLE_STONE = registerBlock("ancient_temple_stone",
            () -> new Block(BlockBehaviour.Properties.of(Material.DECORATION).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> ANCIENT_TEMPLE_KEYHOLE = registerBlock("ancient_temple_keyhole",
            () -> new PoweredBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CRYSTAL_LAMP = registerBlock("crystal_lamp",
            () -> new CrystalLampBlock(BlockBehaviour.Properties.of(Material.DECORATION).requiresCorrectToolForDrops().strength(0.3F).sound(SoundType.GLASS)
                    .lightLevel(value -> value.getValue(CrystalLampBlock.LEVEL) == CrystalsEnum.EMPTY ? 0 : 16)));

    public static final RegistryObject<Block> MICA_BLOCK = registerBlock("mica_block",
            () -> new MicaBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(1.5F)
                    .sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MICA = registerBlock("mica",
            () -> new Mica(7, 5, BlockBehaviour.Properties.of(Material.METAL).randomTicks()
                    .strength(1.5F).lightLevel(state -> 5).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
