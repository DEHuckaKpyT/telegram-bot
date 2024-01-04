package io.github.dehuckakpyt.telegrambot.api.ex

import io.github.dehuckakpyt.telegrambot.api.TelegramChatApi
import io.github.dehuckakpyt.telegrambot.model.type.ForumTopic


/**
 * Created on 19.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramChatExApi : TelegramChatApi {

    suspend fun createForumTopic(
        chatId: Long,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ): ForumTopic = createForumTopic(
        chatId = chatId.toString(),
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun editForumTopic(
        chatId: Long,
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ): Boolean = editForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId
    )

    suspend fun closeForumTopic(
        chatId: Long,
        messageThreadId: Long,
    ): Boolean = closeForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId
    )

    suspend fun reopenForumTopic(
        chatId: Long,
        messageThreadId: Long,
    ): Boolean = reopenForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId
    )

    suspend fun deleteForumTopic(
        chatId: Long,
        messageThreadId: Long,
    ): Boolean = deleteForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId
    )

    suspend fun unpinAllForumTopicMessages(
        chatId: Long,
        messageThreadId: Long,
    ): Boolean = unpinAllForumTopicMessages(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId
    )

    suspend fun editGeneralForumTopic(
        chatId: Long,
        name: String,
    ): Boolean = editGeneralForumTopic(
        chatId = chatId.toString(),
        name = name
    )

    suspend fun closeGeneralForumTopic(
        chatId: Long,
    ): Boolean = closeGeneralForumTopic(
        chatId = chatId.toString()
    )

    suspend fun reopenGeneralForumTopic(
        chatId: Long,
    ): Boolean = reopenGeneralForumTopic(
        chatId = chatId.toString()
    )

    suspend fun hideGeneralForumTopic(
        chatId: Long,
    ): Boolean = hideGeneralForumTopic(
        chatId = chatId.toString()
    )

    suspend fun unhideGeneralForumTopic(
        chatId: Long,
    ): Boolean = unhideGeneralForumTopic(
        chatId = chatId.toString()
    )

    suspend fun unpinAllGeneralForumTopicMessages(
        chatId: Long,
    ): Boolean = unpinAllGeneralForumTopicMessages(
        chatId = chatId.toString()
    )
}