package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 06.04.2024.
 *
 * @author Denis Matytsin
 */
public data class BusinessConnection(
    @param:JsonProperty("id") val id: String,
    @param:JsonProperty("user") val user: User,
    @param:JsonProperty("user_chat_id") val userChatId: Long,
    @param:JsonProperty("date") val date: Long,
    @param:JsonProperty("can_reply") val canReply: Boolean,
    @param:JsonProperty("is_enabled") val isEnabled: Boolean,
)

public data class BusinessMessagesDeleted(
    @param:JsonProperty("business_connection_id") val businessConnectionId: String,
    @param:JsonProperty("chat") val chat: Chat,
    @param:JsonProperty("message_ids") val messageIds: List<Long>,
)

public data class BusinessIntro(
    @param:JsonProperty("title") val title: String? = null,
    @param:JsonProperty("message") val message: String? = null,
    @param:JsonProperty("sticker") val sticker: Sticker? = null,
)

public data class BusinessLocation(
    @param:JsonProperty("address") val address: String,
    @param:JsonProperty("location") val location: Location? = null,
)

public data class BusinessOpeningHours(
    @param:JsonProperty("time_zone_name") val timeZoneName: String,
    @param:JsonProperty("opening_hours") val openingHours: List<BusinessOpeningHoursInterval>,
)

public data class BusinessOpeningHoursInterval(
    @param:JsonProperty("opening_minute") val openingMinute: Int,
    @param:JsonProperty("closing_minute") val closingMinute: Int,
)