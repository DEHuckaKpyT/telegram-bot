package io.github.dehuckakpyt.telegrambot.model.internal

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.ChatAdministratorRights
import io.github.dehuckakpyt.telegrambot.model.type.MenuButton


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
internal data class SetChatMenuButton(
    @get:JsonProperty("chat_id") val chatId: Long?,
    @get:JsonProperty("menu_button") val menuButton: MenuButton?,
)

internal data class GetChatMenuButton(
    @get:JsonProperty("chat_id") val chatId: Long?,
)

internal data class SetMyDefaultAdministratorRights(
    @get:JsonProperty("rights") val rights: ChatAdministratorRights?,
    @get:JsonProperty("for_channels") val forChannels: Boolean?,
)

internal data class GetMyDefaultAdministratorRights(
    @get:JsonProperty("for_channels") val forChannels: Boolean?,
)

internal data class CreateForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("name") val name: String,
    @get:JsonProperty("icon_color") val iconColor: Int?,
    @get:JsonProperty("icon_custom_emoji_id") val iconCustomEmojiId: String?,
)

internal data class EditForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long,
    @get:JsonProperty("name") val name: String?,
    @get:JsonProperty("icon_custom_emoji_id") val iconCustomEmojiId: String?,
)

internal data class CloseForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long,
)

internal data class ReopenForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long,
)

internal data class DeleteForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long,
)

internal data class UnpinAllForumTopicMessages(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("message_thread_id") val messageThreadId: Long,
)

internal data class EditGeneralForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
    @get:JsonProperty("name") val name: String,
)

internal data class CloseGeneralForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal data class ReopenGeneralForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal data class HideGeneralForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
)

internal data class UnhideGeneralForumTopic(
    @get:JsonProperty("chat_id") val chatId: String,
)