package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageEntity
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageId
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyMarkup
import io.github.dehuckakpyt.telegrambot.model.telegram.ReplyParameters
import io.github.dehuckakpyt.telegrambot.model.telegram.SuggestedPostParameters
import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List

/**
 * @author KScript
 */
public abstract class TelegramBotApiExtHandling : TelegramBotApiHandling() {
    /**
     * Use this method to forward messages of any kind. Service messages and messages with protected
     * content can't be forwarded. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be forwarded; required if the message is forwarded to a direct messages chat
     * @param videoStartTimestamp New start timestamp for the forwarded video in the message
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded message from forwarding and
     * saving
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * only available when forwarding to private chats
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only
     */
    public suspend fun Container.forwardMessage(
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        videoStartTimestamp: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
    ): Message = forwardMessage(
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        videoStartTimestamp = videoStartTimestamp,
        disableNotification = disableNotification,
        protectContent = protectContent,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
    )

    /**
     * Use this method to forward multiple messages of any kind. If some of the specified messages
     * can't be found or forwarded, they are skipped. Service messages and messages with protected
     * content can't be forwarded. Album grouping is kept for forwarded messages. On success, an array
     * of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent messages is returned.
     *
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to forward. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the messages
     * will be forwarded; required if the messages are forwarded to a direct messages chat
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the forwarded messages from forwarding and
     * saving
     */
    public suspend fun Container.forwardMessages(
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
    ): List<MessageId> = forwardMessages(
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        disableNotification = disableNotification,
        protectContent = protectContent,
    )

    /**
     * Use this method to copy messages of any kind. Service messages, paid media messages, giveaway
     * messages, giveaway winners messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessage](https://core.telegram.org/bots/api/#forwardmessage), but the copied message
     * doesn't have a link to the original message. Returns the
     * [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent message on success.
     *
     * @param fromChatId Unique identifier for the chat where the original message was sent (or
     * channel username in the format `@channelusername`)
     * @param messageId Message identifier in the chat specified in *from_chat_id*
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
     * @param videoStartTimestamp New start timestamp for the copied video in the message
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * only available when copying to private chats
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.copyMessage(
        fromChatId: Long,
        messageId: Long,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        videoStartTimestamp: Int? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): MessageId = copyMessage(
        fromChatId = fromChatId.toString(),
        messageId = messageId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        videoStartTimestamp = videoStartTimestamp,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to copy messages of any kind. If some of the specified messages can't be
     * found or copied, they are skipped. Service messages, paid media messages, giveaway messages,
     * giveaway winners messages, and invoice messages can't be copied. A quiz
     * [poll](https://core.telegram.org/bots/api/#poll) can be copied only if the value of the field
     * *correct_option_id* is known to the bot. The method is analogous to the method
     * [forwardMessages](https://core.telegram.org/bots/api/#forwardmessages), but the copied messages
     * don't have a link to the original message. Album grouping is kept for copied messages. On
     * success, an array of [MessageId](https://core.telegram.org/bots/api/#messageid) of the sent
     * messages is returned.
     *
     * @param fromChatId Unique identifier for the chat where the original messages were sent (or
     * channel username in the format `@channelusername`)
     * @param messageIds A JSON-serialized list of 1-100 identifiers of messages in the chat
     * *from_chat_id* to copy. The identifiers must be specified in a strictly increasing order.
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the messages
     * will be sent; required if the messages are sent to a direct messages chat
     * @param disableNotification Sends the messages
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent messages from forwarding and saving
     * @param removeCaption Pass *True* to copy the messages without their captions
     */
    public suspend fun Container.copyMessages(
        fromChatId: Long,
        messageIds: Iterable<Long>,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        removeCaption: Boolean? = null,
    ): List<MessageId> = copyMessages(
        fromChatId = fromChatId.toString(),
        messageIds = messageIds,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        removeCaption = removeCaption,
    )

    /**
     * Use this method to send photos. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param photo Photo to send. Pass a file_id as String to send a photo that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a photo from
     * the Internet, or upload a new photo using multipart/form-data. The photo must be at most 10 MB
     * in size. The photo's width and height must not exceed 10000 in total. Width and height ratio
     * must be at most 20. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendPhoto(
        photo: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendPhoto(
        photo = StringInput(photo),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
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
     * @param audio Audio file to send. Pass a file_id as String to send an audio file that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an audio
     * file from the Internet, or upload a new one using multipart/form-data. [More information on
     * Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendAudio(
        audio: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
        thumbnail: Input? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAudio(
        audio = StringInput(audio),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        performer = performer,
        title = title,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send general files. On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * files of any type of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param document File to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendDocument(
        document: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        thumbnail: Input? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendDocument(
        document = StringInput(document),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        thumbnail = thumbnail,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        disableContentTypeDetection = disableContentTypeDetection,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send video files, Telegram clients support MPEG4 videos (other formats may
     * be sent as [Document](https://core.telegram.org/bots/api/#document)). On success, the sent
     * [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can currently send
     * video files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param video Video to send. Pass a file_id as String to send a video that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a video from
     * the Internet, or upload a new video using multipart/form-data. [More information on Sending
     * Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param cover Cover for the video in the message. Pass a file_id to send a file that exists on
     * the Telegram servers (recommended), pass an HTTP URL for Telegram to get a file from the
     * Internet, or pass “attach://\<file_attach_name\>” to upload a new one using multipart/form-data
     * under \<file_attach_name\> name. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param startTimestamp Start timestamp for the video in the message
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendVideo(
        video: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: Input? = null,
        cover: Input? = null,
        startTimestamp: Int? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        supportsStreaming: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideo(
        video = StringInput(video),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        duration = duration,
        width = width,
        height = height,
        thumbnail = thumbnail,
        cover = cover,
        startTimestamp = startTimestamp,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        showCaptionAboveMedia = showCaptionAboveMedia,
        hasSpoiler = hasSpoiler,
        supportsStreaming = supportsStreaming,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned. Bots can
     * currently send animation files of up to 50 MB in size, this limit may be changed in the future.
     *
     * @param animation Animation to send. Pass a file_id as String to send an animation that exists
     * on the Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get an
     * animation from the Internet, or upload a new animation using multipart/form-data. [More
     * information on Sending Files ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendAnimation(
        animation: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        duration: Int? = null,
        width: Int? = null,
        height: Int? = null,
        thumbnail: Input? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        showCaptionAboveMedia: Boolean? = null,
        hasSpoiler: Boolean? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendAnimation(
        animation = StringInput(animation),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
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
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
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
     * @param voice Audio file to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a file from the
     * Internet, or upload a new one using multipart/form-data. [More information on Sending Files
     * ](https://core.telegram.org/bots/api/#sending-files)
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendVoice(
        voice: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: Iterable<MessageEntity>? = null,
        duration: Int? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVoice(
        voice = StringInput(voice),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        caption = caption,
        parseMode = parseMode,
        captionEntities = captionEntities,
        duration = duration,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * As of [v.4.0](https://telegram.org/blog/video-messages-and-telescope), Telegram clients
     * support rounded square MPEG4 videos of up to 1 minute long. Use this method to send video
     * messages. On success, the sent [Message](https://core.telegram.org/bots/api/#message) is
     * returned.
     *
     * @param videoNote Video note to send. Pass a file_id as String to send a video note that
     * exists on the Telegram servers (recommended) or upload a new video using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Sending
     * video notes by a URL is currently unsupported
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
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
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendVideoNote(
        videoNote: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        duration: Int? = null,
        length: Int? = null,
        thumbnail: Input? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendVideoNote(
        videoNote = StringInput(videoNote),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        duration = duration,
        length = length,
        thumbnail = thumbnail,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    )

    /**
     * Use this method to send static .WEBP, [animated](https://telegram.org/blog/animated-stickers)
     * .TGS, or [video](https://telegram.org/blog/video-stickers-better-reactions) .WEBM stickers. On
     * success, the sent [Message](https://core.telegram.org/bots/api/#message) is returned.
     *
     * @param sticker Sticker to send. Pass a file_id as String to send a file that exists on the
     * Telegram servers (recommended), pass an HTTP URL as a String for Telegram to get a .WEBP sticker
     * from the Internet, or upload a new .WEBP, .TGS, or .WEBM sticker using multipart/form-data.
     * [More information on Sending Files ](https://core.telegram.org/bots/api/#sending-files). Video
     * and animated stickers can't be sent via an HTTP URL.
     * @param businessConnectionId Unique identifier of the business connection on behalf of which
     * the message will be sent
     * @param messageThreadId Unique identifier for the target message thread (topic) of a forum;
     * for forum supergroups and private chats of bots with forum topic mode enabled only
     * @param directMessagesTopicId Identifier of the direct messages topic to which the message
     * will be sent; required if the message is sent to a direct messages chat
     * @param emoji Emoji associated with the sticker; only for just uploaded stickers
     * @param disableNotification Sends the message
     * [silently](https://telegram.org/blog/channels-2-0#silent-messages). Users will receive a
     * notification with no sound.
     * @param protectContent Protects the contents of the sent message from forwarding and saving
     * @param allowPaidBroadcast Pass *True* to allow up to 1000 messages per second, ignoring
     * [broadcasting
     * limits](https://core.telegram.org/bots/faq#how-can-i-message-all-of-my-bot-39s-subscribers-at-once)
     * for a fee of 0.1 Telegram Stars per message. The relevant Stars will be withdrawn from the bot's
     * balance
     * @param messageEffectId Unique identifier of the message effect to be added to the message;
     * for private chats only
     * @param suggestedPostParameters A JSON-serialized object containing the parameters of the
     * suggested post to send; for direct messages chats only. If the message is sent as a reply to
     * another suggested post, then that suggested post is automatically declined.
     * @param replyParameters Description of the message to reply to
     * @param replyMarkup Additional interface options. A JSON-serialized object for an [inline
     * keyboard](https://core.telegram.org/bots/features#inline-keyboards), [custom reply
     * keyboard](https://core.telegram.org/bots/features#keyboards), instructions to remove a reply
     * keyboard or to force a reply from the user
     */
    public suspend fun Container.sendSticker(
        sticker: String,
        businessConnectionId: String? = null,
        messageThreadId: Long? = null,
        directMessagesTopicId: Long? = null,
        emoji: String? = null,
        disableNotification: Boolean? = null,
        protectContent: Boolean? = null,
        allowPaidBroadcast: Boolean? = null,
        messageEffectId: String? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: ReplyMarkup? = null,
    ): Message = sendSticker(
        sticker = StringInput(sticker),
        businessConnectionId = businessConnectionId,
        messageThreadId = messageThreadId,
        directMessagesTopicId = directMessagesTopicId,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        messageEffectId = messageEffectId,
        suggestedPostParameters = suggestedPostParameters,
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
     * image, “animated” for a **.TGS** animation, or “video” for a **.WEBM** video
     * @param thumbnail A **.WEBP** or **.PNG** image with the thumbnail, must be up to 128
     * kilobytes in size and have a width and height of exactly 100px, or a **.TGS** animation with a
     * thumbnail up to 32 kilobytes in size (see
     * [https://core.telegram.org/stickers#animation-requirements](https://core.telegram.org/stickers#animation-requirements)
     * for animated sticker technical requirements), or a **.WEBM** video with the thumbnail up to 32
     * kilobytes in size; see
     * [https://core.telegram.org/stickers#video-requirements](https://core.telegram.org/stickers#video-requirements)
     * for video sticker technical requirements. Pass a *file_id* as a String to send a file that
     * already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file
     * from the Internet, or upload a new one using multipart/form-data. [More information on Sending
     * Files ](https://core.telegram.org/bots/api/#sending-files). Animated and video sticker set
     * thumbnails can't be uploaded via HTTP URL. If omitted, then the thumbnail is dropped and the
     * first sticker is used as the thumbnail.
     */
    public suspend fun Container.setStickerSetThumbnail(
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
}
