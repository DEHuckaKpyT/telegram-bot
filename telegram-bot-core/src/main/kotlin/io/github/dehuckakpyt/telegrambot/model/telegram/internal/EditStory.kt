package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InputStoryContent
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.StoryArea
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable

/**
 * @author KScript
 */
internal data class EditStory(
    @get:JsonProperty("business_connection_id")
    public val businessConnectionId: String,
    @get:JsonProperty("story_id")
    public val storyId: Long,
    @get:JsonProperty("content")
    public val content: InputStoryContent,
    @get:JsonProperty("caption")
    public val caption: String? = null,
    @get:JsonProperty("parse_mode")
    public val parseMode: String? = null,
    @get:JsonProperty("caption_entities")
    public val captionEntities: Iterable<MessageEntity>? = null,
    @get:JsonProperty("areas")
    public val areas: Iterable<StoryArea>? = null,
)
