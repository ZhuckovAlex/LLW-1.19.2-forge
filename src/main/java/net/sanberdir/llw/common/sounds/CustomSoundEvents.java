package net.sanberdir.llw.common.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.sanberdir.llw.LLW;

public class CustomSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LLW.MOD_ID);

    public static final RegistryObject<SoundEvent> MOON_TEAR_PLACE =
            registerSoundEvents("moon_tear_place");
    public static final RegistryObject<SoundEvent> MOON_TEAR_HIT =
            registerSoundEvents("moon_tear_hit");
    public static final RegistryObject<SoundEvent> MOON_TEAR_FALL =
            registerSoundEvents("moon_tear_fall");
    public static final RegistryObject<SoundEvent> MOON_TEAR_STEP =
            registerSoundEvents("moon_tear_step");
    public static final RegistryObject<SoundEvent> MOON_TEAR_BREAK =
            registerSoundEvents("moon_tear_break");

    public static final ForgeSoundType MOON_TEAR_SOUNDS = new ForgeSoundType(1f, 1f,
            CustomSoundEvents.MOON_TEAR_BREAK, CustomSoundEvents.MOON_TEAR_STEP, CustomSoundEvents.MOON_TEAR_PLACE,
            CustomSoundEvents.MOON_TEAR_HIT, CustomSoundEvents.MOON_TEAR_FALL);


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LLW.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}