package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * Describes the type of a clickable area on a story. Currently, it can be one of
 *
 * * [StoryAreaTypeLocation](https://core.telegram.org/bots/api/#storyareatypelocation)
 * *
 * [StoryAreaTypeSuggestedReaction](https://core.telegram.org/bots/api/#storyareatypesuggestedreaction)
 * * [StoryAreaTypeLink](https://core.telegram.org/bots/api/#storyareatypelink)
 * * [StoryAreaTypeWeather](https://core.telegram.org/bots/api/#storyareatypeweather)
 * * [StoryAreaTypeUniqueGift](https://core.telegram.org/bots/api/#storyareatypeuniquegift)
 *
 * @see [StoryAreaType] (https://core.telegram.org/bots/api/#storyareatype)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = StoryAreaTypeLocation::class, name = "location"),
    JsonSubTypes.Type(value = StoryAreaTypeSuggestedReaction::class, name = "suggested_reaction"),
    JsonSubTypes.Type(value = StoryAreaTypeLink::class, name = "link"),
    JsonSubTypes.Type(value = StoryAreaTypeWeather::class, name = "weather"),
    JsonSubTypes.Type(value = StoryAreaTypeUniqueGift::class, name = "unique_gift"),
)
public sealed interface StoryAreaType {
    public val type: String
}
