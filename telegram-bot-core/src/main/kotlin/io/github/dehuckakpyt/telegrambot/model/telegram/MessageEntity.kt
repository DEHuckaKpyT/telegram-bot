package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String

/**
 * This object represents one special entity in a text message. For example, hashtags, usernames,
 * URLs, etc.
 *
 * @see [MessageEntity] (https://core.telegram.org/bots/api/#messageentity)
 *
 * @author KScript
 *
 * @param type Type of the entity. Currently, can be “mention” (`@username`), “hashtag”
 * (`#hashtag`), “cashtag” (`$USD`), “bot_command” (`/start@jobs_bot`), “url” (`https://telegram.org`),
 * “email” (`do-not-reply@telegram.org`), “phone_number” (`+1-212-555-0123`), “bold” (**bold text**),
 * “italic” (*italic text*), “underline” (underlined text), “strikethrough” (strikethrough text),
 * “spoiler” (spoiler message), “blockquote” (block quotation), “expandable_blockquote”
 * (collapsed-by-default block quotation), “code” (monowidth string), “pre” (monowidth block),
 * “text_link” (for clickable text URLs), “text_mention” (for users [without
 * usernames](https://telegram.org/blog/edit#new-mentions)), “custom_emoji” (for inline custom emoji
 * stickers)
 * @param offset Offset in [UTF-16 code units](https://core.telegram.org/api/entities#entity-length)
 * to the start of the entity
 * @param length Length of the entity in [UTF-16 code
 * units](https://core.telegram.org/api/entities#entity-length)
 * @param url *Optional*. For “text_link” only, URL that will be opened after user taps on the text
 * @param user *Optional*. For “text_mention” only, the mentioned user
 * @param language *Optional*. For “pre” only, the programming language of the entity text
 * @param customEmojiId *Optional*. For “custom_emoji” only, unique identifier of the custom emoji.
 * Use [getCustomEmojiStickers](https://core.telegram.org/bots/api/#getcustomemojistickers) to get full
 * information about the sticker
 */
public data class MessageEntity(
    /**
     * Type of the entity. Currently, can be “mention” (`@username`), “hashtag” (`#hashtag`),
     * “cashtag” (`$USD`), “bot_command” (`/start@jobs_bot`), “url” (`https://telegram.org`), “email”
     * (`do-not-reply@telegram.org`), “phone_number” (`+1-212-555-0123`), “bold” (**bold text**),
     * “italic” (*italic text*), “underline” (underlined text), “strikethrough” (strikethrough text),
     * “spoiler” (spoiler message), “blockquote” (block quotation), “expandable_blockquote”
     * (collapsed-by-default block quotation), “code” (monowidth string), “pre” (monowidth block),
     * “text_link” (for clickable text URLs), “text_mention” (for users [without
     * usernames](https://telegram.org/blog/edit#new-mentions)), “custom_emoji” (for inline custom
     * emoji stickers)
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String,
    /**
     * Offset in [UTF-16 code units](https://core.telegram.org/api/entities#entity-length) to the
     * start of the entity
     */
    @get:JsonProperty("offset")
    @param:JsonProperty("offset")
    public val offset: Int,
    /**
     * Length of the entity in [UTF-16 code
     * units](https://core.telegram.org/api/entities#entity-length)
     */
    @get:JsonProperty("length")
    @param:JsonProperty("length")
    public val length: Int,
    /**
     * *Optional*. For “text_link” only, URL that will be opened after user taps on the text
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String? = null,
    /**
     * *Optional*. For “text_mention” only, the mentioned user
     */
    @get:JsonProperty("user")
    @param:JsonProperty("user")
    public val user: User? = null,
    /**
     * *Optional*. For “pre” only, the programming language of the entity text
     */
    @get:JsonProperty("language")
    @param:JsonProperty("language")
    public val language: String? = null,
    /**
     * *Optional*. For “custom_emoji” only, unique identifier of the custom emoji. Use
     * [getCustomEmojiStickers](https://core.telegram.org/bots/api/#getcustomemojistickers) to get full
     * information about the sticker
     */
    @get:JsonProperty("custom_emoji_id")
    @param:JsonProperty("custom_emoji_id")
    public val customEmojiId: String? = null,
)
