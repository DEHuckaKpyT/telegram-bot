package io.github.dehuckakpyt.telegrambotexample.runner

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.model.type.ParseMode.HTML
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.Templating.Companion.with
import io.github.dehuckakpyt.telegrambotexample.template.runnerNotifyWhenStarted
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Component
class NotifyWhenStartedRunner(
    // всегда доступен TelegramBot для отправки сообщений
    private val bot: TelegramBot,
) : CommandLineRunner, Templating {
    private val chatIdToNotify = 1165327523L

    override fun run(vararg args: String): Unit = runBlocking {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted with ("botUsername" to bot.username), parseMode = HTML)
    }
}