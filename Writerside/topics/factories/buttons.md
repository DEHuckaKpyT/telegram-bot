# Buttons

## Inside handlers

```kotlin
fun BotHandling.sendCallHandler() {

    command("/call") {
        sendMessage("message text", replyMarkup = inlineKeyboard(
            callbackButton("first button", "simple"),
            callbackButton("second button", "with_obj", AnyObject())
        ))
    }
    callback("simple") {
        sendMessage("call1")
    }
    callback("with_obj") {
        val obj = transferred<AnyObject>()
        sendMessage("call2 ${obj.name}")
    }
    command("/auth") {
        sendMessage("auth message", replyMarkup = inlineKeyboard(
            authButton("auth in system", "https://your-system.com/auth-by-telegram"),
        ))
    }
}
```

## In other classes

```kotlin
class PresentationTestClass(
    private val bot: TelegramBot,
    private val buttonFactory: ButtonFactory,
) {
    val chatId = 123L
    val fromId = 123L
    sendMessage(chatId, "message text", replyMarkup = inlineKeyboard(
        buttonFactory.callbackButton("first button", "simple"),
        // for transfer objects you need to specify chatId and fromId
        buttonFactory.callbackButton(chatId, fromId, "second button", "with_obj", AnyObject())
    ))
}
```

## More features

More useful methods tou can see [here](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/telegram-bot-core/src/main/kotlin/io/github/dehuckakpyt/telegrambot/factory/keyboard/Keyboards.kt).