package com.walrusking.wklib.config

import com.hypixel.hytale.server.core.util.Config
import com.walrusking.wklib.logging.WKLogger
import com.walrusking.wklib.plugins.WKPlugin
import java.util.Locale.getDefault

class ConfigUtil {
	companion object {
		fun getConfigName(configName: String): String {
			return configName.replace(" ", "").lowercase(getDefault())
		}

		fun loadConfig(plugin: WKPlugin, config: Config<*>) {
			val pluginName = plugin.pluginName

			val configName = getConfigName(pluginName)
			val dataDirectory = plugin.dataDirectory
			val pluginConfig = dataDirectory.resolve("$configName.json").toFile()

			if (!pluginConfig.exists()) {
				config.save()
				WKLogger("WKLib:Configs").info("Created new config $pluginName")
			} else {
				config.load()
				WKLogger("WKLib:Configs").info("Loaded existing config $pluginName")
			}
		}
	}
}