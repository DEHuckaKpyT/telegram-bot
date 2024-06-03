package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * The background is taken directly from a built-in chat theme.
 *
 * @see [BackgroundTypeChatTheme] (https://core.telegram.org/bots/api/#backgroundtypechattheme)
 *
 * @author KScript
 *
 * @param type Type of the background, always “chat_theme”
 * @param themeName Name of the chat theme, which is usually an emoji
 */
public data class BackgroundTypeChatTheme(
    /**
     * Type of the background, always “chat_theme”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Name of the chat theme, which is usually an emoji
     */
    @get:JsonProperty("theme_name")
    @param:JsonProperty("theme_name")
    public val themeName: String,
) : BackgroundType
