package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents an inline button that switches the current user to inline mode in a chosen
 * chat, with an optional default inline query.
 *
 * @see [SwitchInlineQueryChosenChat]
 * (https://core.telegram.org/bots/api/#switchinlinequerychosenchat)
 *
 * @author KScript
 *
 * @param query *Optional*. The default inline query to be inserted in the input field. If left
 * empty, only the bot's username will be inserted
 * @param allowUserChats *Optional*. True, if private chats with users can be chosen
 * @param allowBotChats *Optional*. True, if private chats with bots can be chosen
 * @param allowGroupChats *Optional*. True, if group and supergroup chats can be chosen
 * @param allowChannelChats *Optional*. True, if channel chats can be chosen
 */
public data class SwitchInlineQueryChosenChat(
    /**
     * *Optional*. The default inline query to be inserted in the input field. If left empty, only
     * the bot's username will be inserted
     */
    @get:JsonProperty("query")
    @param:JsonProperty("query")
    public val query: String? = null,
    /**
     * *Optional*. True, if private chats with users can be chosen
     */
    @get:JsonProperty("allow_user_chats")
    @param:JsonProperty("allow_user_chats")
    public val allowUserChats: Boolean? = null,
    /**
     * *Optional*. True, if private chats with bots can be chosen
     */
    @get:JsonProperty("allow_bot_chats")
    @param:JsonProperty("allow_bot_chats")
    public val allowBotChats: Boolean? = null,
    /**
     * *Optional*. True, if group and supergroup chats can be chosen
     */
    @get:JsonProperty("allow_group_chats")
    @param:JsonProperty("allow_group_chats")
    public val allowGroupChats: Boolean? = null,
    /**
     * *Optional*. True, if channel chats can be chosen
     */
    @get:JsonProperty("allow_channel_chats")
    @param:JsonProperty("allow_channel_chats")
    public val allowChannelChats: Boolean? = null,
)
