package io.github.dehuckakpyt.telegrambotexample.handler

import com.dehucka.microservice.exception.CustomException
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.container.MassageContainer.Companion.CONTACT
import io.github.dehuckakpyt.telegrambot.container.MassageContainer.Companion.TEXT
import io.github.dehuckakpyt.telegrambot.ext.contactButton
import io.github.dehuckakpyt.telegrambot.ext.removeKeyboard
import io.github.dehuckakpyt.telegrambotexample.template.*


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.registerCommand() {
    command("/register", next = "get contact") {
        sendMessage(register, replyMarkup = contactButton(registerContactButton))
    }

    step("get contact", type = CONTACT) {
        sendMessage(registerComplete with contact, replyMarkup = removeKeyboard())
    }

    val phonePattern = Regex("\\+?[78]?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}([\\s\\-]?\\d{2}){2}")

    step("get contact", type = TEXT, next = "get firstname") {
        phonePattern.find(text) ?: throw CustomException(registerWrongPhoneFormat)

        sendMessage(registerGetFirstname, replyMarkup = removeKeyboard())
        transferToNext(text)
    }

    step("get firstname") {
        val phone = transferred<String>()
        sendMessage(registerComplete with mapOf("firstName" to text, "phoneNumber" to phone))
    }
}