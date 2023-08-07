package net.scaf.adventuresmod.util;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.scaf.adventuresmod.FewAdventuresMod;

public class ClientUtils {
    public static final ModelLayerLocation layerHumanoid = createLocation("temple_guard");

    public static ModelLayerLocation createLocation(String name) {
        return new ModelLayerLocation(new ResourceLocation(FewAdventuresMod.MOD_ID, name), "main");
    }
}
