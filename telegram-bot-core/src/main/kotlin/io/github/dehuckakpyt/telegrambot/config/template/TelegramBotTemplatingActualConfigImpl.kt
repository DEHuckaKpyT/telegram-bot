package io.github.dehuckakpyt.telegrambot.config.template

import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TelegramBotTemplatingActualConfigImpl : TelegramBotTemplatingActualConfig {
    override lateinit var htmlFormatter: HtmlFormatter
    override lateinit var templater: Templater
}