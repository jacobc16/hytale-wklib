package com.walrusking.wklib.events.player

import com.hypixel.hytale.server.core.event.events.player.*
import com.walrusking.wklib.events.Events

class PlayerEvents {
	companion object {
		fun onPlayerReady(event: PlayerReadyEvent) {
			Events.onPlayerReady.runHandlers(
				WKPlayerReadyEvent(
					event.playerRef,
					event.player,
					event.readyId
				)
			)
		}

		fun onPlayerConnect(event: PlayerConnectEvent) =
			Events.onPlayerConnect.runHandlers(event)

		fun onPlayerDisconnect(event: PlayerDisconnectEvent) =
			Events.onPlayerDisconnect.runHandlers(event)

		fun onPlayerChat(event: PlayerChatEvent) {
			Events.onPlayerChat.runHandlers(
				WKPlayerChatEvent(
					event.sender,
					event.targets,
					event.content
				)
			)
		}

		fun onPlayerSetupConnect(event: PlayerSetupConnectEvent) =
			Events.onPlayerSetupConnect.runHandlers(event)

		fun onPlayerSetupDisconnect(event: PlayerSetupDisconnectEvent) =
			Events.onPlayerSetupDisconnect.runHandlers(event)

		fun onPlayerAddedToWorld(event: AddPlayerToWorldEvent) =
			Events.onPlayerAddedToWorld.runHandlers(event)

		fun onPlayerDrainFromWorld(event: DrainPlayerFromWorldEvent) =
			Events.onPlayerDrainFromWorld.runHandlers(event)
	}
}