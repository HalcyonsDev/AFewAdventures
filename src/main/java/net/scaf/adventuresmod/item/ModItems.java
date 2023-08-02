package net.scaf.adventuresmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.scaf.adventuresmod.FewAdventuresMod;
import net.scaf.adventuresmod.food.ModFoods;
import net.scaf.adventuresmod.item.custom.BulletItem;
import net.scaf.adventuresmod.item.custom.DarkBladeItem;
import net.scaf.adventuresmod.item.custom.GunItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FewAdventuresMod.MOD_ID);

    public static final RegistryObject<Item> GREEN_REFRACTING_CRYSTAL = ITEMS.register("green_refracting_crystal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SEA_AMULET = ITEMS.register("sea_amulet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_SCROLL = ITEMS.register("blood_scroll",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AQUARIUM_RING = ITEMS.register("aquarium_ring",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ANCIENT_KEY = ITEMS.register("ancient_key",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASTRAL_STONE = ITEMS.register("astral_stone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASTRAL_CLUSTER = ITEMS.register("astral_cluster",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLASK_OF_BLASE_POWDER = ITEMS.register("flask_of_blaze_powder",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHARD_OF_MICA = ITEMS.register("shard_of_mica",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KNIFE = ITEMS.register("knife",
            () -> new SwordItem(Tiers.IRON, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> EVE = ITEMS.register("eve",
            () -> new SwordItem(Tiers.DIAMOND, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> DARK_BLADE = ITEMS.register("dark_blade",
            () -> new DarkBladeItem(Tiers.DIAMOND, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> FROSTMOURNE = ITEMS.register("frostmourne",
            () -> new SwordItem(Tiers.DIAMOND, 3, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> ANCIENT_RUNE = ITEMS.register("ancient_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SUNRISE = ITEMS.register("sunrise",
            () -> new SwordItem(Tiers.IRON, 3, -2.0F, new Item.Properties()));

    public static final RegistryObject<Item> REJUVENATING_FRUIT = ITEMS.register("rejuvenating_fruit",
            () -> new Item(new Item.Properties().food(ModFoods.REJUVENATING_FRUIT)));

    public static final RegistryObject<Item> SIMPLE_BULLET = ITEMS.register("simple_bullet",
            () -> new BulletItem(new Item.Properties(), 5));

    public static final RegistryObject<Item> RIFLE = ITEMS.register("rifle",
            () -> new GunItem(new Item.Properties().durability(513), 0, 1, 16, 1.5));

    public static final RegistryObject<Item> UGLINESS = ITEMS.register("ugliness",
            () -> new GunItem(new Item.Properties().durability(513), 0, 1, 16, 1.5));

    public static final RegistryObject<Item> BEAUTY = ITEMS.register("beauty",
            () -> new GunItem(new Item.Properties().durability(513), 0, 1, 16, 1.5));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
