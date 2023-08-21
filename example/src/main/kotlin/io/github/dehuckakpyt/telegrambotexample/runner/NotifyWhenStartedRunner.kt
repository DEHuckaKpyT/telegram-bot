package io.github.dehuckakpyt.telegrambotexample.runner

import com.elbekd.bot.types.ParseMode.Html
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.template.TemplatingExtended
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
class NotifyWhenStartedRunner : Runner, TemplatingExtended {
    private val bot = get<TelegramBot>()
    private val chatIdToNotify = 1165327523L

    override suspend fun execute() {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted("botUsername" to bot.username), parseMode = Html)
    }
}