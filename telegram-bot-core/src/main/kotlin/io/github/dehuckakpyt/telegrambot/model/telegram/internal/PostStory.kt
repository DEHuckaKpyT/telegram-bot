package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InputStoryContent
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.StoryArea
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class PostStory(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("content")
    public val content: InputStoryContent,
    @get:JsonProperty("active_period")
    public val activePeriod: Int,
    @get:JsonProperty("caption")
    public val caption: String? = null,
    @get:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    @get:JsonProperty("caption_entities")
    public val captionEntities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("areas")
    public val areas: Iterable<StoryArea>? = null,
    @get:JsonProperty("post_to_chat_page")
    public val postToChatPage: Boolean? = null,
    @get:JsonProperty("protect_content")
    public val protectContent: Boolean? = null,
)
