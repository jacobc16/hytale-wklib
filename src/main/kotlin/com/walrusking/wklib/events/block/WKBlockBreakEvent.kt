package com.walrusking.wklib.events.block

import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.walrusking.wklib.events.EventData
import com.walrusking.wklib.events.WKEntityEventSystem

/**
 * An abstract class that extends WKEntityEventSystem to handle block break events with custom logic.
 *
 * This is an ECS system.
 */
abstract class WKBlockBreakEvent : WKEntityEventSystem<BreakBlockEvent>(BreakBlockEvent::class.java) {
	override fun onExecute(data: EventData<BreakBlockEvent>) {
		val eventData = WKBlockBreakEventData(
			data.index,
			data.chunk,
			data.store,
			data.commandBuffer,
			data.event
		)

		onBreak(eventData)
	}

	/**
	 * Method to be overridden by subclasses to implement custom block break event handling logic.
	 *
	 * @param event The WKBlockBreakEventData containing information about the block break event and context.
	 */
	abstract fun onBreak(event: WKBlockBreakEventData)
}

/**
 * Data class encapsulating information about a block break event.
 *
 * @property index The index of the entity in the chunk.
 * @property chunk The ArchetypeChunk containing the entities.
 * @property store The Store managing the entities.
 * @property commandBuffer The CommandBuffer for issuing commands to entities.
 * @property event The BreakBlockEvent instance.
 */
class WKBlockBreakEventData(
	index: Int,
	chunk: ArchetypeChunk<EntityStore?>,
	store: Store<EntityStore?>,
	commandBuffer: CommandBuffer<EntityStore?>,
	event: BreakBlockEvent
) : EventData<BreakBlockEvent>(index, chunk, store, commandBuffer, event) {
	/* Reference to the player breaking the block */
	val playerRef = chunk.getComponent(index, PlayerRef.getComponentType())

	/* The player who is breaking the block, if available */
	val player = chunk.getComponent(index, Player.getComponentType())

	/* The item in the player's hand when breaking the block */
	val itemInHand = event.itemInHand

	/* The type of block being broken */
	val blockType = event.blockType

	/* The world where the block break event is occurring */
	val world = store.externalData.world
}