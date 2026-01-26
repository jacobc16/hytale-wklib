## Creating a Block Break Event

To create a Block Break Event in your plugin, you need to extend the `WKBlockBreakEvent` class and override the `onBreak` method. This method will be called whenever a block is broken in the game.

```kotlin
class BlockBreakTest : WKBlockBreakEvent() {
	override fun onBreak(event: WKBlockBreakEventData) {

	}
}
```

## Creating a Block Damage Event

To create a Block Damage Event in your plugin, you need to extend the `WKBlockDamageEvent` class and override the `onDamage` method. This method will be called whenever a block is damaged in the game.

```kotlin
class BlockDamageTest : WKBlockDamageEvent() {
	override fun onDamage(event: WKBlockDamageEventData) {
		
	}
}
```

## Creating a Block Use Event

You can create a block pre-use / post-use event by extending the `WKPreUseBlockEvent` or `WKPostUseBlockEvent` class and overriding the `onUse` method.

```kotlin
class BlockUseTest : WKPreUseBlockEvent() {
    override fun onUse(event: WKPreUseBlockEventData) {
        
    }
}

class BlockUsePostTest : WKPostUseBlockEvent() {
    override fun onUse(event: WKPostUseBlockEventData) {
        
    }
}
```

## Register Events

You can register all block events in the same way:

```kotlin
override fun setup() {
	Events.registerSystem(BlockBreakTest())
    Events.registerSystem(BlockDamageTest())
    Events.registerSystem(BlockUseTest())
    Events.registerSystem(BlockUsePostTest())
}
```
