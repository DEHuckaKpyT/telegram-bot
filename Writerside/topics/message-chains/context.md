# Context

## Container context

Anywhere in the application you can call the `currentContainerContext()` (or `currentContainerContextOrNull()`) method to get info about the current command, step, chat and user.
It works only if you don\`t change coroutine context between `BotHandling` and `currentContainerContext()`. For example:

```kotlin
fun BotHandling.startCommand() {
    command("/start") {
        printContext()
    }
}

suspend fun printContext() {
    val currentContext = currentContainerContext()

    // prints "COMMAND"
    println(currentContext.type)
    // prints "/start"
    println(currentContext.step)
    // prints "User(id=123, isBot=false, firstName=., lastName=., username=.,...)"
    println(currentContext.from)
    // prints "Chat(id=123, type=private, title=null, username=., firstName=.,...)"
    println(currentContext.chat)
}
```