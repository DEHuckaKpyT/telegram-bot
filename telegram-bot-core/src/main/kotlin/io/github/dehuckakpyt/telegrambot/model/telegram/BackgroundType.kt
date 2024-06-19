package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object describes the type of a background. Currently, it can be one of
 *
 * * [BackgroundTypeFill](https://core.telegram.org/bots/api/#backgroundtypefill)
 * * [BackgroundTypeWallpaper](https://core.telegram.org/bots/api/#backgroundtypewallpaper)
 * * [BackgroundTypePattern](https://core.telegram.org/bots/api/#backgroundtypepattern)
 * * [BackgroundTypeChatTheme](https://core.telegram.org/bots/api/#backgroundtypechattheme)
 *
 * @see [BackgroundType] (https://core.telegram.org/bots/api/#backgroundtype)
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
    JsonSubTypes.Type(value = BackgroundTypeFill::class, name = "fill"),
    JsonSubTypes.Type(value = BackgroundTypeWallpaper::class, name = "wallpaper"),
    JsonSubTypes.Type(value = BackgroundTypePattern::class, name = "pattern"),
    JsonSubTypes.Type(value = BackgroundTypeChatTheme::class, name = "chat_theme"),
)
public sealed interface BackgroundType {
    public val type: String
}
