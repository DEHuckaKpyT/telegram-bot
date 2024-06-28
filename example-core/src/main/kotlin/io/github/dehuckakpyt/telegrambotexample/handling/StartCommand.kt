package io.github.dehuckakpyt.telegrambotexample.handling

import io.github.dehuckakpyt.telegrambot.handling.BotHandling


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.startCommand() {
    command("/start") {
        sendMessage("Hello! My name is \${botUsername} :-)" with ("botUsername" to bot.username))
    }
}