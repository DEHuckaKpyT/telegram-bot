package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * Upon receiving a message with this object, Telegram clients will display a reply interface to the
 * user (act as if the user has selected the bot's message and tapped 'Reply'). This can be extremely
 * useful if you want to create user-friendly step-by-step interfaces without having to sacrifice
 * [privacy mode](https://core.telegram.org/bots/features#privacy-mode). Not supported in channels and
 * for messages sent on behalf of a Telegram Business account.
 *
 * @see [ForceReply] (https://core.telegram.org/bots/api/#forcereply)
 *
 * @author KScript
 *
 * @param forceReply Shows reply interface to the user, as if they manually selected the bot's
 * message and tapped 'Reply'
 * @param inputFieldPlaceholder *Optional*. The placeholder to be shown in the input field when the
 * reply is active; 1-64 characters
 * @param selective *Optional*. Use this parameter if you want to force reply from specific users
 * only. Targets: 1) users that are @mentioned in the *text* of the
 * [Message](https://core.telegram.org/bots/api/#message) object; 2) if the bot's message is a reply to
 * a message in the same chat and forum topic, sender of the original message.
 */
public data class ForceReply(
    /**
     * Shows reply interface to the user, as if they manually selected the bot's message and tapped
     * 'Reply'
     */
    @get:JsonProperty("force_reply")
    @param:JsonProperty("force_reply")
    public val forceReply: Boolean,
    /**
     * *Optional*. The placeholder to be shown in the input field when the reply is active; 1-64
     * characters
     */
    @get:JsonProperty("input_field_placeholder")
    @param:JsonProperty("input_field_placeholder")
    public val inputFieldPlaceholder: String? = null,
    /**
     * *Optional*. Use this parameter if you want to force reply from specific users only. Targets:
     * 1) users that are @mentioned in the *text* of the
     * [Message](https://core.telegram.org/bots/api/#message) object; 2) if the bot's message is a
     * reply to a message in the same chat and forum topic, sender of the original message.
     */
    @get:JsonProperty("selective")
    @param:JsonProperty("selective")
    public val selective: Boolean? = null,
) : ReplyMarkup
