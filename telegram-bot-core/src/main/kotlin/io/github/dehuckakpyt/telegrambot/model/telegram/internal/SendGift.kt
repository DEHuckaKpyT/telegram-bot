package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SendGift(
    @get:JsonProperty("gift_id")
    public val giftId: String,
    @get:JsonProperty("user_id")
    public val userId: Long? = null,
    @get:JsonProperty("chat_id")
    public val chatId: String? = null,
    @get:JsonProperty("pay_for_upgrade")
    public val payForUpgrade: Boolean? = null,
    @get:JsonProperty("text")
    public val text: String? = null,
    @get:JsonProperty("text_parse_mode")
    public val textParseMode: String? = null,
    @get:JsonProperty("text_entities")
    public val textEntities: Iterable<MessageEntity>? = null,
)
