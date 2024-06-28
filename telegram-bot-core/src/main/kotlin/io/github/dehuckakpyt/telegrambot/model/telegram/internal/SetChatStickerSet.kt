package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * @author KScript
 */
internal data class SetChatStickerSet(
    @get:JsonProperty("chat_id")
    public val chatId: String,
    @get:JsonProperty("sticker_set_name")
    public val stickerSetName: String,
)
