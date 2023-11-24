package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambotexample.factory.SimpleTextFactory
import org.koin.core.annotation.Factory


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Factory
class ClassConstructorHandler(
    textFactory: SimpleTextFactory
) : BotHandler({

    fun getShortText() = "short text"

    command("/class_constructor_handler") {
        sendMessage("команда в конструкторе BotHandler '${textFactory.getSimpleText()}' '${getShortText()}'")
    }
})