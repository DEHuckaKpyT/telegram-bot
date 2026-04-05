package io.github.dehuckakpyt.telegrambotexample.runner

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.Templating.Companion.with
import io.github.dehuckakpyt.telegrambotexample.template.runnerNotifyWhenStarted
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Singleton
class NotifyWhenStartedRunner(
    @Provided private val bot: TelegramBot,
) : Runner, Templating {
    // всегда доступен TelegramBot для отправки сообщений
    // достаточно просто воспользоваться Koin
    private val chatIdToNotify = 1165327523L

    override suspend fun execute() {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted with ("botUsername" to bot.username), parseMode = "HTML")
    }
}
