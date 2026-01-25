package com.walrusking.wklib.events.player

import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.walrusking.wklib.helpers.getPlayer

class WKPlayerChatEvent(sender: PlayerRef, targets: List<PlayerRef>, content: String) : PlayerChatEvent(
	sender, targets,
	content
) {
	val player = sender.getPlayer()
}