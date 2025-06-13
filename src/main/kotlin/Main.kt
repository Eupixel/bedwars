package net.eupixel

import kotlinx.coroutines.runBlocking
import net.eupixel.command.CommandManager
import net.eupixel.event.EventManager
import net.eupixel.save.Config
import net.eupixel.vivlib.core.DirectusClient
import net.eupixel.vivlib.core.Vivlib
import net.eupixel.vivlib.util.Helper
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.instance.anvil.AnvilLoader

fun main() {
    val server = MinecraftServer.init()
    Vivlib.init()

    println("Downloading lobby world...")
    runBlocking {
        val ok = DirectusClient.downloadFile("worlds", "name", "lobby", "world_data", "lobby.zip")
        if (ok) {
            Helper.unzip("lobby.zip", "lobby")
        } else {
            println("Failed to download the lobby.")
        }
    }

    Config.instance = MinecraftServer.getInstanceManager()
        .createInstanceContainer()
        .apply { chunkLoader = AnvilLoader("lobby"); timeRate = 0 }

    EventManager.init()
    CommandManager.init()
    MojangAuth.init()
    server.start("0.0.0.0", 25565)
}