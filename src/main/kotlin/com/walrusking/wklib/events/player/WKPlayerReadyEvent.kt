package com.walrusking.wklib.events.player

import com.hypixel.hytale.component.Component
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.walrusking.wklib.components.Components
import com.walrusking.wklib.helpers.ensureAndGetComponent
import com.walrusking.wklib.helpers.getWorld

class WKPlayerReadyEvent(
	ref: Ref<EntityStore>,
	player: Player,
	readyId: Int
) : PlayerReadyEvent(ref, player, readyId) {
	val store = playerRef.store
	val world = playerRef.getWorld()

	fun <T : Component<EntityStore>> ensureAndGetComponent(componentId: String): T {
		val type = Components.getType<T>(componentId)
			?: throw IllegalArgumentException("$\"${componentId}\" component type not registered!")

		return playerRef.ensureAndGetComponent(type)
	}
}