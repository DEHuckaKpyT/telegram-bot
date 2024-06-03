package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.telegram.ChatFullInfo
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatInviteLink
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatMember
import io.github.dehuckakpyt.telegrambot.model.telegram.ChatPermissions
import io.github.dehuckakpyt.telegrambot.model.telegram.ForumTopic
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.InputMedia
import io.github.dehuckakpyt.telegrambot.model.telegram.InputPollOption
import io.github.dehuckakpyt.telegrambot.model.telegram.LabeledPrice
import io.github.dehuckakpyt.telegrambot.model.telegram.LinkPreviewOptions
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.Poll
import io.github.dehuckakpyt.telegrambot.model.telegram.ReactionType
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import io.github.dehuckakpyt.telegrambot.model.telegram.UserChatBoosts
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List

/**
 * Created on 03.06.2024.
 *
 * @author KScript
 */
public interface TelegramBotApiExt : TelegramBotApi {
    /**
     * Use this method to send text messages. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param text Text of the message to be sent, 1-4096 characters after entities parsing
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param parseMode Mode for parsing entities in the message text. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param entities A JSON-serialized list of special entities that appear in message text, which
     * can be specified instead of *parse_mode*
     * @param linkPreviewOptions Link preview generation options for the message
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendMessage(
        chatId: Long,
        text: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        parseMode: String? = null,
        entities: Iterable<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendMessage(
        chatId = chatId.toString(),
        text = text,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to forward messages of any kind. Service messages and messages with protected
     * content can't be forwarded. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded message from forwarding and
     * saving
     */
    public suspend fun forwardMessage(
        chatId: Long,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = forwardMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to forward messages of any kind. Service messages and messages with protected
     * content can't be forwarded. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded message from forwarding and
     * saving
     */
    public suspend fun forwardMessage(
        chatId: String,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = forwardMessage(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to forward messages of any kind. Service messages and messages with protected
     * content can't be forwarded. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded message from forwarding and
     * saving
     */
    public suspend fun forwardMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): Message = forwardMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to forward multiple messages of any kind. If some of the specified messages
     * can't be found or forwarded, they are skipped. Service messages and messages with protected
     * content can't be forwarded. Album grouping is kept for forwarded messages. On success, an array
     * of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent messages is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to forward. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded messages from forwarding and
     * saving
     */
    public suspend fun forwardMessages(
        chatId: Long,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to forward multiple messages of any kind. If some of the specified messages
     * can't be found or forwarded, they are skipped. Service messages and messages with protected
     * content can't be forwarded. Album grouping is kept for forwarded messages. On success, an array
     * of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent messages is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to forward. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded messages from forwarding and
     * saving
     */
    public suspend fun forwardMessages(
        chatId: String,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to forward multiple messages of any kind. If some of the specified messages
     * can't be found or forwarded, they are skipped. Service messages and messages with protected
     * content can't be forwarded. Album grouping is kept for forwarded messages. On success, an array
     * of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent messages is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to forward. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded messages from forwarding and
     * saving
     */
    public suspend fun forwardMessages(
        chatId: Long,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to copy messages of any kind. Service messages, giveaway messages, giveaway
     * winners messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessage](https://core.telegram.org/bots/api/#forwardmessage), but the copied message
     * doesn't have a link to the original message. Returns the
     * [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent message on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption New caption for media, 0-1024 characters after entities parsing. If not
     * specified, the original caption is kept
     * @param parseMode Mode for parsing entities in the new caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the new
     * caption, which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media. Ignored if a new caption isn't specified.
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun copyMessage(
        chatId: Long,
        fromChatId: String,
        messageId: Long,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): MessageId = copyMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageId = messageId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to copy messages of any kind. Service messages, giveaway messages, giveaway
     * winners messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessage](https://core.telegram.org/bots/api/#forwardmessage), but the copied message
     * doesn't have a link to the original message. Returns the
     * [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent message on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption New caption for media, 0-1024 characters after entities parsing. If not
     * specified, the original caption is kept
     * @param parseMode Mode for parsing entities in the new caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the new
     * caption, which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media. Ignored if a new caption isn't specified.
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun copyMessage(
        chatId: String,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): MessageId = copyMessage(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to copy messages of any kind. Service messages, giveaway messages, giveaway
     * winners messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessage](https://core.telegram.org/bots/api/#forwardmessage), but the copied message
     * doesn't have a link to the original message. Returns the
     * [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent message on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption New caption for media, 0-1024 characters after entities parsing. If not
     * specified, the original caption is kept
     * @param parseMode Mode for parsing entities in the new caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the new
     * caption, which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media. Ignored if a new caption isn't specified.
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun copyMessage(
        chatId: Long,
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): MessageId = copyMessage(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to copy messages of any kind. If some of the specified messages can't be
     * found or copied, they are skipped. Service messages, giveaway messages, giveaway winners
     * messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessages](https://core.telegram.org/bots/api/#forwardmessages), but the copied messages
     * don't have a link to the original message. Album grouping is kept for copied messages. On
     * success, an array of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent
     * messages is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to copy. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent messages from forwarding and saving
     * @param removeCaption Pass *True* to copy the messages without their captions
     */
    public suspend fun copyMessages(
        chatId: Long,
        fromChatId: String,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId,
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    /**
     * Use this method to copy messages of any kind. If some of the specified messages can't be
     * found or copied, they are skipped. Service messages, giveaway messages, giveaway winners
     * messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessages](https://core.telegram.org/bots/api/#forwardmessages), but the copied messages
     * don't have a link to the original message. Album grouping is kept for copied messages. On
     * success, an array of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent
     * messages is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to copy. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent messages from forwarding and saving
     * @param removeCaption Pass *True* to copy the messages without their captions
     */
    public suspend fun copyMessages(
        chatId: String,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        chatId = chatId,
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    /**
     * Use this method to copy messages of any kind. If some of the specified messages can't be
     * found or copied, they are skipped. Service messages, giveaway messages, giveaway winners
     * messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessages](https://core.telegram.org/bots/api/#forwardmessages), but the copied messages
     * don't have a link to the original message. Album grouping is kept for copied messages. On
     * success, an array of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent
     * messages is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to copy. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent messages from forwarding and saving
     * @param removeCaption Pass *True* to copy the messages without their captions
     */
    public suspend fun copyMessages(
        chatId: Long,
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        chatId = chatId.toString(),
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    /**
     * Use this method to send photos. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param photo Photo to send. Pass a file_id as String to send a photo that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a photo from
     * the Internet, or upload a new photo using multipart/form-data. The photo must be at most 10 MB
     * in size. The photo's width and height must not exceed 10000 in total. Width and height ratio
     * must be at most 20. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Photo caption (may also be used when resending photos by *file_id*), 0-1024
     * characters after entities parsing
     * @param parseMode Mode for parsing entities in the photo caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the photo needs to be covered with a spoiler animation
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendPhoto(
        chatId: Long,
        photo: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendPhoto(
        chatId = chatId.toString(),
        photo = photo,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send photos. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param photo Photo to send. Pass a file_id as String to send a photo that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a photo from
     * the Internet, or upload a new photo using multipart/form-data. The photo must be at most 10 MB
     * in size. The photo's width and height must not exceed 10000 in total. Width and height ratio
     * must be at most 20. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Photo caption (may also be used when resending photos by *file_id*), 0-1024
     * characters after entities parsing
     * @param parseMode Mode for parsing entities in the photo caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the photo needs to be covered with a spoiler animation
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendPhoto(
        chatId: String,
        photo: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendPhoto(
        chatId = chatId,
        photo = StringInput(photo),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send photos. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param photo Photo to send. Pass a file_id as String to send a photo that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a photo from
     * the Internet, or upload a new photo using multipart/form-data. The photo must be at most 10 MB
     * in size. The photo's width and height must not exceed 10000 in total. Width and height ratio
     * must be at most 20. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Photo caption (may also be used when resending photos by *file_id*), 0-1024
     * characters after entities parsing
     * @param parseMode Mode for parsing entities in the photo caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the photo needs to be covered with a spoiler animation
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendPhoto(
        chatId: Long,
        photo: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendPhoto(
        chatId = chatId.toString(),
        photo = StringInput(photo),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the
     * music player. Your audio must be in the .MP3 or .M4A format. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For sending voice messages, use the
     * [sendVoice](https://core.telegram.org/bots/api/#sendvoice) method instead.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param audio Audio file to send. Pass a file_id as String to send an audio file that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an audio
     * file from the Internet, or upload a new one using multipart/form-data. [More information on
     * Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Audio caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the audio caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param duration Duration of the audio in seconds
     * @param performer Performer
     * @param title Track name
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendAudio(
        chatId: Long,
        audio: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAudio(
        chatId = chatId.toString(),
        audio = audio,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the
     * music player. Your audio must be in the .MP3 or .M4A format. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For sending voice messages, use the
     * [sendVoice](https://core.telegram.org/bots/api/#sendvoice) method instead.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param audio Audio file to send. Pass a file_id as String to send an audio file that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an audio
     * file from the Internet, or upload a new one using multipart/form-data. [More information on
     * Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Audio caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the audio caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param duration Duration of the audio in seconds
     * @param performer Performer
     * @param title Track name
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendAudio(
        chatId: String,
        audio: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAudio(
        chatId = chatId,
        audio = StringInput(audio),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display them in the
     * music player. Your audio must be in the .MP3 or .M4A format. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * audio files of up to 50 MB in size, this limit may be changed in the future.
     *
     * For sending voice messages, use the
     * [sendVoice](https://core.telegram.org/bots/api/#sendvoice) method instead.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param audio Audio file to send. Pass a file_id as String to send an audio file that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an audio
     * file from the Internet, or upload a new one using multipart/form-data. [More information on
     * Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Audio caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the audio caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param duration Duration of the audio in seconds
     * @param performer Performer
     * @param title Track name
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendAudio(
        chatId: Long,
        audio: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAudio(
        chatId = chatId.toString(),
        audio = StringInput(audio),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send general files. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * files of any type of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param document File to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Document caption (may also be used when resending documents by *file_id*),
     * 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the document caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param disableContentTypeDetection Disables automatic server-side content type detection for
     * files uploaded using multipart/form-data
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendDocument(
        chatId: Long,
        document: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendDocument(
        chatId = chatId.toString(),
        document = document,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send general files. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * files of any type of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param document File to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Document caption (may also be used when resending documents by *file_id*),
     * 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the document caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param disableContentTypeDetection Disables automatic server-side content type detection for
     * files uploaded using multipart/form-data
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendDocument(
        chatId: String,
        document: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendDocument(
        chatId = chatId,
        document = StringInput(document),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send general files. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * files of any type of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param document File to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Document caption (may also be used when resending documents by *file_id*),
     * 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the document caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param disableContentTypeDetection Disables automatic server-side content type detection for
     * files uploaded using multipart/form-data
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendDocument(
        chatId: Long,
        document: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendDocument(
        chatId = chatId.toString(),
        document = StringInput(document),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send video files, Telegram clients support MPEG4 videos (other formats may
     * be sent as [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * video files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param video Video to send. Pass a file_id as String to send a video that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a video from
     * the Internet, or upload a new video using multipart/form-data. [More information on Sending
     * Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent video in seconds
     * @param width Video width
     * @param height Video height
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Video caption (may also be used when resending videos by *file_id*), 0-1024
     * characters after entities parsing
     * @param parseMode Mode for parsing entities in the video caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the video needs to be covered with a spoiler animation
     * @param supportsStreaming Pass *True* if the uploaded video is suitable for streaming
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVideo(
        chatId: Long,
        video: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideo(
        chatId = chatId.toString(),
        video = video,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send video files, Telegram clients support MPEG4 videos (other formats may
     * be sent as [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * video files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param video Video to send. Pass a file_id as String to send a video that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a video from
     * the Internet, or upload a new video using multipart/form-data. [More information on Sending
     * Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent video in seconds
     * @param width Video width
     * @param height Video height
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Video caption (may also be used when resending videos by *file_id*), 0-1024
     * characters after entities parsing
     * @param parseMode Mode for parsing entities in the video caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the video needs to be covered with a spoiler animation
     * @param supportsStreaming Pass *True* if the uploaded video is suitable for streaming
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVideo(
        chatId: String,
        video: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideo(
        chatId = chatId,
        video = StringInput(video),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send video files, Telegram clients support MPEG4 videos (other formats may
     * be sent as [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * video files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param video Video to send. Pass a file_id as String to send a video that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a video from
     * the Internet, or upload a new video using multipart/form-data. [More information on Sending
     * Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent video in seconds
     * @param width Video width
     * @param height Video height
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Video caption (may also be used when resending videos by *file_id*), 0-1024
     * characters after entities parsing
     * @param parseMode Mode for parsing entities in the video caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the video needs to be covered with a spoiler animation
     * @param supportsStreaming Pass *True* if the uploaded video is suitable for streaming
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVideo(
        chatId: Long,
        video: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideo(
        chatId = chatId.toString(),
        video = StringInput(video),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can
     * currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an
     * animation from the Internet, or upload a new animation using multipart/form-data. [More
     * information on Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent animation in seconds
     * @param width Animation width
     * @param height Animation height
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Animation caption (may also be used when resending animation by *file_id*),
     * 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the animation caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the animation needs to be covered with a spoiler animation
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendAnimation(
        chatId: Long,
        animation: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAnimation(
        chatId = chatId.toString(),
        animation = animation,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can
     * currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an
     * animation from the Internet, or upload a new animation using multipart/form-data. [More
     * information on Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent animation in seconds
     * @param width Animation width
     * @param height Animation height
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Animation caption (may also be used when resending animation by *file_id*),
     * 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the animation caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the animation needs to be covered with a spoiler animation
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendAnimation(
        chatId: String,
        animation: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAnimation(
        chatId = chatId,
        animation = StringInput(animation),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can
     * currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an
     * animation from the Internet, or upload a new animation using multipart/form-data. [More
     * information on Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent animation in seconds
     * @param width Animation width
     * @param height Animation height
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param caption Animation caption (may also be used when resending animation by *file_id*),
     * 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the animation caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media
     * @param hasSpoiler Pass *True* if the animation needs to be covered with a spoiler animation
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendAnimation(
        chatId: Long,
        animation: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: ContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAnimation(
        chatId = chatId.toString(),
        animation = StringInput(animation),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a
     * playable voice message. For this to work, your audio must be in an .OGG file encoded with OPUS,
     * or in .MP3 format, or in .M4A format (other formats may be sent as
     * [Audio](https://core.telegram.org/bots/api/#audio) or
     * [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * voice messages of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param voice Audio file to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Voice message caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the voice message caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param duration Duration of the voice message in seconds
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVoice(
        chatId: Long,
        voice: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVoice(
        chatId = chatId.toString(),
        voice = voice,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a
     * playable voice message. For this to work, your audio must be in an .OGG file encoded with OPUS,
     * or in .MP3 format, or in .M4A format (other formats may be sent as
     * [Audio](https://core.telegram.org/bots/api/#audio) or
     * [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * voice messages of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param voice Audio file to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Voice message caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the voice message caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param duration Duration of the voice message in seconds
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVoice(
        chatId: String,
        voice: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVoice(
        chatId = chatId,
        voice = StringInput(voice),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send audio files, if you want Telegram clients to display the file as a
     * playable voice message. For this to work, your audio must be in an .OGG file encoded with OPUS,
     * or in .MP3 format, or in .M4A format (other formats may be sent as
     * [Audio](https://core.telegram.org/bots/api/#audio) or
     * [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * voice messages of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param voice Audio file to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param caption Voice message caption, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the voice message caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param duration Duration of the voice message in seconds
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVoice(
        chatId: Long,
        voice: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVoice(
        chatId = chatId.toString(),
        voice = StringInput(voice),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * As of [v.4.0](https://telegram.org/blog/video-messages-and-telescope), Telegram clients
     * support rounded square MPEG4 videos of up to 1 minute long. Use this method to send video
     * messages. On success, the sent [Message](https://core.telegram.org/bots/api/#message) is
     * returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param videoNote Video note to send. Pass a file_id as String to send a video note that
     * exists on the Telegram servers (recommended) or upload a new video using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Sending
     * video notes by a URL is currently unsupported
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent video in seconds
     * @param length Video width and height, i.e. diameter of the video message
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVideoNote(
        chatId: Long,
        videoNote: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        length: Int? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideoNote(
        chatId = chatId.toString(),
        videoNote = videoNote,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * As of [v.4.0](https://telegram.org/blog/video-messages-and-telescope), Telegram clients
     * support rounded square MPEG4 videos of up to 1 minute long. Use this method to send video
     * messages. On success, the sent [Message](https://core.telegram.org/bots/api/#message) is
     * returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param videoNote Video note to send. Pass a file_id as String to send a video note that
     * exists on the Telegram servers (recommended) or upload a new video using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Sending
     * video notes by a URL is currently unsupported
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent video in seconds
     * @param length Video width and height, i.e. diameter of the video message
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVideoNote(
        chatId: String,
        videoNote: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        length: Int? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideoNote(
        chatId = chatId,
        videoNote = StringInput(videoNote),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * As of [v.4.0](https://telegram.org/blog/video-messages-and-telescope), Telegram clients
     * support rounded square MPEG4 videos of up to 1 minute long. Use this method to send video
     * messages. On success, the sent [Message](https://core.telegram.org/bots/api/#message) is
     * returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param videoNote Video note to send. Pass a file_id as String to send a video note that
     * exists on the Telegram servers (recommended) or upload a new video using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Sending
     * video notes by a URL is currently unsupported
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param duration Duration of sent video in seconds
     * @param length Video width and height, i.e. diameter of the video message
     * @param thumbnail Thumbnail of the file sent; can be ignored if thumbnail generation for the
     * file is supported server-side. The thumbnail should be in JPEG format and less than 200 kB in
     * size. A thumbnail's width and height should not exceed 320. Ignored if the file is not uploaded
     * using multipart/form-data. Thumbnails can't be reused and can be only uploaded as a new file, so
     * you can pass “attach://\<file_attach_name\>” if the thumbnail was uploaded using
     * multipart/form-data under \<file_attach_name\>. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVideoNote(
        chatId: Long,
        videoNote: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        duration: Int? = null,
        length: Int? = null,
        thumbnail: ContentInput? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideoNote(
        chatId = chatId.toString(),
        videoNote = StringInput(videoNote),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send a group of photos, videos, documents or audios as an album. Documents
     * and audio files can be only grouped in an album with messages of the same type. On success, an
     * array of [Messages](https://core.telegram.org/bots/api/#message) that were sent is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param media A JSON-serialized array describing messages to be sent, must include 2-10 items
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param disableNotification Sends messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent messages from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     */
    public suspend fun sendMediaGroup(
        chatId: Long,
        media: Iterable<InputMedia>,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
    ): List<Message> = sendMediaGroup(
        chatId = chatId.toString(),
        media = media,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
    )

    /**
     * Use this method to send point on the map. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param latitude Latitude of the location
     * @param longitude Longitude of the location
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param horizontalAccuracy The radius of uncertainty for the location, measured in meters;
     * 0-1500
     * @param livePeriod Period in seconds during which the location will be updated (see [Live
     * Locations](https://telegram.org/blog/live-locations), should be between 60 and 86400, or
     * 0x7FFFFFFF for live locations that can be edited indefinitely.
     * @param heading For live locations, a direction in which the user is moving, in degrees. Must
     * be between 1 and 360 if specified.
     * @param proximityAlertRadius For live locations, a maximum distance for proximity alerts about
     * approaching another chat member, in meters. Must be between 1 and 100000 if specified.
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendLocation(
        chatId: Long,
        latitude: Double,
        longitude: Double,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        horizontalAccuracy: Double? = null,
        livePeriod: Int? = null,
        heading: Int? = null,
        proximityAlertRadius: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendLocation(
        chatId = chatId.toString(),
        latitude = latitude,
        longitude = longitude,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        horizontalAccuracy = horizontalAccuracy,
        livePeriod = livePeriod,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send information about a venue. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param latitude Latitude of the venue
     * @param longitude Longitude of the venue
     * @param title Name of the venue
     * @param address Address of the venue
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param foursquareId Foursquare identifier of the venue
     * @param foursquareType Foursquare type of the venue, if known. (For example,
     * “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     * @param googlePlaceId Google Places identifier of the venue
     * @param googlePlaceType Google Places type of the venue. (See [supported
     * types](https://developers.google.com/places/web-service/supported_types).)
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendVenue(
        chatId: Long,
        latitude: Double,
        longitude: Double,
        title: String,
        address: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        foursquareId: String? = null,
        foursquareType: String? = null,
        googlePlaceId: String? = null,
        googlePlaceType: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVenue(
        chatId = chatId.toString(),
        latitude = latitude,
        longitude = longitude,
        title = title,
        address = address,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        foursquareId = foursquareId,
        foursquareType = foursquareType,
        googlePlaceId = googlePlaceId,
        googlePlaceType = googlePlaceType,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send phone contacts. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param phoneNumber Contact's phone number
     * @param firstName Contact's first name
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param lastName Contact's last name
     * @param vcard Additional data about the contact in the form of a
     * [vCard](https://en.wikipedia.org/wiki/VCard), 0-2048 bytes
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendContact(
        chatId: Long,
        phoneNumber: String,
        firstName: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        lastName: String? = null,
        vcard: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendContact(
        chatId = chatId.toString(),
        phoneNumber = phoneNumber,
        firstName = firstName,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        lastName = lastName,
        vcard = vcard,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send a native poll. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param question Poll question, 1-300 characters
     * @param options A JSON-serialized list of 2-10 answer options
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param questionParseMode Mode for parsing entities in the question. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details. Currently,
     * only custom emoji entities are allowed
     * @param questionEntities A JSON-serialized list of special entities that appear in the poll
     * question. It can be specified instead of *question_parse_mode*
     * @param isAnonymous *True*, if the poll needs to be anonymous, defaults to *True*
     * @param type Poll type, “quiz” or “regular”, defaults to “regular”
     * @param allowsMultipleAnswers *True*, if the poll allows multiple answers, ignored for polls
     * in quiz mode, defaults to *False*
     * @param correctOptionId 0-based identifier of the correct answer option, required for polls in
     * quiz mode
     * @param explanation Text that is shown when a user chooses an incorrect answer or taps on the
     * lamp icon in a quiz-style poll, 0-200 characters with at most 2 line feeds after entities
     * parsing
     * @param explanationParseMode Mode for parsing entities in the explanation. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param explanationEntities A JSON-serialized list of special entities that appear in the poll
     * explanation. It can be specified instead of *explanation_parse_mode*
     * @param openPeriod Amount of time in seconds the poll will be active after creation, 5-600.
     * Can't be used together with *close_date*.
     * @param closeDate Point in time (Unix timestamp) when the poll will be automatically closed.
     * Must be at least 5 and no more than 600 seconds in the future. Can't be used together with
     * *open_period*.
     * @param isClosed Pass *True* if the poll needs to be immediately closed. This can be useful
     * for poll preview.
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendPoll(
        chatId: Long,
        question: String,
        options: Iterable<InputPollOption>,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        questionParseMode: String? = null,
        questionEntities: Iterable<MessageEntity>? = null,
        isAnonymous: Boolean? = null,
        type: String? = null,
        allowsMultipleAnswers: Boolean? = null,
        correctOptionId: Long? = null,
        explanation: String? = null,
        explanationParseMode: String? = null,
        explanationEntities: Iterable<MessageEntity>? = null,
        openPeriod: Int? = null,
        closeDate: Long? = null,
        isClosed: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendPoll(
        chatId = chatId.toString(),
        question = question,
        options = options,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        questionParseMode = questionParseMode,
        questionEntities = questionEntities,
        isAnonymous = isAnonymous,
        type = type,
        allowsMultipleAnswers = allowsMultipleAnswers,
        correctOptionId = correctOptionId,
        explanation = explanation,
        explanationParseMode = explanationParseMode,
        explanationEntities = explanationEntities,
        openPeriod = openPeriod,
        closeDate = closeDate,
        isClosed = isClosed,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send an animated emoji that will display a random value. On success, the
     * sent [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param emoji Emoji on which the dice throw animation is based. Currently, must be one of
     * “🎲”, “🎯”, “🏀”, “⚽”, “🎳”, or “🎰”. Dice can have values 1-6 for “🎲”, “🎯” and “🎳”, values
     * 1-5 for “🏀” and “⚽”, and values 1-64 for “🎰”. Defaults to “🎲”
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendDice(
        chatId: Long,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendDice(
        chatId = chatId.toString(),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method when you need to tell the user that something is happening on the bot's side.
     * The status is set for 5 seconds or less (when a message arrives from your bot, Telegram clients
     * clear its typing status). Returns *True* on success.
     *
     * Example: The [ImageBot](https://t.me/imagebot) needs some time to process a request and
     * upload the image. Instead of sending a text message along the lines of “Retrieving image, please
     * wait…”, the bot may use [sendChatAction](https://core.telegram.org/bots/api/#sendchataction)
     * with *action* = *upload_photo*. The user will see a “sending photo” status for the bot.
     *
     * We only recommend using this method when a response from the bot will take a **noticeable**
     * amount of time to arrive.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param action Type of action to broadcast. Choose one, depending on what the user is about to
     * receive: *typing* for [text messages](https://core.telegram.org/bots/api/#sendmessage),
     * *upload_photo* for [photos](https://core.telegram.org/bots/api/#sendphoto), *record_video* or
     * *upload_video* for [videos](https://core.telegram.org/bots/api/#sendvideo), *record_voice* or
     * *upload_voice* for [voice notes](https://core.telegram.org/bots/api/#sendvoice),
     * *upload_document* for [general files](https://core.telegram.org/bots/api/#senddocument),
     * *choose_sticker* for [stickers](https://core.telegram.org/bots/api/#sendsticker),
     * *find_location* for [location data](https://core.telegram.org/bots/api/#sendlocation),
     * *record_video_note* or *upload_video_note* for [video
     * notes](https://core.telegram.org/bots/api/#sendvideonote).
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the action will be sent
     * @param messageThreadId Unique identifier for the target message thread; for supergroups only
     */
    public suspend fun sendChatAction(
        chatId: Long,
        action: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
    ): Boolean = sendChatAction(
        chatId = chatId.toString(),
        action = action,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
    )

    /**
     * Use this method to change the chosen reactions on a message. Service messages can't be
     * reacted to. Automatically forwarded messages from a channel to its discussion group have the
     * same available reactions as messages in the channel. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param messageId Identifier of the target message. If the message belongs to a media group,
     * the reaction is set to the first non-deleted message in the group instead.
     * @param reaction A JSON-serialized list of reaction types to set on the message. Currently, as
     * non-premium users, bots can set up to one reaction per message. A custom emoji reaction can be
     * used if it is either already present on the message or explicitly allowed by chat
     * administrators.
     * @param isBig Pass *True* to set the reaction with a big animation
     */
    public suspend fun setMessageReaction(
        chatId: Long,
        messageId: Long,
        reaction: Iterable<ReactionType>? = null,
        isBig: Boolean? = null,
    ): Boolean = setMessageReaction(
        chatId = chatId.toString(),
        messageId = messageId,
        reaction = reaction,
        isBig = isBig,
    )

    /**
     * Use this method to ban a user in a group, a supergroup or a channel. In the case of
     * supergroups and channels, the user will not be able to return to the chat on their own using
     * invite links, etc., unless [unbanned](https://core.telegram.org/bots/api/#unbanchatmember)
     * first. The bot must be an administrator in the chat for this to work and must have the
     * appropriate administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or
     * channel (in the format `@channelusername`)
     * @param userId Unique identifier of the target user
     * @param untilDate Date when the user will be unbanned; Unix time. If user is banned for more
     * than 366 days or less than 30 seconds from the current time they are considered to be banned
     * forever. Applied for supergroups and channels only.
     * @param revokeMessages Pass *True* to delete all messages from the chat for the user that is
     * being removed. If *False*, the user will be able to see messages in the group that were sent
     * before the user was removed. Always *True* for supergroups and channels.
     */
    public suspend fun banChatMember(
        chatId: Long,
        userId: Long,
        untilDate: Long? = null,
        revokeMessages: Boolean? = null,
    ): Boolean = banChatMember(
        chatId = chatId.toString(),
        userId = userId,
        untilDate = untilDate,
        revokeMessages = revokeMessages,
    )

    /**
     * Use this method to unban a previously banned user in a supergroup or channel. The user will
     * **not** return to the group or channel automatically, but will be able to join via link, etc.
     * The bot must be an administrator for this to work. By default, this method guarantees that after
     * the call the user is not a member of the chat, but will be able to join it. So if the user is a
     * member of the chat they will also be **removed** from the chat. If you don't want this, use the
     * parameter *only_if_banned*. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target group or username of the target supergroup or
     * channel (in the format `@channelusername`)
     * @param userId Unique identifier of the target user
     * @param onlyIfBanned Do nothing if the user is not banned
     */
    public suspend fun unbanChatMember(
        chatId: Long,
        userId: Long,
        onlyIfBanned: Boolean? = null,
    ): Boolean = unbanChatMember(
        chatId = chatId.toString(),
        userId = userId,
        onlyIfBanned = onlyIfBanned,
    )

    /**
     * Use this method to restrict a user in a supergroup. The bot must be an administrator in the
     * supergroup for this to work and must have the appropriate administrator rights. Pass *True* for
     * all permissions to lift restrictions from a user. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param userId Unique identifier of the target user
     * @param permissions A JSON-serialized object for new user permissions
     * @param useIndependentChatPermissions Pass *True* if chat permissions are set independently.
     * Otherwise, the *can_send_other_messages* and *can_add_web_page_previews* permissions will imply
     * the *can_send_messages*, *can_send_audios*, *can_send_documents*, *can_send_photos*,
     * *can_send_videos*, *can_send_video_notes*, and *can_send_voice_notes* permissions; the
     * *can_send_polls* permission will imply the *can_send_messages* permission.
     * @param untilDate Date when restrictions will be lifted for the user; Unix time. If user is
     * restricted for more than 366 days or less than 30 seconds from the current time, they are
     * considered to be restricted forever
     */
    public suspend fun restrictChatMember(
        chatId: Long,
        userId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
        untilDate: Long? = null,
    ): Boolean = restrictChatMember(
        chatId = chatId.toString(),
        userId = userId,
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
        untilDate = untilDate,
    )

    /**
     * Use this method to promote or demote a user in a supergroup or a channel. The bot must be an
     * administrator in the chat for this to work and must have the appropriate administrator rights.
     * Pass *False* for all boolean parameters to demote a user. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param userId Unique identifier of the target user
     * @param isAnonymous Pass *True* if the administrator's presence in the chat is hidden
     * @param canManageChat Pass *True* if the administrator can access the chat event log, get
     * boost list, see hidden supergroup and channel members, report spam messages and ignore slow
     * mode. Implied by any other administrator privilege.
     * @param canDeleteMessages Pass *True* if the administrator can delete messages of other users
     * @param canManageVideoChats Pass *True* if the administrator can manage video chats
     * @param canRestrictMembers Pass *True* if the administrator can restrict, ban or unban chat
     * members, or access supergroup statistics
     * @param canPromoteMembers Pass *True* if the administrator can add new administrators with a
     * subset of their own privileges or demote administrators that they have promoted, directly or
     * indirectly (promoted by administrators that were appointed by him)
     * @param canChangeInfo Pass *True* if the administrator can change chat title, photo and other
     * settings
     * @param canInviteUsers Pass *True* if the administrator can invite new users to the chat
     * @param canPostStories Pass *True* if the administrator can post stories to the chat
     * @param canEditStories Pass *True* if the administrator can edit stories posted by other
     * users, post stories to the chat page, pin chat stories, and access the chat's story archive
     * @param canDeleteStories Pass *True* if the administrator can delete stories posted by other
     * users
     * @param canPostMessages Pass *True* if the administrator can post messages in the channel, or
     * access channel statistics; for channels only
     * @param canEditMessages Pass *True* if the administrator can edit messages of other users and
     * can pin messages; for channels only
     * @param canPinMessages Pass *True* if the administrator can pin messages; for supergroups only
     * @param canManageTopics Pass *True* if the user is allowed to create, rename, close, and
     * reopen forum topics; for supergroups only
     */
    public suspend fun promoteChatMember(
        chatId: Long,
        userId: Long,
        isAnonymous: Boolean? = null,
        canManageChat: Boolean? = null,
        canDeleteMessages: Boolean? = null,
        canManageVideoChats: Boolean? = null,
        canRestrictMembers: Boolean? = null,
        canPromoteMembers: Boolean? = null,
        canChangeInfo: Boolean? = null,
        canInviteUsers: Boolean? = null,
        canPostStories: Boolean? = null,
        canEditStories: Boolean? = null,
        canDeleteStories: Boolean? = null,
        canPostMessages: Boolean? = null,
        canEditMessages: Boolean? = null,
        canPinMessages: Boolean? = null,
        canManageTopics: Boolean? = null,
    ): Boolean = promoteChatMember(
        chatId = chatId.toString(),
        userId = userId,
        isAnonymous = isAnonymous,
        canManageChat = canManageChat,
        canDeleteMessages = canDeleteMessages,
        canManageVideoChats = canManageVideoChats,
        canRestrictMembers = canRestrictMembers,
        canPromoteMembers = canPromoteMembers,
        canChangeInfo = canChangeInfo,
        canInviteUsers = canInviteUsers,
        canPostStories = canPostStories,
        canEditStories = canEditStories,
        canDeleteStories = canDeleteStories,
        canPostMessages = canPostMessages,
        canEditMessages = canEditMessages,
        canPinMessages = canPinMessages,
        canManageTopics = canManageTopics,
    )

    /**
     * Use this method to set a custom title for an administrator in a supergroup promoted by the
     * bot. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param userId Unique identifier of the target user
     * @param customTitle New custom title for the administrator; 0-16 characters, emoji are not
     * allowed
     */
    public suspend fun setChatAdministratorCustomTitle(
        chatId: Long,
        userId: Long,
        customTitle: String,
    ): Boolean = setChatAdministratorCustomTitle(
        chatId = chatId.toString(),
        userId = userId,
        customTitle = customTitle,
    )

    /**
     * Use this method to ban a channel chat in a supergroup or a channel. Until the chat is
     * [unbanned](https://core.telegram.org/bots/api/#unbanchatsenderchat), the owner of the banned
     * chat won't be able to send messages on behalf of **any of their channels**. The bot must be an
     * administrator in the supergroup or channel for this to work and must have the appropriate
     * administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param senderChatId Unique identifier of the target sender chat
     */
    public suspend fun banChatSenderChat(chatId: Long, senderChatId: Long): Boolean =
            banChatSenderChat(
        chatId = chatId.toString(),
        senderChatId = senderChatId,
    )

    /**
     * Use this method to unban a previously banned channel chat in a supergroup or channel. The bot
     * must be an administrator for this to work and must have the appropriate administrator rights.
     * Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param senderChatId Unique identifier of the target sender chat
     */
    public suspend fun unbanChatSenderChat(chatId: Long, senderChatId: Long): Boolean =
            unbanChatSenderChat(
        chatId = chatId.toString(),
        senderChatId = senderChatId,
    )

    /**
     * Use this method to set default chat permissions for all members. The bot must be an
     * administrator in the group or a supergroup for this to work and must have the
     * *can_restrict_members* administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param permissions A JSON-serialized object for new default chat permissions
     * @param useIndependentChatPermissions Pass *True* if chat permissions are set independently.
     * Otherwise, the *can_send_other_messages* and *can_add_web_page_previews* permissions will imply
     * the *can_send_messages*, *can_send_audios*, *can_send_documents*, *can_send_photos*,
     * *can_send_videos*, *can_send_video_notes*, and *can_send_voice_notes* permissions; the
     * *can_send_polls* permission will imply the *can_send_messages* permission.
     */
    public suspend fun setChatPermissions(
        chatId: Long,
        permissions: ChatPermissions,
        useIndependentChatPermissions: Boolean? = null,
    ): Boolean = setChatPermissions(
        chatId = chatId.toString(),
        permissions = permissions,
        useIndependentChatPermissions = useIndependentChatPermissions,
    )

    /**
     * Use this method to generate a new primary invite link for a chat; any previously generated
     * primary link is revoked. The bot must be an administrator in the chat for this to work and must
     * have the appropriate administrator rights. Returns the new invite link as *String* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     */
    public suspend fun exportChatInviteLink(chatId: Long): String = exportChatInviteLink(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to create an additional invite link for a chat. The bot must be an
     * administrator in the chat for this to work and must have the appropriate administrator rights.
     * The link can be revoked using the method
     * [revokeChatInviteLink](https://core.telegram.org/bots/api/#revokechatinvitelink). Returns the
     * new invite link as [ChatInviteLink](https://core.telegram.org/bots/api/#chatinvitelink) object.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param name Invite link name; 0-32 characters
     * @param expireDate Point in time (Unix timestamp) when the link will expire
     * @param memberLimit The maximum number of users that can be members of the chat simultaneously
     * after joining the chat via this invite link; 1-99999
     * @param createsJoinRequest *True*, if users joining the chat via the link need to be approved
     * by chat administrators. If *True*, *member_limit* can't be specified
     */
    public suspend fun createChatInviteLink(
        chatId: Long,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Int? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = createChatInviteLink(
        chatId = chatId.toString(),
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest,
    )

    /**
     * Use this method to edit a non-primary invite link created by the bot. The bot must be an
     * administrator in the chat for this to work and must have the appropriate administrator rights.
     * Returns the edited invite link as a
     * [ChatInviteLink](https://core.telegram.org/bots/api/#chatinvitelink) object.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param inviteLink The invite link to edit
     * @param name Invite link name; 0-32 characters
     * @param expireDate Point in time (Unix timestamp) when the link will expire
     * @param memberLimit The maximum number of users that can be members of the chat simultaneously
     * after joining the chat via this invite link; 1-99999
     * @param createsJoinRequest *True*, if users joining the chat via the link need to be approved
     * by chat administrators. If *True*, *member_limit* can't be specified
     */
    public suspend fun editChatInviteLink(
        chatId: Long,
        inviteLink: String,
        name: String? = null,
        expireDate: Long? = null,
        memberLimit: Int? = null,
        createsJoinRequest: Boolean? = null,
    ): ChatInviteLink = editChatInviteLink(
        chatId = chatId.toString(),
        inviteLink = inviteLink,
        name = name,
        expireDate = expireDate,
        memberLimit = memberLimit,
        createsJoinRequest = createsJoinRequest,
    )

    /**
     * Use this method to revoke an invite link created by the bot. If the primary link is revoked,
     * a new link is automatically generated. The bot must be an administrator in the chat for this to
     * work and must have the appropriate administrator rights. Returns the revoked invite link as
     * [ChatInviteLink](https://core.telegram.org/bots/api/#chatinvitelink) object.
     *
     * @param chatId Unique identifier of the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param inviteLink The invite link to revoke
     */
    public suspend fun revokeChatInviteLink(chatId: Long, inviteLink: String): ChatInviteLink =
            revokeChatInviteLink(
        chatId = chatId.toString(),
        inviteLink = inviteLink,
    )

    /**
     * Use this method to approve a chat join request. The bot must be an administrator in the chat
     * for this to work and must have the *can_invite_users* administrator right. Returns *True* on
     * success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param userId Unique identifier of the target user
     */
    public suspend fun approveChatJoinRequest(chatId: Long, userId: Long): Boolean =
            approveChatJoinRequest(
        chatId = chatId.toString(),
        userId = userId,
    )

    /**
     * Use this method to decline a chat join request. The bot must be an administrator in the chat
     * for this to work and must have the *can_invite_users* administrator right. Returns *True* on
     * success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param userId Unique identifier of the target user
     */
    public suspend fun declineChatJoinRequest(chatId: Long, userId: Long): Boolean =
            declineChatJoinRequest(
        chatId = chatId.toString(),
        userId = userId,
    )

    /**
     * Use this method to set a new profile photo for the chat. Photos can't be changed for private
     * chats. The bot must be an administrator in the chat for this to work and must have the
     * appropriate administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param photo New chat photo, uploaded using multipart/form-data
     */
    public suspend fun setChatPhoto(chatId: Long, photo: Input): Boolean = setChatPhoto(
        chatId = chatId.toString(),
        photo = photo,
    )

    /**
     * Use this method to delete a chat photo. Photos can't be changed for private chats. The bot
     * must be an administrator in the chat for this to work and must have the appropriate
     * administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     */
    public suspend fun deleteChatPhoto(chatId: Long): Boolean = deleteChatPhoto(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to change the title of a chat. Titles can't be changed for private chats. The
     * bot must be an administrator in the chat for this to work and must have the appropriate
     * administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param title New chat title, 1-128 characters
     */
    public suspend fun setChatTitle(chatId: Long, title: String): Boolean = setChatTitle(
        chatId = chatId.toString(),
        title = title,
    )

    /**
     * Use this method to change the description of a group, a supergroup or a channel. The bot must
     * be an administrator in the chat for this to work and must have the appropriate administrator
     * rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param description New chat description, 0-255 characters
     */
    public suspend fun setChatDescription(chatId: Long, description: String? = null): Boolean =
            setChatDescription(
        chatId = chatId.toString(),
        description = description,
    )

    /**
     * Use this method to add a message to the list of pinned messages in a chat. If the chat is not
     * a private chat, the bot must be an administrator in the chat for this to work and must have the
     * 'can_pin_messages' administrator right in a supergroup or 'can_edit_messages' administrator
     * right in a channel. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param messageId Identifier of a message to pin
     * @param disableNotification Pass *True* if it is not necessary to send a notification to all
     * chat members about the new pinned message. Notifications are always disabled in channels and
     * private chats.
     */
    public suspend fun pinChatMessage(
        chatId: Long,
        messageId: Long,
        disableNotification: Boolean? = null,
    ): Boolean = pinChatMessage(
        chatId = chatId.toString(),
        messageId = messageId,
        disableNotification = disableNotification,
    )

    /**
     * Use this method to remove a message from the list of pinned messages in a chat. If the chat
     * is not a private chat, the bot must be an administrator in the chat for this to work and must
     * have the 'can_pin_messages' administrator right in a supergroup or 'can_edit_messages'
     * administrator right in a channel. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param messageId Identifier of a message to unpin. If not specified, the most recent pinned
     * message (by sending date) will be unpinned.
     */
    public suspend fun unpinChatMessage(chatId: Long, messageId: Long? = null): Boolean =
            unpinChatMessage(
        chatId = chatId.toString(),
        messageId = messageId,
    )

    /**
     * Use this method to clear the list of pinned messages in a chat. If the chat is not a private
     * chat, the bot must be an administrator in the chat for this to work and must have the
     * 'can_pin_messages' administrator right in a supergroup or 'can_edit_messages' administrator
     * right in a channel. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     */
    public suspend fun unpinAllChatMessages(chatId: Long): Boolean = unpinAllChatMessages(
        chatId = chatId.toString(),
    )

    /**
     * Use this method for your bot to leave a group, supergroup or channel. Returns *True* on
     * success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup or
     * channel (in the format `@channelusername`)
     */
    public suspend fun leaveChat(chatId: Long): Boolean = leaveChat(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to get up-to-date information about the chat. Returns a
     * [ChatFullInfo](https://core.telegram.org/bots/api/#chatfullinfo) object on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup or
     * channel (in the format `@channelusername`)
     */
    public suspend fun getChat(chatId: Long): ChatFullInfo = getChat(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to get a list of administrators in a chat, which aren't bots. Returns an
     * Array of [ChatMember](https://core.telegram.org/bots/api/#chatmember) objects.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup or
     * channel (in the format `@channelusername`)
     */
    public suspend fun getChatAdministrators(chatId: Long): List<ChatMember> =
            getChatAdministrators(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to get the number of members in a chat. Returns *Int* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup or
     * channel (in the format `@channelusername`)
     */
    public suspend fun getChatMemberCount(chatId: Long): Int = getChatMemberCount(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to get information about a member of a chat. The method is only guaranteed to
     * work for other users if the bot is an administrator in the chat. Returns a
     * [ChatMember](https://core.telegram.org/bots/api/#chatmember) object on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup or
     * channel (in the format `@channelusername`)
     * @param userId Unique identifier of the target user
     */
    public suspend fun getChatMember(chatId: Long, userId: Long): ChatMember = getChatMember(
        chatId = chatId.toString(),
        userId = userId,
    )

    /**
     * Use this method to set a new group sticker set for a supergroup. The bot must be an
     * administrator in the chat for this to work and must have the appropriate administrator rights.
     * Use the field *can_set_sticker_set* optionally returned in
     * [getChat](https://core.telegram.org/bots/api/#getchat) requests to check if the bot can use this
     * method. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param stickerSetName Name of the sticker set to be set as the group sticker set
     */
    public suspend fun setChatStickerSet(chatId: Long, stickerSetName: String): Boolean =
            setChatStickerSet(
        chatId = chatId.toString(),
        stickerSetName = stickerSetName,
    )

    /**
     * Use this method to delete a group sticker set from a supergroup. The bot must be an
     * administrator in the chat for this to work and must have the appropriate administrator rights.
     * Use the field *can_set_sticker_set* optionally returned in
     * [getChat](https://core.telegram.org/bots/api/#getchat) requests to check if the bot can use this
     * method. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     */
    public suspend fun deleteChatStickerSet(chatId: Long): Boolean = deleteChatStickerSet(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to create a topic in a forum supergroup chat. The bot must be an
     * administrator in the chat for this to work and must have the *can_manage_topics* administrator
     * rights. Returns information about the created topic as a
     * [ForumTopic](https://core.telegram.org/bots/api/#forumtopic) object.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param name Topic name, 1-128 characters
     * @param iconColor Color of the topic icon in RGB format. Currently, must be one of 7322096
     * (0x6FB9F0), 16766590 (0xFFD67E), 13338331 (0xCB86DB), 9367192 (0x8EEE98), 16749490 (0xFF93B2),
     * or 16478047 (0xFB6F5F)
     * @param iconCustomEmojiId Unique identifier of the custom emoji shown as the topic icon. Use
     * [getForumTopicIconStickers](https://core.telegram.org/bots/api/#getforumtopiciconstickers) to
     * get all allowed custom emoji identifiers.
     */
    public suspend fun createForumTopic(
        chatId: Long,
        name: String,
        iconColor: Int? = null,
        iconCustomEmojiId: String? = null,
    ): ForumTopic = createForumTopic(
        chatId = chatId.toString(),
        name = name,
        iconColor = iconColor,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    /**
     * Use this method to edit name and icon of a topic in a forum supergroup chat. The bot must be
     * an administrator in the chat for this to work and must have *can_manage_topics* administrator
     * rights, unless it is the creator of the topic. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param messageThreadId Unique identifier for the target message thread of the forum topic
     * @param name New topic name, 0-128 characters. If not specified or empty, the current name of
     * the topic will be kept
     * @param iconCustomEmojiId New unique identifier of the custom emoji shown as the topic icon.
     * Use [getForumTopicIconStickers](https://core.telegram.org/bots/api/#getforumtopiciconstickers)
     * to get all allowed custom emoji identifiers. Pass an empty string to remove the icon. If not
     * specified, the current icon will be kept
     */
    public suspend fun editForumTopic(
        chatId: Long,
        messageThreadId: Long,
        name: String? = null,
        iconCustomEmojiId: String? = null,
    ): Boolean = editForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
        name = name,
        iconCustomEmojiId = iconCustomEmojiId,
    )

    /**
     * Use this method to close an open topic in a forum supergroup chat. The bot must be an
     * administrator in the chat for this to work and must have the *can_manage_topics* administrator
     * rights, unless it is the creator of the topic. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param messageThreadId Unique identifier for the target message thread of the forum topic
     */
    public suspend fun closeForumTopic(chatId: Long, messageThreadId: Long): Boolean =
            closeForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    /**
     * Use this method to reopen a closed topic in a forum supergroup chat. The bot must be an
     * administrator in the chat for this to work and must have the *can_manage_topics* administrator
     * rights, unless it is the creator of the topic. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param messageThreadId Unique identifier for the target message thread of the forum topic
     */
    public suspend fun reopenForumTopic(chatId: Long, messageThreadId: Long): Boolean =
            reopenForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    /**
     * Use this method to delete a forum topic along with all its messages in a forum supergroup
     * chat. The bot must be an administrator in the chat for this to work and must have the
     * *can_delete_messages* administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param messageThreadId Unique identifier for the target message thread of the forum topic
     */
    public suspend fun deleteForumTopic(chatId: Long, messageThreadId: Long): Boolean =
            deleteForumTopic(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    /**
     * Use this method to clear the list of pinned messages in a forum topic. The bot must be an
     * administrator in the chat for this to work and must have the *can_pin_messages* administrator
     * right in the supergroup. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param messageThreadId Unique identifier for the target message thread of the forum topic
     */
    public suspend fun unpinAllForumTopicMessages(chatId: Long, messageThreadId: Long): Boolean =
            unpinAllForumTopicMessages(
        chatId = chatId.toString(),
        messageThreadId = messageThreadId,
    )

    /**
     * Use this method to edit the name of the 'General' topic in a forum supergroup chat. The bot
     * must be an administrator in the chat for this to work and must have *can_manage_topics*
     * administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     * @param name New topic name, 1-128 characters
     */
    public suspend fun editGeneralForumTopic(chatId: Long, name: String): Boolean =
            editGeneralForumTopic(
        chatId = chatId.toString(),
        name = name,
    )

    /**
     * Use this method to close an open 'General' topic in a forum supergroup chat. The bot must be
     * an administrator in the chat for this to work and must have the *can_manage_topics*
     * administrator rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     */
    public suspend fun closeGeneralForumTopic(chatId: Long): Boolean = closeGeneralForumTopic(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to reopen a closed 'General' topic in a forum supergroup chat. The bot must
     * be an administrator in the chat for this to work and must have the *can_manage_topics*
     * administrator rights. The topic will be automatically unhidden if it was hidden. Returns *True*
     * on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     */
    public suspend fun reopenGeneralForumTopic(chatId: Long): Boolean = reopenGeneralForumTopic(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to hide the 'General' topic in a forum supergroup chat. The bot must be an
     * administrator in the chat for this to work and must have the *can_manage_topics* administrator
     * rights. The topic will be automatically closed if it was open. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     */
    public suspend fun hideGeneralForumTopic(chatId: Long): Boolean = hideGeneralForumTopic(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to unhide the 'General' topic in a forum supergroup chat. The bot must be an
     * administrator in the chat for this to work and must have the *can_manage_topics* administrator
     * rights. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     */
    public suspend fun unhideGeneralForumTopic(chatId: Long): Boolean = unhideGeneralForumTopic(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to clear the list of pinned messages in a General forum topic. The bot must
     * be an administrator in the chat for this to work and must have the *can_pin_messages*
     * administrator right in the supergroup. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target supergroup (in
     * the format `@supergroupusername`)
     */
    public suspend fun unpinAllGeneralForumTopicMessages(chatId: Long): Boolean =
            unpinAllGeneralForumTopicMessages(
        chatId = chatId.toString(),
    )

    /**
     * Use this method to get the list of boosts added to a chat by a user. Requires administrator
     * rights in the chat. Returns a
     * [UserChatBoosts](https://core.telegram.org/bots/api/#userchatboosts) object.
     *
     * @param chatId Unique identifier for the chat or username of the channel (in the format
     * `@channelusername`)
     * @param userId Unique identifier of the target user
     */
    public suspend fun getUserChatBoosts(chatId: Long, userId: Long): UserChatBoosts =
            getUserChatBoosts(
        chatId = chatId.toString(),
        userId = userId,
    )

    /**
     * Use this method to edit text and [game](https://core.telegram.org/bots/api/#games) messages.
     * On success, if the edited message is not an inline message, the edited
     * [Message](https://core.telegram.org/bots/api/#message) is returned, otherwise *True* is
     * returned.
     *
     * @param chatId Required if *inline_message_id* is not specified. Unique identifier for the
     * target chat or username of the target channel (in the format `@channelusername`)
     * @param messageId Required if *inline_message_id* is not specified. Identifier of the message
     * to edit
     * @param text New text of the message, 1-4096 characters after entities parsing
     * @param parseMode Mode for parsing entities in the message text. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param entities A JSON-serialized list of special entities that appear in message text, which
     * can be specified instead of *parse_mode*
     * @param linkPreviewOptions Link preview generation options for the message
     * @param replyMarkup A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun editMessageText(
        chatId: Long,
        messageId: Long,
        text: String,
        parseMode: String? = null,
        entities: Iterable<MessageEntity>? = null,
        linkPreviewOptions: LinkPreviewOptions? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageText(
        chatId = chatId.toString(),
        messageId = messageId,
        text = text,
        parseMode = parseMode,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to edit captions of messages. On success, if the edited message is not an
     * inline message, the edited [Message](https://core.telegram.org/bots/api/#message) is returned,
     * otherwise *True* is returned.
     *
     * @param chatId Required if *inline_message_id* is not specified. Unique identifier for the
     * target chat or username of the target channel (in the format `@channelusername`)
     * @param messageId Required if *inline_message_id* is not specified. Identifier of the message
     * to edit
     * @param caption New caption of the message, 0-1024 characters after entities parsing
     * @param parseMode Mode for parsing entities in the message caption. See [formatting
     * options](https://core.telegram.org/bots/api/#formatting-options) for more details.
     * @param captionEntities A JSON-serialized list of special entities that appear in the caption,
     * which can be specified instead of *parse_mode*
     * @param showCaptionAboveMedia Pass *True*, if the caption must be shown above the message
     * media. Supported only for animation, photo and video messages.
     * @param replyMarkup A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun editMessageCaption(
        chatId: Long,
        messageId: Long,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageCaption(
        chatId = chatId.toString(),
        messageId = messageId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to edit animation, audio, document, photo, or video messages. If a message is
     * part of a message album, then it can be edited only to an audio for audio albums, only to a
     * document for document albums and to a photo or a video otherwise. When an inline message is
     * edited, a new file can't be uploaded; use a previously uploaded file via its file_id or specify
     * a URL. On success, if the edited message is not an inline message, the edited
     * [Message](https://core.telegram.org/bots/api/#message) is returned, otherwise *True* is
     * returned.
     *
     * @param chatId Required if *inline_message_id* is not specified. Unique identifier for the
     * target chat or username of the target channel (in the format `@channelusername`)
     * @param messageId Required if *inline_message_id* is not specified. Identifier of the message
     * to edit
     * @param media A JSON-serialized object for a new media content of the message
     * @param replyMarkup A JSON-serialized object for a new [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun editMessageMedia(
        chatId: Long,
        messageId: Long,
        media: InputMedia,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageMedia(
        chatId = chatId.toString(),
        messageId = messageId,
        media = media,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to edit live location messages. A location can be edited until its
     * *live_period* expires or editing is explicitly disabled by a call to
     * [stopMessageLiveLocation](https://core.telegram.org/bots/api/#stopmessagelivelocation). On
     * success, if the edited message is not an inline message, the edited
     * [Message](https://core.telegram.org/bots/api/#message) is returned, otherwise *True* is
     * returned.
     *
     * @param chatId Required if *inline_message_id* is not specified. Unique identifier for the
     * target chat or username of the target channel (in the format `@channelusername`)
     * @param messageId Required if *inline_message_id* is not specified. Identifier of the message
     * to edit
     * @param latitude Latitude of new location
     * @param longitude Longitude of new location
     * @param livePeriod New period in seconds during which the location can be updated, starting
     * from the message send date. If 0x7FFFFFFF is specified, then the location can be updated
     * forever. Otherwise, the new value must not exceed the current *live_period* by more than a day,
     * and the live location expiration date must remain within the next 90 days. If not specified,
     * then *live_period* remains unchanged
     * @param horizontalAccuracy The radius of uncertainty for the location, measured in meters;
     * 0-1500
     * @param heading Direction in which the user is moving, in degrees. Must be between 1 and 360
     * if specified.
     * @param proximityAlertRadius The maximum distance for proximity alerts about approaching
     * another chat member, in meters. Must be between 1 and 100000 if specified.
     * @param replyMarkup A JSON-serialized object for a new [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun editMessageLiveLocation(
        chatId: Long,
        messageId: Long,
        latitude: Double,
        longitude: Double,
        livePeriod: Int? = null,
        horizontalAccuracy: Double? = null,
        heading: Int? = null,
        proximityAlertRadius: Int? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageLiveLocation(
        chatId = chatId.toString(),
        messageId = messageId,
        latitude = latitude,
        longitude = longitude,
        livePeriod = livePeriod,
        horizontalAccuracy = horizontalAccuracy,
        heading = heading,
        proximityAlertRadius = proximityAlertRadius,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to stop updating a live location message before *live_period* expires. On
     * success, if the message is not an inline message, the edited
     * [Message](https://core.telegram.org/bots/api/#message) is returned, otherwise *True* is
     * returned.
     *
     * @param chatId Required if *inline_message_id* is not specified. Unique identifier for the
     * target chat or username of the target channel (in the format `@channelusername`)
     * @param messageId Required if *inline_message_id* is not specified. Identifier of the message
     * with live location to stop
     * @param replyMarkup A JSON-serialized object for a new [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun stopMessageLiveLocation(
        chatId: Long,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = stopMessageLiveLocation(
        chatId = chatId.toString(),
        messageId = messageId,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to edit only the reply markup of messages. On success, if the edited message
     * is not an inline message, the edited [Message](https://core.telegram.org/bots/api/#message) is
     * returned, otherwise *True* is returned.
     *
     * @param chatId Required if *inline_message_id* is not specified. Unique identifier for the
     * target chat or username of the target channel (in the format `@channelusername`)
     * @param messageId Required if *inline_message_id* is not specified. Identifier of the message
     * to edit
     * @param replyMarkup A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun editMessageReplyMarkup(
        chatId: Long,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = editMessageReplyMarkup(
        chatId = chatId.toString(),
        messageId = messageId,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to stop a poll which was sent by the bot. On success, the stopped
     * [Poll](https://core.telegram.org/bots/api/#poll) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param messageId Identifier of the original message with the poll
     * @param replyMarkup A JSON-serialized object for a new message [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards).
     */
    public suspend fun stopPoll(
        chatId: Long,
        messageId: Long,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Poll = stopPoll(
        chatId = chatId.toString(),
        messageId = messageId,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to delete a message, including service messages, with the following
     * limitations:  
     * - A message can only be deleted if it was sent less than 48 hours ago.  
     * - Service messages about a supergroup, channel, or forum topic creation can't be deleted.  
     * - A dice message in a private chat can only be deleted if it was sent more than 24 hours ago.
     *  
     * - Bots can delete outgoing messages in private chats, groups, and supergroups.  
     * - Bots can delete incoming messages in private chats.  
     * - Bots granted *can_post_messages* permissions can delete outgoing messages in channels.  
     * - If the bot is an administrator of a group, it can delete any message there.  
     * - If the bot has *can_delete_messages* permission in a supergroup or a channel, it can delete
     * any message there.  
     * Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param messageId Identifier of the message to delete
     */
    public suspend fun deleteMessage(chatId: Long, messageId: Long): Boolean = deleteMessage(
        chatId = chatId.toString(),
        messageId = messageId,
    )

    /**
     * Use this method to delete multiple messages simultaneously. If some of the specified messages
     * can't be found, they are skipped. Returns *True* on success.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages to delete. See
     * [deleteMessage](https://core.telegram.org/bots/api/#deletemessage) for limitations on which
     * messages can be deleted
     */
    public suspend fun deleteMessages(chatId: Long, messageIds: Iterable<Long>): Boolean =
            deleteMessages(
        chatId = chatId.toString(),
        messageIds = messageIds,
    )

    /**
     * Use this method to send static .WEBP, [animated](https://telegram.org/blog/animated-stickers)
     * .TGS, or [video](https://telegram.org/blog/video-stickers-better-reactions) .WEBM stickers. On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param sticker Sticker to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a .WEBP sticker
     * from the Internet, or upload a new .WEBP, .TGS, or .WEBM sticker using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Video
     * and animated stickers can't be sent via an HTTP URL.
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param emoji Emoji associated with the sticker; only for just uploaded stickers
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendSticker(
        chatId: Long,
        sticker: Input,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendSticker(
        chatId = chatId.toString(),
        sticker = sticker,
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send static .WEBP, [animated](https://telegram.org/blog/animated-stickers)
     * .TGS, or [video](https://telegram.org/blog/video-stickers-better-reactions) .WEBM stickers. On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param sticker Sticker to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a .WEBP sticker
     * from the Internet, or upload a new .WEBP, .TGS, or .WEBM sticker using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Video
     * and animated stickers can't be sent via an HTTP URL.
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param emoji Emoji associated with the sticker; only for just uploaded stickers
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendSticker(
        chatId: String,
        sticker: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendSticker(
        chatId = chatId,
        sticker = StringInput(sticker),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send static .WEBP, [animated](https://telegram.org/blog/animated-stickers)
     * .TGS, or [video](https://telegram.org/blog/video-stickers-better-reactions) .WEBM stickers. On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param sticker Sticker to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a .WEBP sticker
     * from the Internet, or upload a new .WEBP, .TGS, or .WEBM sticker using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Video
     * and animated stickers can't be sent via an HTTP URL.
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param emoji Emoji associated with the sticker; only for just uploaded stickers
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun sendSticker(
        chatId: Long,
        sticker: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendSticker(
        chatId = chatId.toString(),
        sticker = StringInput(sticker),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to set the thumbnail of a regular or mask sticker set. The format of the
     * thumbnail file must match the format of the stickers in the set. Returns *True* on success.
     *
     * @param name Sticker set name
     * @param userId User identifier of the sticker set owner
     * @param format Format of the thumbnail, must be one of “static” for a **.WEBP** or **.PNG**
     * image, “animated” for a **.TGS** animation, or “video” for a **WEBM** video
     * @param thumbnail A **.WEBP** or **.PNG** image with the thumbnail, must be up to 128
     * kilobytes in size and have a width and height of exactly 100px, or a **.TGS** animation with a
     * thumbnail up to 32 kilobytes in size (see
     * [https://core.telegram.org/stickers#animated-sticker-requirements](https://core.telegram.org/stickers#animated-sticker-requirements)
     * for animated sticker technical requirements), or a **WEBM** video with the thumbnail up to 32
     * kilobytes in size; see
     * [https://core.telegram.org/stickers#video-sticker-requirements](https://core.telegram.org/stickers#video-sticker-requirements)
     * for video sticker technical requirements. Pass a *file_id* as a String to send a file that
     * already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file
     * from the Internet, or upload a new one using multipart/form-data. [More information on Sending
     * Files ](https://core.telegram.org/bots/api/#sending-files). Animated and video sticker set
     * thumbnails can't be uploaded via HTTP URL. If omitted, then the thumbnail is dropped and the
     * first sticker is used as the thumbnail.
     */
    public suspend fun setStickerSetThumbnail(
        name: String,
        userId: Long,
        format: String,
        thumbnail: String? = null,
    ): Boolean = setStickerSetThumbnail(
        name = name,
        userId = userId,
        format = format,
        thumbnail = thumbnail?.let { StringInput(thumbnail) },
    )

    /**
     * Use this method to send invoices. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the
     * format `@channelusername`)
     * @param title Product name, 1-32 characters
     * @param description Product description, 1-255 characters
     * @param payload Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the
     * user, use for your internal processes.
     * @param currency Three-letter ISO 4217 currency code, see [more on
     * currencies](https://core.telegram.org/bots/payments#supported-currencies). Pass “XTR” for
     * payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param prices Price breakdown, a JSON-serialized list of components (e.g. product price, tax,
     * discount, delivery cost, delivery tax, bonus, etc.). Must contain exactly one item for payments
     * in [Telegram Stars](https://t.me/BotNews/90).
     * @param messageThreadId Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     * @param providerToken Payment provider token, obtained via
     * [@BotFather](https://t.me/botfather). Pass an empty string for payments in [Telegram
     * Stars](https://t.me/BotNews/90).
     * @param maxTipAmount The maximum accepted amount for tips in the *smallest units* of the
     * currency (integer, **not** float/double). For example, for a maximum tip of `US$ 1.45` pass
     * `max_tip_amount = 145`. See the *exp* parameter in
     * [currencies.json](https://core.telegram.org/bots/payments/currencies.json), it shows the number
     * of digits past the decimal point for each currency (2 for the majority of currencies). Defaults
     * to 0. Not supported for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param suggestedTipAmounts A JSON-serialized array of suggested amounts of tips in the
     * *smallest units* of the currency (integer, **not** float/double). At most 4 suggested tip
     * amounts can be specified. The suggested tip amounts must be positive, passed in a strictly
     * increased order and must not exceed *max_tip_amount*.
     * @param startParameter Unique deep-linking parameter. If left empty, **forwarded copies** of
     * the sent message will have a *Pay* button, allowing multiple users to pay directly from the
     * forwarded message, using the same invoice. If non-empty, forwarded copies of the sent message
     * will have a *URL* button with a deep link to the bot (instead of a *Pay* button), with the value
     * used as the start parameter
     * @param providerData JSON-serialized data about the invoice, which will be shared with the
     * payment provider. A detailed description of required fields should be provided by the payment
     * provider.
     * @param photoUrl URL of the product photo for the invoice. Can be a photo of the goods or a
     * marketing image for a service. People like it better when they see what they are paying for.
     * @param photoSize Photo size in bytes
     * @param photoWidth Photo width
     * @param photoHeight Photo height
     * @param needName Pass *True* if you require the user's full name to complete the order.
     * Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param needPhoneNumber Pass *True* if you require the user's phone number to complete the
     * order. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param needEmail Pass *True* if you require the user's email address to complete the order.
     * Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param needShippingAddress Pass *True* if you require the user's shipping address to complete
     * the order. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param sendPhoneNumberToProvider Pass *True* if the user's phone number should be sent to the
     * provider. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param sendEmailToProvider Pass *True* if the user's email address should be sent to the
     * provider. Ignored for payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param isFlexible Pass *True* if the final price depends on the shipping method. Ignored for
     * payments in [Telegram Stars](https://t.me/BotNews/90).
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards). If empty, one 'Pay `total
     * price`' button will be shown. If not empty, the first button must be a Pay button.
     */
    public suspend fun sendInvoice(
        chatId: Long,
        title: String,
        description: String,
        payload: String,
        currency: String,
        prices: Iterable<LabeledPrice>,
        messageThreadId: Long? = null,
        providerToken: String? = null,
        maxTipAmount: Int? = null,
        suggestedTipAmounts: Iterable<Int>? = null,
        startParameter: String? = null,
        providerData: String? = null,
        photoUrl: String? = null,
        photoSize: Int? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null,
        needName: Boolean? = null,
        needPhoneNumber: Boolean? = null,
        needEmail: Boolean? = null,
        needShippingAddress: Boolean? = null,
        sendPhoneNumberToProvider: Boolean? = null,
        sendEmailToProvider: Boolean? = null,
        isFlexible: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: InlineKeyboardMarkup? = null,
    ): Message = sendInvoice(
        chatId = chatId.toString(),
        title = title,
        description = description,
        payload = payload,
        currency = currency,
        prices = prices,
        messageThreadId = messageThreadId,
        providerToken = providerToken,
        maxTipAmount = maxTipAmount,
        suggestedTipAmounts = suggestedTipAmounts,
        startParameter = startParameter,
        providerData = providerData,
        photoUrl = photoUrl,
        photoSize = photoSize,
        photoWidth = photoWidth,
        photoHeight = photoHeight,
        needName = needName,
        needPhoneNumber = needPhoneNumber,
        needEmail = needEmail,
        needShippingAddress = needShippingAddress,
        sendPhoneNumberToProvider = sendPhoneNumberToProvider,
        sendEmailToProvider = sendEmailToProvider,
        isFlexible = isFlexible,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )
}
