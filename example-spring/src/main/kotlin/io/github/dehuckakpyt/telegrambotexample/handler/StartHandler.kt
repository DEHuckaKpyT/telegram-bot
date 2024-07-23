package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambotexample.holder.MessageTemplateHolder

@HandlerComponent
class StartHandler(
    template: MessageTemplateHolder,
) : BotHandler({

    command("/start") {
        sendMessage(template.start)
        sendMessage("Hello! My name is ${bot.username} :-)")
    }
})