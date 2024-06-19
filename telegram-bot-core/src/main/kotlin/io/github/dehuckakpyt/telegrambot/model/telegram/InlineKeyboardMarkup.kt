package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * This object represents an [inline
 * keyboard](https://core.telegram.org/bots/features#inline-keyboards) that appears right next to the
 * message it belongs to.
 *
 * @see [InlineKeyboardMarkup] (https://core.telegram.org/bots/api/#inlinekeyboardmarkup)
 *
 * @author KScript
 *
 * @param inlineKeyboard Array of button rows, each represented by an Array of
 * [InlineKeyboardButton](https://core.telegram.org/bots/api/#inlinekeyboardbutton) objects
 */
public data class InlineKeyboardMarkup(
    /**
     * Array of button rows, each represented by an Array of
     * [InlineKeyboardButton](https://core.telegram.org/bots/api/#inlinekeyboardbutton) objects
     */
    @get:JsonProperty("inline_keyboard")
    @param:JsonProperty("inline_keyboard")
    public val inlineKeyboard: List<List<InlineKeyboardButton>>,
) : ReplyMarkup
