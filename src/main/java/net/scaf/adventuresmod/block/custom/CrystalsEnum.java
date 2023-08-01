package net.scaf.adventuresmod.block.custom;

import net.minecraft.util.StringRepresentable;
import net.minecraft.util.StringUtil;
import org.jetbrains.annotations.NotNull;

public enum CrystalsEnum implements StringRepresentable {

    EMPTY,
    GLOWSTONE,
    REDSTONE,
    DIAMOND,
    LAPIS_LAZULI,
    EMERALD;

    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public @NotNull String getSerializedName() {
        if (this == EMPTY) {
            return "empty";
        } else if (this == GLOWSTONE) {
            return "glowstone";
        } else if (this == REDSTONE) {
            return "redstone";
        } else if (this == DIAMOND) {
            return "diamond";
        } else if (this == LAPIS_LAZULI) {
            return "lapis_lazuli";
        }
        return "emerald";
    }
}
