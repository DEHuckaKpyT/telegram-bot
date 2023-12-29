package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.handling.BotHandling


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.withArgsCommand() {
    command("/with_args") {
        // при вызове команды строкой "/with_args__arg1_0 arg2 0" будет выведено
        // "commandPathParam = arg1_0, commandArgument = arg2 0"
        sendMessage("commandPathParam = $commandPathParam, commandArgument = $commandArgument")
    }
}