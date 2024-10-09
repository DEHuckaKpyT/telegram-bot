package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.WebAppData


/**
 * Created on 06.10.2024.
 *
 * @author Denis Matytsin
 */
class WebAppDataMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val webAppData: WebAppData get() = message.webAppData!!

    override val type: String = "WEB_APP_DATA"
}