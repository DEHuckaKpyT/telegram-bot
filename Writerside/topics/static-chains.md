# Static chains

Entry point will be command, callback, reaction etc.:
```kotlin
fun BotHandling.startHandler() {
    command("/start") {
        sendMessage("Enter your name")
    }
}
```

Then it is very simply add the next step of the chain using parameter `next =` and method `step()`:
```kotlin
fun BotHandling.startCommand() {
    command("/start", next = "get_name") {
        sendMessage("Enter your name")
    }

    step("get_name") {
        sendMessage("Hello, $text!")
    }
}
```

<note>Name of argument <code>next</code> are optional but recommended to increase code readability.</note>

This way we can make chains of any length:
```kotlin
fun BotHandling.startCommand() {
    command("/start", next = "get_name") {
        sendMessage("Введите своё имя")
    }

    step("get_name", next = "get_age") {
        sendMessage("Hello, $text!")
        sendMessage("Please enter your age")
    }

    step("get_age") {
        val age = text.toIntOrNull() ?: throw ChatException("An integer is expected")
        
        sendMessage("$age years - recorded")
    }
}
```

<note>If the user sends a message after the end of the chain, a human-readable exception will be displayed.
See <a href="application-exceptions.md">here</a> for how to configure it.</note>
