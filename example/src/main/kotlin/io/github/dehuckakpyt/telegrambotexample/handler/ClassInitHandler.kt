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
class ClassInitHandler(
    textFactory: SimpleTextFactory
) : BotHandler() {
    init {
        command("/class_init_handler") {
            sendMessage("команда в init ClassHandler:BotHandler '${textFactory.getSimpleText()}' '${getShortText()}'")
        }
    }

    private fun getShortText() = "short text"
}