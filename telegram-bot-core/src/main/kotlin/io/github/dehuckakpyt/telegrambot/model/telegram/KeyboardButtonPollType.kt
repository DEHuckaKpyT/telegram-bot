package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object represents type of a poll, which is allowed to be created and sent when the
 * corresponding button is pressed.
 *
 * @see [KeyboardButtonPollType] (https://core.telegram.org/bots/api/#keyboardbuttonpolltype)
 *
 * @author KScript
 *
 * @param type *Optional*. If *quiz* is passed, the user will be allowed to create only polls in the
 * quiz mode. If *regular* is passed, only regular polls will be allowed. Otherwise, the user will be
 * allowed to create a poll of any type.
 */
public data class KeyboardButtonPollType(
    /**
     * *Optional*. If *quiz* is passed, the user will be allowed to create only polls in the quiz
     * mode. If *regular* is passed, only regular polls will be allowed. Otherwise, the user will be
     * allowed to create a poll of any type.
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String? = null,
)
