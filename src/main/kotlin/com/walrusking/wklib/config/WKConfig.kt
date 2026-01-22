package com.walrusking.wklib.config

open class WKConfig(
	var configVersion: Int = 1
) {
	open fun upgradeConfig(oldVersion: Int): List<ConfigChange> {
		return emptyList()
	}
}

data class ConfigChange(val fieldName: String, val changeType: ConfigChangeType)

enum class ConfigChangeType {
	ADDED,
	REMOVED,
}