package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object describes paid media. Currently, it can be one of
 *
 * * [PaidMediaLivePhoto](https://core.telegram.org/bots/api/#paidmedialivephoto)
 * * [PaidMediaPhoto](https://core.telegram.org/bots/api/#paidmediaphoto)
 * * [PaidMediaPreview](https://core.telegram.org/bots/api/#paidmediapreview)
 * * [PaidMediaVideo](https://core.telegram.org/bots/api/#paidmediavideo)
 *
 * @see [PaidMedia] (https://core.telegram.org/bots/api/#paidmedia)
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
    JsonSubTypes.Type(value = PaidMediaLivePhoto::class, name = "live_photo"),
    JsonSubTypes.Type(value = PaidMediaPhoto::class, name = "photo"),
    JsonSubTypes.Type(value = PaidMediaPreview::class, name = "preview"),
    JsonSubTypes.Type(value = PaidMediaVideo::class, name = "video"),
)
public sealed interface PaidMedia {
    public val type: String
}
