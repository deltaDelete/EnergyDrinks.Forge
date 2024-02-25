package ru.deltadelete.energy_drinks_mod.effects

import net.minecraft.world.effect.MobEffect
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.deltadelete.energy_drinks_mod.EnergyDrinks
import thedarkcolour.kotlinforforge.forge.registerObject


object ModEffects {
    val Registry: DeferredRegister<MobEffect> =
        DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EnergyDrinks.ID)

    val ENERGY_DRINK by Registry.registerObject("energy_drink") {
        EnergyDrinkEffect()
    }
    val DIARRHEA by Registry.registerObject("diarrhea") {
        DiarrheaEffect()
    }
}