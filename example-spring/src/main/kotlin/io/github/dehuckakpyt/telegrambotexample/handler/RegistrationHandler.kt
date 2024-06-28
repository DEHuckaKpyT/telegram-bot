package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.container.message.MessageType.CONTACT
import io.github.dehuckakpyt.telegrambot.container.message.MessageType.TEXT
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.factory.keyboard.contactKeyboard
import io.github.dehuckakpyt.telegrambot.factory.keyboard.removeKeyboard
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambotexample.template.*


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@HandlerComponent
class RegistrationHandler : BotHandler({
    val phonePattern = Regex("\\+?[78]?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}([\\s\\-]?\\d{2}){2}")

    command("/register", next = "get_contact") {
        sendMessage(register, replyMarkup = contactKeyboard(registerContactButton))
    }

    // if you specify a type, the body of the method will contain a container with fields and methods of this type
    // for example, in the CONTACT type you can get the field contact: Contact not null
    step("get_contact", type = CONTACT) {
        sendMessage(registerComplete with contact, replyMarkup = removeKeyboard())
    }

    // if you specify the TEXT type (you may not specify it, because it is selected by default)
    // there will be a text not null field inside, and the contact field will no longer exist
    // accordingly, if the user sends a contact, the first method (type = CONTACT) will be called.
    // if the user sends a number in text, the second method (type = TEXT) will be called.
    // if the user sends something else, both methods will be ignored, the user will get a message about available message types.
    step("get_contact", type = TEXT, next = "get_firstname") {
        phonePattern.find(text) ?: throw ChatException(registerWrongPhoneFormat)

        sendMessage(registerGetFirstname, replyMarkup = removeKeyboard())
        transfer(text)
    }

    step("get_firstname") {
        val phone = transferred<String>()
        sendMessage(registerComplete with mapOf("firstName" to text, "phoneNumber" to phone))
    }
})