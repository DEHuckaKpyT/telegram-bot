package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class GiftPremiumSubscription(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("month_count")
    public val monthCount: Int,
    @get:JsonProperty("star_count")
    public val starCount: Int,
    @get:JsonProperty("text")
    public val text: String? = null,
    @get:JsonProperty("text_parse_mode")
    public val textParseMode: String? = null,
    @get:JsonProperty("text_entities")
    public val textEntities: Iterable<MessageEntity>? = null,
)
