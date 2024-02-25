package ru.deltadelete.energy_drinks_mod.effects

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player


class EnergyDrinkEffect : MobEffect(MobEffectCategory.NEUTRAL, 0x00CC00) {
    override fun applyEffectTick(entity: LivingEntity, amplifier: Int) {
        assert(entity is Player)
        super.applyEffectTick(entity, amplifier)
    }

    override fun isInstantenous(): Boolean {
        return false
    }
}