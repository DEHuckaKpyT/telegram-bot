package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * @author KScript
 */
internal data class RepostStory(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("from_chat_id")
    public val fromChatId: Long,
    @get:JsonProperty("from_story_id")
    public val fromStoryId: Long,
    @get:JsonProperty("active_period")
    public val activePeriod: Int,
    @get:JsonProperty("post_to_chat_page")
    public val postToChatPage: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
)
