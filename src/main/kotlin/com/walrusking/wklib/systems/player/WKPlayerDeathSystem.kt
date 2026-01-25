package com.walrusking.wklib.systems.player

import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.walrusking.wklib.helpers.getComponent
import com.walrusking.wklib.systems.WKBaseOnDeathSystem
import com.walrusking.wklib.systems.WKOnDeathEvent

abstract class WKPlayerDeathSystem : WKBaseOnDeathSystem<WKPlayerDeathEvent>(
) {
	override fun onDeath(event: WKPlayerDeathEvent) {

	}

	override fun createEvent(
		ref: Ref<EntityStore>,
		deathComponent: DeathComponent,
		store: Store<EntityStore>,
		commandBuffer: CommandBuffer<EntityStore>
	): WKPlayerDeathEvent {
		return WKPlayerDeathEvent(ref, deathComponent, store, commandBuffer)
	}

	override fun getQuery(): Query<EntityStore> {
		return Query.and(Player.getComponentType())
	}
}

class WKPlayerDeathEvent(
	ref: Ref<EntityStore>, deathComponent: DeathComponent, store: Store<EntityStore>,
	commandBuffer: CommandBuffer<EntityStore>
) : WKOnDeathEvent(
	ref, deathComponent,
	store, commandBuffer
) {
	val player: Player? = ref.getComponent(Player.getComponentType())
}