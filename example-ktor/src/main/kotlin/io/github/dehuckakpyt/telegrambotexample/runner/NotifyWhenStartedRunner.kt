package io.github.dehuckakpyt.telegrambotexample.runner

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.Templating.Companion.with
import io.github.dehuckakpyt.telegrambotexample.template.runnerNotifyWhenStarted
import org.koin.core.annotation.Single
import org.koin.core.component.get


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Single
class NotifyWhenStartedRunner : Runner, Templating {
    // всегда доступен TelegramBot для отправки сообщений
    // достаточно просто воспользоваться Koin
    private val bot = get<TelegramBot>()
    private val chatIdToNotify = 1165327523L

    override suspend fun execute() {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted with ("botUsername" to bot.username), parseMode = "HTML")
    }
}