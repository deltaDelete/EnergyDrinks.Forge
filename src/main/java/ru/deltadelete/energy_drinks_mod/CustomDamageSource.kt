package ru.deltadelete.energy_drinks_mod

import net.minecraft.world.damagesource.DamageSource


open class CustomDamageSource(string: String) : DamageSource(string) {
    object HEART_ATTACK : CustomDamageSource("heart_attack")
}

