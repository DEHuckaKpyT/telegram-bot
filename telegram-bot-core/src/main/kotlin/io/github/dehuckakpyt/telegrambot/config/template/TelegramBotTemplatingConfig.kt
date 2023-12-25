package io.github.dehuckakpyt.telegrambot.config.template

import freemarker.template.Configuration
import io.github.dehuckakpyt.telegrambot.config.receiver.UpdateReceiverConfig
import io.github.dehuckakpyt.telegrambot.formatter.HtmlFormatter


/**
 * Created on 23.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class TelegramBotTemplatingConfig(
    var htmlFormatter: HtmlFormatter? = null,
    internal var freemarkerConfig: (Configuration.() -> Unit)? = null,
) {

    fun freemarker(block: Configuration.() -> Unit) {
        freemarkerConfig = block
    }
}

fun UpdateReceiverConfig.templating(block: TelegramBotTemplatingConfig.() -> Unit) {
    val templatingConfig = TelegramBotTemplatingConfig().apply(block)
    this.htmlFormatter = templatingConfig.htmlFormatter
    templatingConfig.freemarkerConfig?.let { templatingConfig.freemarkerConfig!!.invoke(this.freemarkerConfig) }
}