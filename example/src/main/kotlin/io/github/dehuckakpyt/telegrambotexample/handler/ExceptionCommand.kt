package io.github.dehuckakpyt.telegrambotexample.handler

import com.dehucka.microservice.exception.CustomException
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException


/**
 * Created on 23.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.exceptionCommand() {

    command("/exception") {
        // По умолчанию - ошибка, которая выведется в чат пользователю
        throw ChatException("Обычная ошибка")
    }

    command("/private_exception") {
        // По умолчанию - ошибка, которая выведется только в личный чат
        // В группах ошибка будет проигнорирована
        throw PrivateChatException("Ошибка для личного чата")
    }

    command("/custom_exception") {
        // Также можно добавить свои ошибки и действия для них
        throw CustomException("Своя ошибка")
    }
}