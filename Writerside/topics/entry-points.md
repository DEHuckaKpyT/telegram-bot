# Entry points

## Command processing

Command handlers have all the fields of a normal text message.
But in addition to them, argument retrieval is available.

For example, from the string `/some_command__abc_123 987 cxz` the command `/some_command`, the parameter `abc_123` and the argument `987 cxz` will be obtained.
In the handler, you can get them from the `commandPathParam` (`abc_123`) and `commandArgument` (`987 cxz`) fields.

```kotlin
fun BotHandling.withArgsCommand() {
    command("/with_args") {
        // when a command is called with the string "/with_args__arg1_0 arg2 0", the following will be displayed
        // "commandPathParam = arg1_0, commandArgument = arg2 0"
        sendMessage("commandPathParam = $commandPathParam, commandArgument = $commandArgument")
    }
}
```

The parameter may be needed in the case of selecting an item from a list.
The command will look something like `/item__a67sdfnas5`.
When the user clicks on the command, you will be able to display a specific item by the id `a67sdfnas5`.

The command argument may be needed when creating a discussion.
The user can type `/topic Kotlin vs Java`, and you can create a discussion with name `Kotlin vs Java`.

## Creating buttons with callbacks

`callback()` method can be used to handle callback with data:

```kotlin
fun BotHandling.callbackCommand() {
    command("/callback") {
        sendMessage("Select an action", replyMarkup = inlineKeyboard(
            callbackButton("Buy slippers", next = "buy", content = "some id 1"),
            callbackButton("Buy hats", next = "buy", content = "some id 2"),
            callbackButton("Give a feedback", next = "get_feedback_intro")))
    }
    
    callback("buy") {
        val itemId = transferred<String>()

        sendMessage("Selected item with identifier $itemId")
    }
    
    callback("get_feedback_intro", next = "get_feedback") {
        sendMessage("Please write us what you think about our company")
    }
    
    step("get_feedback") {
        // save text

        sendMessage("Thank you for your feedback!")
    }
}
```

<note>With default settings, if the callback string is not more than 64 characters long, it is passed in the callback data.
Otherwise, the id for the object is passed to the callback.</note>