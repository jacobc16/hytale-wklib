package com.walrusking.wklib.systems

import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.hypixel.hytale.server.npc.entities.NPCEntity
import com.walrusking.wklib.helpers.getComponent

/**
 * An abstract system that handles NPC death events by extending WKDeathSystem.
 */
abstract class WKNPCDeathSystem : WKBaseOnDeathSystem<WKNPCDeathEvent>() {
	/**
	 * Method to be overridden by subclasses to implement custom NPC death event handling logic.
	 *
	 * @param event The WKNPCDeathEvent containing information about the NPC death event and context.
	 */
	override fun onDeath(event: WKNPCDeathEvent) {}

	override fun createEvent(
		ref: Ref<EntityStore>,
		deathComponent: DeathComponent,
		store: Store<EntityStore>,
		commandBuffer: CommandBuffer<EntityStore>
	): WKNPCDeathEvent {
		return WKNPCDeathEvent(ref, deathComponent, store, commandBuffer)
	}

	/**
	 * Overrides the default query to filter for entities that have the NPCEntity component.
	 *
	 * @return The Query for entities with the NPCEntity component.
	 */
	override fun getQuery(): Query<EntityStore> {
		return Query.and(NPCEntity.getComponentType())
	}
}

/**
 * Data class encapsulating information about an NPC death event.
 *
 * @constructor
 * Creates a new WKNPCDeathEvent.
 *
 * @param ref The Ref of the EntityStore where the death event occurred.
 * @param deathComponent The DeathComponent associated with the entity that died.
 * @param store The Store of EntityStore where the event is being processed.
 * @param commandBuffer The CommandBuffer for making changes to the EntityStore.
 */
class WKNPCDeathEvent(
	ref: Ref<EntityStore>,
	deathComponent: DeathComponent,
	store: Store<EntityStore>,
	commandBuffer: CommandBuffer<EntityStore>
) : WKOnDeathEvent(
	ref,
	deathComponent,
	store,
	commandBuffer
) {
	/** The NPCEntity that has died. */
	val npc: NPCEntity? = ref.getComponent(NPCEntity.getComponentType()!!)
}