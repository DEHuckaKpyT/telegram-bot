package io.github.dehuckakpyt.telegrambotexample.handling

import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambotexample.exception.CustomException


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.exceptionCommand() {
    command("/exception") {
        throw CustomException("text from exception message")
    }
}