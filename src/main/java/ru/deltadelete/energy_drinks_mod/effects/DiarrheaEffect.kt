package ru.deltadelete.energy_drinks_mod.effects

import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import ru.deltadelete.energy_drinks_mod.CustomDamageSource

class DiarrheaEffect : MobEffect(MobEffectCategory.HARMFUL, 0x7b5804) {
    override fun applyEffectTick(entity: LivingEntity, i: Int) {
        val x = entity.x
        val y = entity.y + 0.5
        val z = entity.z
        val item = ItemEntity(
            entity.level,
            x,
            y,
            z,
            ItemStack(Items.DIRT)
        )
        item.setPickUpDelay(1200)
        entity.level.addFreshEntity(item)
        if (entity is Player) {
            entity.level.playSound(entity, x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1f, 1f)
            entity.hurt(DIARRHEA_DAMAGE_SOURCE, 2f)
            for (j in 0..9) {
                entity.level.addAlwaysVisibleParticle(ParticleTypes.EXPLOSION, x, y, z, 0.0, 0.0, 0.0)
            }
        }
        super.applyEffectTick(entity, i)
    }

    override fun isDurationEffectTick(i: Int, j: Int): Boolean {
        return i % 20 == 0
    }

    companion object {
        val DIARRHEA_DAMAGE_SOURCE: DamageSource = CustomDamageSource("diarrhea")
    }
}