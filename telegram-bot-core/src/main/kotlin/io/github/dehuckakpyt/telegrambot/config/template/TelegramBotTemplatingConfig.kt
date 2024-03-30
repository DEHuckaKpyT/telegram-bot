package io.github.dehuckakpyt.telegrambot.config.template

import freemarker.template.Configuration
import freemarker.template.Version
import io.github.dehuckakpyt.telegrambot.config.TelegramBotActualConfig
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter


/**
 * Created on 23.12.2023.
 *
 * Configuration for telegram bot templating context.
 *
 * @author Denis Matytsin
 */
class TelegramBotTemplatingConfig(

    /** Formatter for clean/escape html tags from string */
    var htmlFormatter: (TelegramBotActualConfig.() -> HtmlFormatter)? = null,
) {
    internal val freemarkerConfig: Configuration = Configuration(Version("2.3.32"))

    /** Configure freemarker */
    fun freemarker(block: Configuration.() -> Unit) {
        freemarkerConfig.block()
    }
}