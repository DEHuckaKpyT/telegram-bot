package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.context.SpringContext.context
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.model.type.supplement.content.ContentFactory.content
import io.github.dehuckakpyt.telegrambotexample.template.start

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage(start)
        sendMessage("Hello! My name is ${bot.username} :-)")
    }
}