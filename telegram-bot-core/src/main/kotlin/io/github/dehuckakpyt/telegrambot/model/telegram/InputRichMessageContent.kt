package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty

/**
 * Represents the [content](https://core.telegram.org/bots/api/#inputmessagecontent) of a rich
 * message to be sent as the result of an inline query.
 *
 * @see [InputRichMessageContent] (https://core.telegram.org/bots/api/#inputrichmessagecontent)
 *
 * @author KScript
 *
 * @param richMessage The message to be sent
 */
public data class InputRichMessageContent(
    /**
     * The message to be sent
     */
    @get:JsonProperty("rich_message")
    @param:JsonProperty("rich_message")
    public val richMessage: InputRichMessage,
) : InputMessageContent
