package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.type.ChatAdministratorRights
import io.github.dehuckakpyt.telegrambot.model.type.ForumTopic
import io.github.dehuckakpyt.telegrambot.model.type.MenuButton
import io.github.dehuckakpyt.telegrambot.model.type.Sticker


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramChatApi {
    suspend fun setChatMenuButton(
        chatId: Long? = null,
        menuButton: MenuButton? = null
    ): Boolean

    suspend fun getChatMenuButton(chatId: Long? = null): MenuButton

    suspend fun setMyDefaultAdministratorRights(
        rights: ChatAdministratorRights? = null,
        forChannels: Boolean? = null,
    ): Boolean

    suspend fun getMyDefaultAdministratorRights(
        forChannels: Boolean? = null,
    ): ChatAdministratorRights

    suspend fun getForumTopicIconStickers(): List<Sticker>

    suspend fun createForumTopic(
        chatId: String,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ): ForumTopic

    suspend fun editForumTopic(
        chatId: String,
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ): Boolean

    suspend fun closeForumTopic(
        chatId: String,
        messageThreadId: Long,
    ): Boolean

    suspend fun reopenForumTopic(
        chatId: String,
        messageThreadId: Long,
    ): Boolean

    suspend fun deleteForumTopic(
        chatId: String,
        messageThreadId: Long,
    ): Boolean

    suspend fun unpinAllForumTopicMessages(
        chatId: String,
        messageThreadId: Long,
    ): Boolean

    suspend fun editGeneralForumTopic(
        chatId: String,
        name: String,
    ): Boolean

    suspend fun closeGeneralForumTopic(
        chatId: String,
    ): Boolean

    suspend fun reopenGeneralForumTopic(
        chatId: String,
    ): Boolean

    suspend fun hideGeneralForumTopic(
        chatId: String,
    ): Boolean

    suspend fun unhideGeneralForumTopic(
        chatId: String,
    ): Boolean

    suspend fun unpinAllGeneralForumTopicMessages(
        chatId: String,
    ): Boolean
}