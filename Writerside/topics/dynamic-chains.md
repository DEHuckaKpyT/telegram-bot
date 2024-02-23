# Dynamic chains

Using the `next()` method, you can dynamically select the next step:

```kotlin
fun BotHandling.startCommand() {
    command("/start") {
        next("get_name")
        sendMessage("Enter your name")
    }

    step("get_name") {
        sendMessage("Hello, $text!")
    }
}
```

It is also possible to combine these approaches. For example:

```kotlin
fun BotHandling.fillFormHandler() {
    command("/fill_form", next = "get_age") {
        sendMessage("Let`s start")
        sendMessage("How old are you?")
    }
    
    step("get_age") {
        val age = text.toIntOrNull() ?: throw ChatException("An integer is expected")

        when {
            age < 14 -> {
                sendMessage("I'm sorry, you're not a match :-(")
            }
            age < 18 -> {
                next("get_name")
                sendMessage("What is your name?")
            }
            else -> {
                next("get_driving_record")
                sendMessage("What is your driving record?")
            }
        }
    }
    
    // this step is "additional"
    // because it will be called only if the user is over 18 years old
    step("get_driving_record", next = "get_name") {
        val experience = text.toIntOrNull() ?: throw ChatException("An integer is expected")
        // save driving record
        
        sendMessage("What is your name?")
    }

    // this step will be called any way
    step("get_name") {
        // save name
    }
}
```

If you need to break the chain, you can use the method `finalizeChain()`:

```kotlin
fun BotHandling.fillFormHandler() {
    command("/fill_form", "get_age") {
        sendMessage("Let`s start")
        sendMessage("How old are you?")
    }
    
    step("get_age", next = "get_name") {
        val age = text.toIntOrNull() ?: throw ChatException("An integer is expected")
        
        if (age < 18) {
            finalizeChain()
            sendMessage("I'm sorry, you're not a match :-(")
            return@step
        }
        // save age

        sendMessage("What is your name?")
    }

    step("get_name") {
        // save name
    }
}
```