package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.InputProfilePhoto

/**
 * @author KScript
 */
internal data class SetMyProfilePhoto(
    @get:JsonProperty("photo")
    public val photo: InputProfilePhoto,
)
