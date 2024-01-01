# Step types

By default, the `step()` method expects a text message.
With the `type` parameter you can specify the expected message type from the user:

```kotlin
    step("get_contact", type = CONTACT) {
        sendMessage("Your number is ${contact.phoneNumber}")
    }
```

Multiple message types can be defined for a single step. Example with user registration:

```kotlin
@Factory
class RegistrationHandler : BotHandler({
    // regex with phone number like +7 (123) 456-78-90
    val phonePattern = Regex("\\+?[78]?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}([\\s\\-]?\\d{2}){2}")

    command("/register", next = "get_contact") {
        sendMessage("Enter a number or share your contact to register", 
            replyMarkup = contactButton("Share contact"))
    }

    step("get_contact", type = CONTACT) {
        sendMessage("${contact.firstName}, You have successfully registered with the number ${contact.phoneNumber}!", 
            replyMarkup = removeKeyboard())
    }

    step("get_contact", type = TEXT, next = "get_firstname") {
        phonePattern.find(text) ?: throw ChatException("Incorrect phone number format")

        sendMessage("And what is your name?", replyMarkup = removeKeyboard())
        transfer(text)
    }

    step("get_firstname") {
        val phone = transferred<String>()
        sendMessage("$text, You have successfully registered with the number $phone!")
    }
})
```

Available types at this time:
```kotlin
object MessageType {
    val TEXT = TextMessageArgument::class
    val PHOTO = PhotoMessageArgument::class
    val AUDIO = AudioMessageArgument::class
    val VOICE = VoiceMessageArgument::class
    val CONTACT = ContactMessageArgument::class
    val DOCUMENT = DocumentMessageArgument::class
}
```