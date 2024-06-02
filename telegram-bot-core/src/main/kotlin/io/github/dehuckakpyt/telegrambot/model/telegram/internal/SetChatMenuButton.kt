package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.telegram.MenuButton
import kotlin.Long

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class SetChatMenuButton(
    @get:JsonProperty("chat_id")
    public val chatId: Long? = null,
    @get:JsonProperty("menu_button")
    public val menuButton: MenuButton? = null,
)
