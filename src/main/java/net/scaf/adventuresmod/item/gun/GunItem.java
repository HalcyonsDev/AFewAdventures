package net.scaf.adventuresmod.item.gun;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.scaf.adventuresmod.entity.projectile.BulletEntity;
import net.scaf.adventuresmod.item.ModItems;
import net.scaf.adventuresmod.sound.ModSounds;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GunItem extends ProjectileWeaponItem {

    protected int bonusDamage;
    protected double damageMultiplier;
    protected int fireDelay;
    protected double inaccuracy;
    protected double projectileSpeed = 3;
    private int enchantability;
    protected boolean ignoreInvulnerability = false;
    protected double chanceFreeShot = 0;
    protected Supplier<SoundEvent> fireSound = ModSounds.gun;
    protected Supplier<Ingredient> repairMaterial;

    public GunItem(Properties properties, int bonusDamage, double damageMultiplier, int fireDelay, double inaccuracy) {
        super(properties);
        this.bonusDamage = bonusDamage;
        this.damageMultiplier = damageMultiplier;
        this.fireDelay = fireDelay;
        this.inaccuracy = inaccuracy;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);
        ItemStack ammo = player.getProjectile(gun);

        if (!ammo.isEmpty()) {
            if (ammo.isEmpty()) ammo = new ItemStack(ModItems.SIMPLE_BULLET.get());

            IBullet bulletItem = (IBullet) (ammo.getItem() instanceof IBullet ? ammo.getItem() : ModItems.SIMPLE_BULLET.get());

            if (!world.isClientSide) {
                ItemStack shotAmmo = ammo.getItem() instanceof IBullet ? ammo : new ItemStack(ModItems.SIMPLE_BULLET.get());
                shoot(world, player, gun, shotAmmo, bulletItem);

                gun.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                bulletItem.consume(ammo, player);
            }

            world.playSound(null, player.getX(), player.getY(), player.getZ(), fireSound.get(), SoundSource.PLAYERS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            player.awardStat(Stats.ITEM_USED.get(this));

            player.getCooldowns().addCooldown(this, getFireDelay(gun, player));
            return InteractionResultHolder.consume(gun);
        }
        else return InteractionResultHolder.fail(gun);

    }

    protected void shoot(Level world, Player player, ItemStack gun, ItemStack ammo, IBullet bulletItem) {
        BulletEntity shot = bulletItem.createProjectile(world, ammo, player);

        shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, (float) getProjectileSpeed(gun, player), (float) getInaccuracy(gun, player));
        shot.setIgnoreInvulnerability(ignoreInvulnerability);
        changeBullet(world, player, gun, shot);

        world.addFreshEntity(shot);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack itemStack, @org.jetbrains.annotations.Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            int fireDelay = getFireDelay(itemStack, null);
            tooltip.add(Component.translatable("tooltip.adventuresmod.gun.firerate", fireDelay, (60*20) / fireDelay));

            double inaccuracy = getInaccuracy(itemStack, null);
            if (inaccuracy <= 0) tooltip.add(Component.translatable("tooltip.adventuresmod.gun.accuracy.perfect"));
            else tooltip.add(Component.translatable("tooltip.adventuresmod.gun.accuracy", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(1.0 / inaccuracy)));

            addExtraStatsTooltip(itemStack, world, tooltip);
        } else {
            tooltip.add(Component.translatable("tooltip.adventuresmod.shift"));
        }
    }

    /**
     * Add more tooltips that will be displayed below the base stats.
     */
    protected void addExtraStatsTooltip(ItemStack itemStack, @Nullable Level world, List<Component> tooltip) {}

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    //TODO ammo types
    private static final Predicate<ItemStack> BULLETS = (stack) -> stack.getItem() instanceof IBullet && ((IBullet)stack.getItem()).hasAmmo(stack);

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return (repairMaterial != null && repairMaterial.get().test(repair)) || super.isValidRepairItem(toRepair, repair);
    }

    public int getFireDelay(ItemStack stack, @Nullable Player player) {
        return fireDelay;
    }

    /**
     * Lets the gun do custom stuff to default bullets without redoing all the base stuff from shoot.
     */
    protected void changeBullet(Level world, Player player, ItemStack gun, BulletEntity bullet) {

    }

    public double getProjectileSpeed(ItemStack stack, @Nullable Player player) {
        return projectileSpeed;
    }

    public double getInaccuracy(ItemStack stack, @Nullable Player player) {
        return inaccuracy;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return BULLETS;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }

    /**
     * Sets whether the bullets ignore invulnerability frame (default no), used when making the item for registering.
     */
    public GunItem ignoreInvulnerability(boolean ignoreInvulnerability) {
        this.ignoreInvulnerability = ignoreInvulnerability;
        return this;
    }

    /**
     * Sets the firing sound, used when making the item for registering.
     */
    public GunItem fireSound(Supplier<SoundEvent> fireSound) {
        this.fireSound = fireSound;
        return this;
    }
}
