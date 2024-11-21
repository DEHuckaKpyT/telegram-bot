package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class SetUserEmojiStatus(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("emoji_status_custom_emoji_id")
    public val emojiStatusCustomEmojiId: String? = null,
    @get:JsonProperty("emoji_status_expiration_date")
    public val emojiStatusExpirationDate: Long? = null,
)
