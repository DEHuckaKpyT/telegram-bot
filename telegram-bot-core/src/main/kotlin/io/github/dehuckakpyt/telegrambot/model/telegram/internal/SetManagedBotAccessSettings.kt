package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class SetManagedBotAccessSettings(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("is_access_restricted")
    public val isAccessRestricted: Boolean,
    @get:JsonProperty("added_user_ids")
    public val addedUserIds: Iterable<Long>? = null,
)
