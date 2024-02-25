package ru.deltadelete.energy_drinks_mod

import com.mojang.logging.LogUtils
import net.minecraft.client.Minecraft
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.slf4j.Logger
import ru.deltadelete.energy_drinks_mod.effects.ModEffects
import ru.deltadelete.energy_drinks_mod.items.ModItems
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(EnergyDrinks.ID)
object EnergyDrinks {
    const val ID = "energy_drinks"

    private val LOGGER: Logger = LogUtils.getLogger()

    val CREATIVE_TAB: ModCreativeTab = ModCreativeTab(CreativeModeTab.TABS.size, ID)

    init {
        LOGGER.info("Hello world!")

        // Register the KDeferredRegister to the mod-specific event bus
        onCommonSetup()

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })

        println(obj)
    }

    private fun onCommonSetup() {
        ModEffects.Registry.register(MOD_BUS)
        ModItems.Registry.register(MOD_BUS)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("Initializing client...")
        LOGGER.info("Initializing complete!")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.info("Server starting...")
    }
}

class ModCreativeTab internal constructor(index: Int, label: String) : CreativeModeTab(index, label) {
    override fun makeIcon(): ItemStack {
        return ItemStack(Items.DIRT)
    }
}