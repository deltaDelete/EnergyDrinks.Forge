package ru.deltadelete.energy_drinks_mod.items

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import ru.deltadelete.energy_drinks_mod.EnergyDrinks
import ru.deltadelete.energy_drinks_mod.effects.ModEffects


class ModItemSettings : Item.Properties() {
    init {
        tab(EnergyDrinks.CREATIVE_TAB)
    }

    companion object {
        fun base(): Item.Properties {
            return ModItemSettings()
        }

        fun noStack(): Item.Properties {
            return base().stacksTo(1)
        }

        fun drink(): Item.Properties {
            return base().food(
                FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(2)
                    .saturationMod(10f)
                    .effect({ MobEffectInstance(MobEffects.REGENERATION, 1000, 1) }, 1f)
                    .effect({ MobEffectInstance(ModEffects.ENERGY_DRINK, 24000) }, 1f)
                    .build()
            )
        }

        /**
         *
         * @param effects Pairs of MobEffectInstance and possibility float
         * @return Ready to use drinkable item properties
         */
        @SafeVarargs
        fun drink(vararg effects: Pair<MobEffectInstance, Float>): Item.Properties {
            var food = FoodProperties.Builder()
                .alwaysEat()
                .nutrition(2)
                .saturationMod(10f)
                .effect({ MobEffectInstance(MobEffects.REGENERATION, 1000, 1) }, 1f)
                .effect({ MobEffectInstance(ModEffects.ENERGY_DRINK, 24000) }, 1f)
            for (effect in effects) {
                food = food.effect(effect::first::get, effect.second)
            }
            return base().food(food.build())
        }
    }
}