package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.BotHandler
import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambotexample.factory.SimpleTextFactory


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@HandlerComponent
class ClassConstructorHandler(
    textFactory: SimpleTextFactory,
) : BotHandler({

    fun getShortText() = "short text"

    command("/class_constructor_handler") {
        sendMessage("команда в конструкторе BotHandler '${textFactory.getSimpleText()}' '${getShortText()}'")
    }
})