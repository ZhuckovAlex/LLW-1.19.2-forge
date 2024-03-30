package net.sanberdir.legends_lost_worlds.common.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.legends_lost_worlds.LLW;
public class CustomSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LLW.MOD_ID);

    public static final RegistryObject<SoundEvent> MOON_TEAR =
            registerSoundEvent("moon_tear");
    public static final RegistryObject<SoundEvent> MOON_TEAR_BREAK =
            registerSoundEvent("moon_tear_break");

    public static final ForgeSoundType MOON_TEAR_SOUNDS = new ForgeSoundType(1f, 1f,
            CustomSoundEvents.MOON_TEAR_BREAK, //break
            CustomSoundEvents.MOON_TEAR, //walk
            CustomSoundEvents.MOON_TEAR, //place
            CustomSoundEvents.MOON_TEAR, //hit
            CustomSoundEvents.MOON_TEAR //walk
    );


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(LLW.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
