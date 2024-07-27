# Exceptions

Exceptions can be thrown while processing user responses.
The exception text can be displayed to the user.
In case of an unexpected exception, a default message will be displayed.
See <a href="exception-handling.md">here</a> for how to **configure** it and **add custom exceptions**.

`ChatException` will write an exception message back to the chat where the request came from.

```kotlin
fun BotHandling.exceptionCommand() {
    command("/exception") {
        throw ChatException("usually exception to display to the user")
    }
}
```

```kotlin
fun BotHandling.exceptionCommand() {
    command("/exception_unexpected") {
        // this message will be displayed only in logs (with stacktrace)
        throw RuntimeException("user will not see this message")
        // user will see message like "Oops! Unexpected Error. Please contact with developers."
    }
}
```

If an exception is thrown at some step, the bot will wait for a message from the user again and again at the same step.
Until the user enters a valid response.

```kotlin
fun BotHandling.startCommand() {
    command("/start", next = "get_age") {
        sendMessage("Please enter your age")
    }

    step("get_age", next = "get_name") {
        // until the user enters an integer, the chain will be at this step
        val age = text.toIntOrNull() ?: throw ChatException("An integer is expected")

        sendMessage("$age years - recorded")
        sendMessage("Please enter your name")
    }

    step("get_name") {
        sendMessage("Hello, $text!")
    }
}
```

