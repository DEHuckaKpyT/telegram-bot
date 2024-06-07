package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import kotlinx.coroutines.delay


/**
 * Created on 07.06.2024.
 *
 * @author Denis Matytsin
 */
@HandlerComponent
class DelaysHandler : BotHandler({

    command("/delay") {
        sendMessage("Before 5 seconds")
        delay(5000)
        sendMessage("After 5 seconds")
    }
})