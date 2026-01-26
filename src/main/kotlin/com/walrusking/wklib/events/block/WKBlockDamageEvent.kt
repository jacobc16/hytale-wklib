package com.walrusking.wklib.events.block

import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.event.events.ecs.DamageBlockEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.walrusking.wklib.events.EventData
import com.walrusking.wklib.events.WKEntityEventSystem

/**
 * An abstract class that extends WKEntityEventSystem to handle block damage events with custom logic.
 *
 * This is an ECS system.
 */
abstract class WKBlockDamageEvent : WKEntityEventSystem<DamageBlockEvent>(DamageBlockEvent::class.java) {
	override fun onExecute(data: EventData<DamageBlockEvent>) {
		val eventData = WKBlockDamageEventData(
			data.index,
			data.chunk,
			data.store,
			data.commandBuffer,
			data.event
		)

		onDamage(eventData)
	}

	/**
	 * Method to be overridden by subclasses to implement custom block damage event handling logic.
	 *
	 * @param event The WKBlockDamageEventData containing information about the block damage event and context.
	 */
	abstract fun onDamage(event: WKBlockDamageEventData)
}

/**
 * Data class encapsulating information about a block damage event.
 *
 * @property blockType The type of block being damaged.
 * @property world The world where the block damage event is occurring.
 * @property damageAmount The amount of damage being dealt to the block.
 * @property currentDamage The current damage level of the block after applying the damage.
 */
class WKBlockDamageEventData(
	index: Int,
	chunk: ArchetypeChunk<EntityStore?>,
	store: Store<EntityStore?>,
	commandBuffer: CommandBuffer<EntityStore?>,
	event: DamageBlockEvent
) : EventData<DamageBlockEvent>(index, chunk, store, commandBuffer, event) {
	/* Reference to the player breaking the block */
	val playerRef = chunk.getComponent(index, PlayerRef.getComponentType())

	/* The player who is breaking the block, if available */
	val player = chunk.getComponent(index, Player.getComponentType())

	/* The item in the player's hand when damaging the block */
	val itemInHand = event.itemInHand

	/* The type of block being damaged */
	val blockType = event.blockType

	/* The world where the block damage event is occurring */
	val world = store.externalData.world

	/* The amount of damage being dealt to the block */
	val damageAmount = event.damage

	/* The current damage level of the block after applying the damage */
	val currentDamage = event.currentDamage
}