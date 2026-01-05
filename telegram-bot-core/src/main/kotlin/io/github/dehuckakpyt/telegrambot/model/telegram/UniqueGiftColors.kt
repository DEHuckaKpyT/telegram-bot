package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int
import kotlin.String
import kotlin.collections.List

/**
 * This object contains information about the color scheme for a user's name, message replies and
 * link previews based on a unique gift.
 *
 * @see [UniqueGiftColors] (https://core.telegram.org/bots/api/#uniquegiftcolors)
 *
 * @author KScript
 *
 * @param modelCustomEmojiId Custom emoji identifier of the unique gift's model
 * @param symbolCustomEmojiId Custom emoji identifier of the unique gift's symbol
 * @param lightThemeMainColor Main color used in light themes; RGB format
 * @param lightThemeOtherColors List of 1-3 additional colors used in light themes; RGB format
 * @param darkThemeMainColor Main color used in dark themes; RGB format
 * @param darkThemeOtherColors List of 1-3 additional colors used in dark themes; RGB format
 */
public data class UniqueGiftColors(
    /**
     * Custom emoji identifier of the unique gift's model
     */
    @get:JsonProperty("model_custom_emoji_id")
    @param:JsonProperty("model_custom_emoji_id")
    public val modelCustomEmojiId: String,
    /**
     * Custom emoji identifier of the unique gift's symbol
     */
    @get:JsonProperty("symbol_custom_emoji_id")
    @param:JsonProperty("symbol_custom_emoji_id")
    public val symbolCustomEmojiId: String,
    /**
     * Main color used in light themes; RGB format
     */
    @get:JsonProperty("light_theme_main_color")
    @param:JsonProperty("light_theme_main_color")
    public val lightThemeMainColor: Int,
    /**
     * List of 1-3 additional colors used in light themes; RGB format
     */
    @get:JsonProperty("light_theme_other_colors")
    @param:JsonProperty("light_theme_other_colors")
    public val lightThemeOtherColors: List<Int>,
    /**
     * Main color used in dark themes; RGB format
     */
    @get:JsonProperty("dark_theme_main_color")
    @param:JsonProperty("dark_theme_main_color")
    public val darkThemeMainColor: Int,
    /**
     * List of 1-3 additional colors used in dark themes; RGB format
     */
    @get:JsonProperty("dark_theme_other_colors")
    @param:JsonProperty("dark_theme_other_colors")
    public val darkThemeOtherColors: List<Int>,
)
