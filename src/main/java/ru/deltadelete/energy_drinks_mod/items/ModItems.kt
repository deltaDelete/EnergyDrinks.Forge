package ru.deltadelete.energy_drinks_mod.items

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.deltadelete.energy_drinks_mod.EnergyDrinks
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val Registry: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, EnergyDrinks.ID)

    val FLASH_ORIGINAL by Registry.registerObject("flash_original") {
        EnergyDrink()
    }

    val FLASH_BERRY by Registry.registerObject("flash_berry") {
        EnergyDrink(
            MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 1) to 1f
        )
    }

    val FLASH_GOLD by Registry.registerObject("flash_gold") {
        EnergyDrink(
            MobEffectInstance(MobEffects.CONFUSION, 1000) to 1f,
            MobEffectInstance(MobEffects.ABSORPTION, 1000, 1) to 1f
        )
    }
    val FLASH_ORANGE by Registry.registerObject("flash_orange") {
        EnergyDrink(
            MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000) to 1f
        )
    }
    val FLASH_LIME by Registry.registerObject("flash_lime") {
        EnergyDrink(
            MobEffectInstance(MobEffects.JUMP, 1000, 1) to 1f
        )
    }
    val BLACK_ROCKET by Registry.registerObject("black_rocket") {
        EnergyDrink(
            MobEffectInstance(MobEffects.DIG_SPEED, 1000) to 1f
        )
    }
    val CAN by Registry.registerObject("can") {
        Item(Item.Properties().tab(EnergyDrinks.CREATIVE_TAB))
    }
}

private fun FoodProperties.Builder.effect(
    probability: Float,
    supplier: () -> MobEffectInstance,
): FoodProperties.Builder {
    return this.effect(supplier, probability)
}
