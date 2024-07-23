package io.github.dehuckakpyt.telegrambotexample.runner

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.github.dehuckakpyt.telegrambotexample.holder.MessageTemplateHolder
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
    private val template: MessageTemplateHolder,
    private val templater: Templater,
) : CommandLineRunner {
    private val chatIdToNotify = 1165327523L

    override fun run(vararg args: String): Unit = runBlocking {
        with(templater) {
            bot.sendMessage(chatIdToNotify, template.runnerNotifyWhenStarted with ("botUsername" to bot.username), parseMode = "HTML")
        }
    }
}