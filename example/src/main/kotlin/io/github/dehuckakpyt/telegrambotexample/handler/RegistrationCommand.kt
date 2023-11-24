package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.container.MessageContainer.Companion.CONTACT
import io.github.dehuckakpyt.telegrambot.container.MessageContainer.Companion.TEXT
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.factory.contactKeyboard
import io.github.dehuckakpyt.telegrambot.factory.removeKeyboard
import io.github.dehuckakpyt.telegrambotexample.template.*


/**
 * Created on 17.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.registerCommand() {
    val phonePattern = Regex("\\+?[78]?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}([\\s\\-]?\\d{2}){2}")

    command("/register", next = "get contact") {
        sendMessage(register, replyMarkup = contactKeyboard(registerContactButton))
    }

    // если указать тип, то в теле метода будет контейнер с полями и методами этого типа
    // например, в типе CONTACT можно получить поле contact: Contact not null
    step("get contact", type = CONTACT) {
        sendMessage(registerComplete with contact, replyMarkup = removeKeyboard())
    }

    // если указать тип TEXT (можно не указывать, т к он выбран по умолчанию)
    // то внутри будет поле text not null, поля contact уже не будет
    // соответственно, если пользователь отправит контакт, то сработает первый метод (type = CONTACT)
    // если отправит номер текстом, то вызовется второй метод (type = TEXT)
    // если пользователь отправит что-то другое, то эти оба метода будут проигнорированы, пользователю выведется сообщение о доступных типах сообщения
    step("get contact", type = TEXT, next = "get firstname") {
        phonePattern.find(text) ?: throw ChatException(registerWrongPhoneFormat)

        sendMessage(registerGetFirstname, replyMarkup = removeKeyboard())
        transfer(text)
    }

    step("get firstname") {
        val phone = transferred<String>()
        sendMessage(registerComplete with mapOf("firstName" to text, "phoneNumber" to phone))
    }
}