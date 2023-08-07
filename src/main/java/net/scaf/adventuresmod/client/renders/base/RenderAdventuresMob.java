package net.scaf.adventuresmod.client.renders.base;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.particle.SpellParticle;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.scaf.adventuresmod.FewAdventuresMod;

@OnlyIn(Dist.CLIENT)
public class RenderAdventuresMob <Type extends Mob> extends MobRenderer<Type, EntityModel<Type>> {
    protected final ResourceLocation TEXTURE;
    protected final float scale;

    public RenderAdventuresMob(EntityRendererProvider.Context context, String name, EntityModel<Type> model) {
        this(context, name, model, 1F);
    }
    public RenderAdventuresMob(EntityRendererProvider.Context context, String name, EntityModel<Type> model, float shadowSize) {
        super(context, model, shadowSize);
        this.scale = 1;
        this.TEXTURE = new ResourceLocation(FewAdventuresMod.MOD_ID, "textures/entity/" + name + ".png");
    }

    @Override
    protected void scale(Type type, PoseStack stack, float s) {
        stack.scale(this.scale, this.scale, this.scale);
    }

    public RenderAdventuresMob(EntityRendererProvider.Context context, String name, EntityModel<Type> model, float shadowSize, float scale) {
        super(context, model, shadowSize);
        this.scale = scale;
        this.TEXTURE = new ResourceLocation(FewAdventuresMod.MOD_ID, "textures/entity/" + name + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(Type p_114482_) {
        return TEXTURE;
    }
}
