package com.walrusking.wklib.events.block

import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.ecs.UseBlockEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.walrusking.wklib.events.EventData
import com.walrusking.wklib.events.WKEntityEventSystem

/**
 * An abstract class that extends WKEntityEventSystem to handle block post-use events with custom logic.
 *
 * This is an ECS system.
 */
abstract class WKPostUseBlockEvent : WKEntityEventSystem<UseBlockEvent.Post>(UseBlockEvent.Post::class.java) {
	override fun onExecute(data: EventData<UseBlockEvent.Post>) {
		val eventData = WKPostUseBlockEventData(
			data.index,
			data.chunk,
			data.store,
			data.commandBuffer,
			data.event
		)

		onUse(eventData)
	}

	/**
	 * Method to be overridden by subclasses to implement custom block post-use event handling logic.
	 *
	 * @param event The WKPostUseBlockEventData containing information about the block post-use event and context.
	 */
	abstract fun onUse(event: WKPostUseBlockEventData)
}

/**
 * Data class encapsulating information about a block post-use event.
 *
 * @param index The index of the entity in the chunk.
 * @param chunk The archetype chunk containing the entity.
 * @param store The store associated with the entity.
 * @param commandBuffer The command buffer for issuing commands.
 * @param event The UseBlockEvent.Post event instance.
 */
class WKPostUseBlockEventData(
	index: Int,
	chunk: ArchetypeChunk<EntityStore?>,
	store: Store<EntityStore?>,
	commandBuffer: CommandBuffer<EntityStore?>,
	event: UseBlockEvent.Post

) : EventData<UseBlockEvent.Post>(
	index,
	chunk,
	store,
	commandBuffer,
	event
) {
	/* Reference to the player breaking the block */
	val playerRef = chunk.getComponent(index, PlayerRef.getComponentType())

	/* The player who is breaking the block, if available */
	val player = chunk.getComponent(index, Player.getComponentType())

	/* The type of block being damaged */
	val blockType = event.blockType

	/* The interaction type used when interacting with the block */
	val interactionType = event.interactionType

	/* The world where the block damage event is occurring */
	val world = store.externalData.world

	/* The interaction context for the block use event */
	val context = event.context
}