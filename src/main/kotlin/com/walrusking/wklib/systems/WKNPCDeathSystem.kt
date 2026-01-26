package com.walrusking.wklib.systems

import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.hypixel.hytale.server.npc.entities.NPCEntity

/**
 * An abstract system that handles NPC death events by extending WKDeathSystem.
 */
abstract class WKNPCDeathSystem<T : WKOnDeathEvent> : WKDeathSystem() {
	/**
	 * Method to be overridden by subclasses to implement custom NPC death event handling logic.
	 *
	 * @param event The WKOnDeathEvent containing information about the NPC death event and context.
	 */
	override fun onDeath(event: WKOnDeathEvent) {}

	/**
	 * Overrides the default query to filter for entities that have the NPCEntity component.
	 *
	 * @return The Query for entities with the NPCEntity component.
	 */
	override fun getQuery(): Query<EntityStore> {
		return Query.and(NPCEntity.getComponentType())
	}
}