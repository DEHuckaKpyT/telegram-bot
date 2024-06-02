package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Describes an inline message sent by a [Web App](https://core.telegram.org/bots/webapps) on behalf
 * of a user.
 *
 * @see [SentWebAppMessage] (https://core.telegram.org/bots/api/#sentwebappmessage)
 *
 * @author KScript
 *
 * @param inlineMessageId *Optional*. Identifier of the sent inline message. Available only if there
 * is an [inline keyboard](https://core.telegram.org/bots/api/#inlinekeyboardmarkup) attached to the
 * message.
 */
public data class SentWebAppMessage(
    /**
     * *Optional*. Identifier of the sent inline message. Available only if there is an [inline
     * keyboard](https://core.telegram.org/bots/api/#inlinekeyboardmarkup) attached to the message.
     */
    @get:JsonProperty("inline_message_id")
    @param:JsonProperty("inline_message_id")
    public val inlineMessageId: String? = null,
)
