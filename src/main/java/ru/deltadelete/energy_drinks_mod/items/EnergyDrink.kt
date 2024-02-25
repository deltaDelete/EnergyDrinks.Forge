package ru.deltadelete.energy_drinks_mod.items

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextComponent
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.*
import net.minecraft.world.level.Level
import net.minecraftforge.common.extensions.IForgeItem
import org.jetbrains.annotations.NotNull
import ru.deltadelete.energy_drinks_mod.CustomDamageSource
import ru.deltadelete.energy_drinks_mod.effects.EnergyDrinkEffect


class EnergyDrink : Item {
    constructor() : super(ModItemSettings.drink().rarity(Rarity.UNCOMMON))

    @SafeVarargs
    constructor(vararg effects: Pair<MobEffectInstance, Float>) : super(
        ModItemSettings.drink(effects = effects).rarity(Rarity.UNCOMMON)
    )

    @NotNull
    override fun finishUsingItem(stack: ItemStack, world: Level, user: LivingEntity): ItemStack {
        var currentEffect: MobEffectInstance? = null

        for (effect in user.activeEffects) {
            if (effect.effect is EnergyDrinkEffect) {
                currentEffect = effect
                break
            }
        }
        if (currentEffect != null) {
            user.hurt(CustomDamageSource.HEART_ATTACK, 1000f)
        }

        return super.finishUsingItem(stack, world, user)
    }

    override fun appendHoverText(
        itemStack: ItemStack,
        level: Level?,
        pTooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val effects = itemStack.item.foodProperties!!
            .effects
        for (effect in effects) {
            val effectInstance = effect.first
            val seconds: Float = effectInstance.duration * 0.05f
            var format: String
            var durationString: String
            if (seconds >= 3600) {
                format = "%d:%02d:%02d"
                durationString = String.format(
                    format,
                    (seconds / 3600).toInt(),
                    ((seconds % 3600) / 60).toInt(),
                    (seconds % 60).toInt()
                )
            } else {
                format = "%02d:%02d"
                durationString = String.format(format, ((seconds % 3600) / 60).toInt(), (seconds % 60).toInt())
            }
            pTooltipComponents.add(
                effect.first.effect.displayName.copy()
                    .append(TextComponent(" ("))
                    .append(TextComponent(durationString))
                    .append(TextComponent(")"))
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE))
            )
        }
    }

    override fun use(
        level: Level,
        player: Player,
        interactionHand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        if (this.isEdible) {
            val itemStack = player.getItemInHand(interactionHand)
            if (player.canEat(getFoodProperties(itemStack, player)!!.canAlwaysEat())) {
                player.startUsingItem(interactionHand)
                return InteractionResultHolder.consume(itemStack)
            } else {
                return InteractionResultHolder.fail(itemStack)
            }
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand))
        }
    }

    override fun getEatingSound(): SoundEvent {
        return drinkingSound
    }

    override fun getDrinkingSound(): SoundEvent {
        return SoundEvents.GENERIC_DRINK
    }

    override fun getUseAnimation(itemStack: ItemStack): UseAnim {
        return UseAnim.DRINK
    }
}