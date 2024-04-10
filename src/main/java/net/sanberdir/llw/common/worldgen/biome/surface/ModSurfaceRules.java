package net.sanberdir.llw.common.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.sanberdir.llw.common.blocks.LLWBlocks;
import net.sanberdir.llw.common.worldgen.biome.ModBiomes;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource CRYSTAL_CAVE_STONE = makeStateRule(LLWBlocks.CRYSTAL_CAVE_STONE.get());
    private static final SurfaceRules.RuleSource CRYSTAL_CAVE_STONE_1 = makeStateRule(LLWBlocks.CRYSTAL_CAVE_STONE.get());
    private static final SurfaceRules.RuleSource CRYSTAL_CAVE_STONE_2 = makeStateRule(LLWBlocks.CRYSTAL_CAVE_STONE.get());
    private static final SurfaceRules.RuleSource CRYSTAL_CAVE_STONE_3 = makeStateRule(LLWBlocks.CRYSTAL_CAVE_STONE.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, CRYSTAL_CAVE_STONE_1), CRYSTAL_CAVE_STONE);

        // Правила для пола и потолка в пещерах биома CRYSTAL_CAVE_STONE
        SurfaceRules.RuleSource caveStoneSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CRYSTAL_CAVE_STONE_2),
                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CRYSTAL_CAVE_STONE_3));

        return SurfaceRules.sequence(caveStoneSurface, grassSurface);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}