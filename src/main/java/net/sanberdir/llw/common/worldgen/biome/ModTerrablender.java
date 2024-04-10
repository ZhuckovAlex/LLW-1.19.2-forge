package net.sanberdir.llw.common.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import net.sanberdir.llw.LLW;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(LLW.MOD_ID, "overworld"), 5));
    }
}