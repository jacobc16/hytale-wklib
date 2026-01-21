package com.walrusking.wklib.helpers

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.util.NotificationUtil
import com.walrusking.wklib.utilities.TextFormat

fun PlayerRef.getPlayer(): Player? {
	val ref = reference

	if (ref == null || !ref.isValid) {
		return null
	}

	val store = ref.store

	return store.getComponent(ref, Player.getComponentType())
}

fun PlayerRef.sendNotification(message: Message) {
	NotificationUtil.sendNotification(packetHandler, message)
}

fun PlayerRef.sendNotification(message: String) {
	NotificationUtil.sendNotification(packetHandler, message)
}

fun PlayerRef.sendNotification(primaryMessage: Message, secondaryMessage: Message) {
	NotificationUtil.sendNotification(packetHandler, primaryMessage, secondaryMessage)
}

fun PlayerRef.sendNotification(primaryMessage: String, secondaryMessage: String) {
	NotificationUtil.sendNotification(packetHandler, primaryMessage, secondaryMessage)
}

fun PlayerRef.sendFormattedMessage(message: String) {
	sendMessage(TextFormat.format(message))
}

fun PlayerRef.sendFormattedNotification(message: String) {
	sendNotification(TextFormat.format(message))
}

fun PlayerRef.sendFormattedNotification(primaryMessage: String, secondaryMessage: String) {
	sendNotification(TextFormat.format(primaryMessage), TextFormat.format(secondaryMessage))
}