package net.eupixel.event

import net.eupixel.event.events.AsyncPlayerConfiguration
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent

object EventManager {
    fun init() {
        MinecraftServer.getGlobalEventHandler().addListener(AsyncPlayerConfigurationEvent::class.java, ::AsyncPlayerConfiguration)
    }
}