package io.github.dehuckakpyt.telegrambot.config.template

import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramBotTemplatingActualConfig {
    val htmlFormatter: HtmlFormatter
    val templater: Templater
}