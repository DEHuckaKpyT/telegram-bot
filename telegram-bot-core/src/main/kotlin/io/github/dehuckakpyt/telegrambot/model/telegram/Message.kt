package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * This object represents a message.
 *
 * @see [Message] (https://core.telegram.org/bots/api/#message)
 *
 * @author KScript
 *
 * @param messageId Unique message identifier inside this chat. In specific instances (e.g., message
 * containing a video sent to a big chat), the server might automatically schedule a message instead of
 * sending it immediately. In such cases, this field will be 0 and the relevant message will be
 * unusable until it is actually sent
 * @param messageThreadId *Optional*. Unique identifier of a message thread to which the message
 * belongs; for supergroups only
 * @param directMessagesTopic *Optional*. Information about the direct messages chat topic that
 * contains the message
 * @param from *Optional*. Sender of the message; may be empty for messages sent to channels. For
 * backward compatibility, if the message was sent on behalf of a chat, the field contains a fake
 * sender user in non-channel chats
 * @param senderChat *Optional*. Sender of the message when sent on behalf of a chat. For example,
 * the supergroup itself for messages sent by its anonymous administrators or a linked channel for
 * messages automatically forwarded to the channel's discussion group. For backward compatibility, if
 * the message was sent on behalf of a chat, the field *from* contains a fake sender user in
 * non-channel chats.
 * @param senderBoostCount *Optional*. If the sender of the message boosted the chat, the number of
 * boosts added by the user
 * @param senderBusinessBot *Optional*. The bot that actually sent the message on behalf of the
 * business account. Available only for outgoing messages sent on behalf of the connected business
 * account.
 * @param date Date the message was sent in Unix time. It is always a positive number, representing
 * a valid date.
 * @param businessConnectionId *Optional*. Unique identifier of the business connection from which
 * the message was received. If non-empty, the message belongs to a chat of the corresponding business
 * account that is independent from any potential bot chat which might share the same identifier.
 * @param chat Chat the message belongs to
 * @param forwardOrigin *Optional*. Information about the original message for forwarded messages
 * @param isTopicMessage *Optional*. *True*, if the message is sent to a forum topic
 * @param isAutomaticForward *Optional*. *True*, if the message is a channel post that was
 * automatically forwarded to the connected discussion group
 * @param replyToMessage *Optional*. For replies in the same chat and message thread, the original
 * message. Note that the [Message](https://core.telegram.org/bots/api/#message) object in this field
 * will not contain further *reply_to_message* fields even if it itself is a reply.
 * @param externalReply *Optional*. Information about the message that is being replied to, which
 * may come from another chat or forum topic
 * @param quote *Optional*. For replies that quote part of the original message, the quoted part of
 * the message
 * @param replyToStory *Optional*. For replies to a story, the original story
 * @param replyToChecklistTaskId *Optional*. Identifier of the specific checklist task that is being
 * replied to
 * @param viaBot *Optional*. Bot through which the message was sent
 * @param editDate *Optional*. Date the message was last edited in Unix time
 * @param hasProtectedContent *Optional*. *True*, if the message can't be forwarded
 * @param isFromOffline *Optional*. *True*, if the message was sent by an implicit action, for
 * example, as an away or a greeting business message, or as a scheduled message
 * @param isPaidPost *Optional*. *True*, if the message is a paid post. Note that such posts must
 * not be deleted for 24 hours to receive the payment and can't be edited.
 * @param mediaGroupId *Optional*. The unique identifier of a media message group this message
 * belongs to
 * @param authorSignature *Optional*. Signature of the post author for messages in channels, or the
 * custom title of an anonymous group administrator
 * @param paidStarCount *Optional*. The number of Telegram Stars that were paid by the sender of the
 * message to send it
 * @param text *Optional*. For text messages, the actual UTF-8 text of the message
 * @param entities *Optional*. For text messages, special entities like usernames, URLs, bot
 * commands, etc. that appear in the text
 * @param linkPreviewOptions *Optional*. Options used for link preview generation for the message,
 * if it is a text message and link preview options were changed
 * @param suggestedPostInfo *Optional*. Information about suggested post parameters if the message
 * is a suggested post in a channel direct messages chat. If the message is an approved or declined
 * suggested post, then it can't be edited.
 * @param effectId *Optional*. Unique identifier of the message effect added to the message
 * @param animation *Optional*. Message is an animation, information about the animation. For
 * backward compatibility, when this field is set, the *document* field will also be set
 * @param audio *Optional*. Message is an audio file, information about the file
 * @param document *Optional*. Message is a general file, information about the file
 * @param paidMedia *Optional*. Message contains paid media; information about the paid media
 * @param photo *Optional*. Message is a photo, available sizes of the photo
 * @param sticker *Optional*. Message is a sticker, information about the sticker
 * @param story *Optional*. Message is a forwarded story
 * @param video *Optional*. Message is a video, information about the video
 * @param videoNote *Optional*. Message is a [video
 * note](https://telegram.org/blog/video-messages-and-telescope), information about the video message
 * @param voice *Optional*. Message is a voice message, information about the file
 * @param caption *Optional*. Caption for the animation, audio, document, paid media, photo, video
 * or voice
 * @param captionEntities *Optional*. For messages with a caption, special entities like usernames,
 * URLs, bot commands, etc. that appear in the caption
 * @param showCaptionAboveMedia *Optional*. *True*, if the caption must be shown above the message
 * media
 * @param hasMediaSpoiler *Optional*. *True*, if the message media is covered by a spoiler animation
 * @param checklist *Optional*. Message is a checklist
 * @param contact *Optional*. Message is a shared contact, information about the contact
 * @param dice *Optional*. Message is a dice with random value
 * @param game *Optional*. Message is a game, information about the game. [More about games
 * ](https://core.telegram.org/bots/api/#games)
 * @param poll *Optional*. Message is a native poll, information about the poll
 * @param venue *Optional*. Message is a venue, information about the venue. For backward
 * compatibility, when this field is set, the *location* field will also be set
 * @param location *Optional*. Message is a shared location, information about the location
 * @param newChatMembers *Optional*. New members that were added to the group or supergroup and
 * information about them (the bot itself may be one of these members)
 * @param leftChatMember *Optional*. A member was removed from the group, information about them
 * (this member may be the bot itself)
 * @param newChatTitle *Optional*. A chat title was changed to this value
 * @param newChatPhoto *Optional*. A chat photo was change to this value
 * @param deleteChatPhoto *Optional*. Service message: the chat photo was deleted
 * @param groupChatCreated *Optional*. Service message: the group has been created
 * @param supergroupChatCreated *Optional*. Service message: the supergroup has been created. This
 * field can't be received in a message coming through updates, because bot can't be a member of a
 * supergroup when it is created. It can only be found in reply_to_message if someone replies to a very
 * first message in a directly created supergroup.
 * @param channelChatCreated *Optional*. Service message: the channel has been created. This field
 * can't be received in a message coming through updates, because bot can't be a member of a channel
 * when it is created. It can only be found in reply_to_message if someone replies to a very first
 * message in a channel.
 * @param messageAutoDeleteTimerChanged *Optional*. Service message: auto-delete timer settings
 * changed in the chat
 * @param migrateToChatId *Optional*. The group has been migrated to a supergroup with the specified
 * identifier. This number may have more than 32 significant bits and some programming languages may
 * have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
 * signed 64-bit integer or double-precision float type are safe for storing this identifier.
 * @param migrateFromChatId *Optional*. The supergroup has been migrated from a group with the
 * specified identifier. This number may have more than 32 significant bits and some programming
 * languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant
 * bits, so a signed 64-bit integer or double-precision float type are safe for storing this
 * identifier.
 * @param pinnedMessage *Optional*. Specified message was pinned. Note that the
 * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain further
 * *reply_to_message* fields even if it itself is a reply.
 * @param invoice *Optional*. Message is an invoice for a
 * [payment](https://core.telegram.org/bots/api/#payments), information about the invoice. [More about
 * payments ](https://core.telegram.org/bots/api/#payments)
 * @param successfulPayment *Optional*. Message is a service message about a successful payment,
 * information about the payment. [More about payments ](https://core.telegram.org/bots/api/#payments)
 * @param refundedPayment *Optional*. Message is a service message about a refunded payment,
 * information about the payment. [More about payments ](https://core.telegram.org/bots/api/#payments)
 * @param usersShared *Optional*. Service message: users were shared with the bot
 * @param chatShared *Optional*. Service message: a chat was shared with the bot
 * @param gift *Optional*. Service message: a regular gift was sent or received
 * @param uniqueGift *Optional*. Service message: a unique gift was sent or received
 * @param connectedWebsite *Optional*. The domain name of the website on which the user has logged
 * in. [More about Telegram Login ](https://core.telegram.org/widgets/login)
 * @param writeAccessAllowed *Optional*. Service message: the user allowed the bot to write messages
 * after adding it to the attachment or side menu, launching a Web App from a link, or accepting an
 * explicit request from a Web App sent by the method
 * [requestWriteAccess](https://core.telegram.org/bots/webapps#initializing-mini-apps)
 * @param passportData *Optional*. Telegram Passport data
 * @param proximityAlertTriggered *Optional*. Service message. A user in the chat triggered another
 * user's proximity alert while sharing Live Location.
 * @param boostAdded *Optional*. Service message: user boosted the chat
 * @param chatBackgroundSet *Optional*. Service message: chat background set
 * @param checklistTasksDone *Optional*. Service message: some tasks in a checklist were marked as
 * done or not done
 * @param checklistTasksAdded *Optional*. Service message: tasks were added to a checklist
 * @param directMessagePriceChanged *Optional*. Service message: the price for paid messages in the
 * corresponding direct messages chat of a channel has changed
 * @param forumTopicCreated *Optional*. Service message: forum topic created
 * @param forumTopicEdited *Optional*. Service message: forum topic edited
 * @param forumTopicClosed *Optional*. Service message: forum topic closed
 * @param forumTopicReopened *Optional*. Service message: forum topic reopened
 * @param generalForumTopicHidden *Optional*. Service message: the 'General' forum topic hidden
 * @param generalForumTopicUnhidden *Optional*. Service message: the 'General' forum topic unhidden
 * @param giveawayCreated *Optional*. Service message: a scheduled giveaway was created
 * @param giveaway *Optional*. The message is a scheduled giveaway message
 * @param giveawayWinners *Optional*. A giveaway with public winners was completed
 * @param giveawayCompleted *Optional*. Service message: a giveaway without public winners was
 * completed
 * @param paidMessagePriceChanged *Optional*. Service message: the price for paid messages has
 * changed in the chat
 * @param suggestedPostApproved *Optional*. Service message: a suggested post was approved
 * @param suggestedPostApprovalFailed *Optional*. Service message: approval of a suggested post has
 * failed
 * @param suggestedPostDeclined *Optional*. Service message: a suggested post was declined
 * @param suggestedPostPaid *Optional*. Service message: payment for a suggested post was received
 * @param suggestedPostRefunded *Optional*. Service message: payment for a suggested post was
 * refunded
 * @param videoChatScheduled *Optional*. Service message: video chat scheduled
 * @param videoChatStarted *Optional*. Service message: video chat started
 * @param videoChatEnded *Optional*. Service message: video chat ended
 * @param videoChatParticipantsInvited *Optional*. Service message: new participants invited to a
 * video chat
 * @param webAppData *Optional*. Service message: data sent by a Web App
 * @param replyMarkup *Optional*. Inline keyboard attached to the message. `login_url` buttons are
 * represented as ordinary `url` buttons.
 */
public data class Message(
    /**
     * Unique message identifier inside this chat. In specific instances (e.g., message containing a
     * video sent to a big chat), the server might automatically schedule a message instead of sending
     * it immediately. In such cases, this field will be 0 and the relevant message will be unusable
     * until it is actually sent
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    override val messageId: Long,
    /**
     * *Optional*. Unique identifier of a message thread to which the message belongs; for
     * supergroups only
     */
    @get:JsonProperty("message_thread_id")
    @param:JsonProperty("message_thread_id")
    public val messageThreadId: Long? = null,
    /**
     * *Optional*. Information about the direct messages chat topic that contains the message
     */
    @get:JsonProperty("direct_messages_topic")
    @param:JsonProperty("direct_messages_topic")
    public val directMessagesTopic: DirectMessagesTopic? = null,
    /**
     * *Optional*. Sender of the message; may be empty for messages sent to channels. For backward
     * compatibility, if the message was sent on behalf of a chat, the field contains a fake sender
     * user in non-channel chats
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User? = null,
    /**
     * *Optional*. Sender of the message when sent on behalf of a chat. For example, the supergroup
     * itself for messages sent by its anonymous administrators or a linked channel for messages
     * automatically forwarded to the channel's discussion group. For backward compatibility, if the
     * message was sent on behalf of a chat, the field *from* contains a fake sender user in
     * non-channel chats.
     */
    @get:JsonProperty("sender_chat")
    @param:JsonProperty("sender_chat")
    public val senderChat: Chat? = null,
    /**
     * *Optional*. If the sender of the message boosted the chat, the number of boosts added by the
     * user
     */
    @get:JsonProperty("sender_boost_count")
    @param:JsonProperty("sender_boost_count")
    public val senderBoostCount: Int? = null,
    /**
     * *Optional*. The bot that actually sent the message on behalf of the business account.
     * Available only for outgoing messages sent on behalf of the connected business account.
     */
    @get:JsonProperty("sender_business_bot")
    @param:JsonProperty("sender_business_bot")
    public val senderBusinessBot: User? = null,
    /**
     * Date the message was sent in Unix time. It is always a positive number, representing a valid
     * date.
     */
    @get:JsonProperty("date")
    @param:JsonProperty("date")
    override val date: Long,
    /**
     * *Optional*. Unique identifier of the business connection from which the message was received.
     * If non-empty, the message belongs to a chat of the corresponding business account that is
     * independent from any potential bot chat which might share the same identifier.
     */
    @get:JsonProperty("business_connection_id")
    @param:JsonProperty("business_connection_id")
    public val businessConnectionId: String? = null,
    /**
     * Chat the message belongs to
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    override val chat: Chat,
    /**
     * *Optional*. Information about the original message for forwarded messages
     */
    @get:JsonProperty("forward_origin")
    @param:JsonProperty("forward_origin")
    public val forwardOrigin: MessageOrigin? = null,
    /**
     * *Optional*. *True*, if the message is sent to a forum topic
     */
    @get:JsonProperty("is_topic_message")
    @param:JsonProperty("is_topic_message")
    public val isTopicMessage: Boolean? = null,
    /**
     * *Optional*. *True*, if the message is a channel post that was automatically forwarded to the
     * connected discussion group
     */
    @get:JsonProperty("is_automatic_forward")
    @param:JsonProperty("is_automatic_forward")
    public val isAutomaticForward: Boolean? = null,
    /**
     * *Optional*. For replies in the same chat and message thread, the original message. Note that
     * the [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain
     * further *reply_to_message* fields even if it itself is a reply.
     */
    @get:JsonProperty("reply_to_message")
    @param:JsonProperty("reply_to_message")
    public val replyToMessage: Message? = null,
    /**
     * *Optional*. Information about the message that is being replied to, which may come from
     * another chat or forum topic
     */
    @get:JsonProperty("external_reply")
    @param:JsonProperty("external_reply")
    public val externalReply: ExternalReplyInfo? = null,
    /**
     * *Optional*. For replies that quote part of the original message, the quoted part of the
     * message
     */
    @get:JsonProperty("quote")
    @param:JsonProperty("quote")
    public val quote: TextQuote? = null,
    /**
     * *Optional*. For replies to a story, the original story
     */
    @get:JsonProperty("reply_to_story")
    @param:JsonProperty("reply_to_story")
    public val replyToStory: Story? = null,
    /**
     * *Optional*. Identifier of the specific checklist task that is being replied to
     */
    @get:JsonProperty("reply_to_checklist_task_id")
    @param:JsonProperty("reply_to_checklist_task_id")
    public val replyToChecklistTaskId: Long? = null,
    /**
     * *Optional*. Bot through which the message was sent
     */
    @get:JsonProperty("via_bot")
    @param:JsonProperty("via_bot")
    public val viaBot: User? = null,
    /**
     * *Optional*. Date the message was last edited in Unix time
     */
    @get:JsonProperty("edit_date")
    @param:JsonProperty("edit_date")
    public val editDate: Long? = null,
    /**
     * *Optional*. *True*, if the message can't be forwarded
     */
    @get:JsonProperty("has_protected_content")
    @param:JsonProperty("has_protected_content")
    public val hasProtectedContent: Boolean? = null,
    /**
     * *Optional*. *True*, if the message was sent by an implicit action, for example, as an away or
     * a greeting business message, or as a scheduled message
     */
    @get:JsonProperty("is_from_offline")
    @param:JsonProperty("is_from_offline")
    public val isFromOffline: Boolean? = null,
    /**
     * *Optional*. *True*, if the message is a paid post. Note that such posts must not be deleted
     * for 24 hours to receive the payment and can't be edited.
     */
    @get:JsonProperty("is_paid_post")
    @param:JsonProperty("is_paid_post")
    public val isPaidPost: Boolean? = null,
    /**
     * *Optional*. The unique identifier of a media message group this message belongs to
     */
    @get:JsonProperty("media_group_id")
    @param:JsonProperty("media_group_id")
    public val mediaGroupId: String? = null,
    /**
     * *Optional*. Signature of the post author for messages in channels, or the custom title of an
     * anonymous group administrator
     */
    @get:JsonProperty("author_signature")
    @param:JsonProperty("author_signature")
    public val authorSignature: String? = null,
    /**
     * *Optional*. The number of Telegram Stars that were paid by the sender of the message to send
     * it
     */
    @get:JsonProperty("paid_star_count")
    @param:JsonProperty("paid_star_count")
    public val paidStarCount: Int? = null,
    /**
     * *Optional*. For text messages, the actual UTF-8 text of the message
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String? = null,
    /**
     * *Optional*. For text messages, special entities like usernames, URLs, bot commands, etc. that
     * appear in the text
     */
    @get:JsonProperty("entities")
    @param:JsonProperty("entities")
    public val entities: List<MessageEntity>? = null,
    /**
     * *Optional*. Options used for link preview generation for the message, if it is a text message
     * and link preview options were changed
     */
    @get:JsonProperty("link_preview_options")
    @param:JsonProperty("link_preview_options")
    public val linkPreviewOptions: LinkPreviewOptions? = null,
    /**
     * *Optional*. Information about suggested post parameters if the message is a suggested post in
     * a channel direct messages chat. If the message is an approved or declined suggested post, then
     * it can't be edited.
     */
    @get:JsonProperty("suggested_post_info")
    @param:JsonProperty("suggested_post_info")
    public val suggestedPostInfo: SuggestedPostInfo? = null,
    /**
     * *Optional*. Unique identifier of the message effect added to the message
     */
    @get:JsonProperty("effect_id")
    @param:JsonProperty("effect_id")
    public val effectId: String? = null,
    /**
     * *Optional*. Message is an animation, information about the animation. For backward
     * compatibility, when this field is set, the *document* field will also be set
     */
    @get:JsonProperty("animation")
    @param:JsonProperty("animation")
    public val animation: Animation? = null,
    /**
     * *Optional*. Message is an audio file, information about the file
     */
    @get:JsonProperty("audio")
    @param:JsonProperty("audio")
    public val audio: Audio? = null,
    /**
     * *Optional*. Message is a general file, information about the file
     */
    @get:JsonProperty("document")
    @param:JsonProperty("document")
    public val document: Document? = null,
    /**
     * *Optional*. Message contains paid media; information about the paid media
     */
    @get:JsonProperty("paid_media")
    @param:JsonProperty("paid_media")
    public val paidMedia: PaidMediaInfo? = null,
    /**
     * *Optional*. Message is a photo, available sizes of the photo
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>? = null,
    /**
     * *Optional*. Message is a sticker, information about the sticker
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Sticker? = null,
    /**
     * *Optional*. Message is a forwarded story
     */
    @get:JsonProperty("story")
    @param:JsonProperty("story")
    public val story: Story? = null,
    /**
     * *Optional*. Message is a video, information about the video
     */
    @get:JsonProperty("video")
    @param:JsonProperty("video")
    public val video: Video? = null,
    /**
     * *Optional*. Message is a [video
     * note](https://telegram.org/blog/video-messages-and-telescope), information about the video
     * message
     */
    @get:JsonProperty("video_note")
    @param:JsonProperty("video_note")
    public val videoNote: VideoNote? = null,
    /**
     * *Optional*. Message is a voice message, information about the file
     */
    @get:JsonProperty("voice")
    @param:JsonProperty("voice")
    public val voice: Voice? = null,
    /**
     * *Optional*. Caption for the animation, audio, document, paid media, photo, video or voice
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: String? = null,
    /**
     * *Optional*. For messages with a caption, special entities like usernames, URLs, bot commands,
     * etc. that appear in the caption
     */
    @get:JsonProperty("caption_entities")
    @param:JsonProperty("caption_entities")
    public val captionEntities: List<MessageEntity>? = null,
    /**
     * *Optional*. *True*, if the caption must be shown above the message media
     */
    @get:JsonProperty("show_caption_above_media")
    @param:JsonProperty("show_caption_above_media")
    public val showCaptionAboveMedia: Boolean? = null,
    /**
     * *Optional*. *True*, if the message media is covered by a spoiler animation
     */
    @get:JsonProperty("has_media_spoiler")
    @param:JsonProperty("has_media_spoiler")
    public val hasMediaSpoiler: Boolean? = null,
    /**
     * *Optional*. Message is a checklist
     */
    @get:JsonProperty("checklist")
    @param:JsonProperty("checklist")
    public val checklist: Checklist? = null,
    /**
     * *Optional*. Message is a shared contact, information about the contact
     */
    @get:JsonProperty("contact")
    @param:JsonProperty("contact")
    public val contact: Contact? = null,
    /**
     * *Optional*. Message is a dice with random value
     */
    @get:JsonProperty("dice")
    @param:JsonProperty("dice")
    public val dice: Dice? = null,
    /**
     * *Optional*. Message is a game, information about the game. [More about games
     * ](https://core.telegram.org/bots/api/#games)
     */
    @get:JsonProperty("game")
    @param:JsonProperty("game")
    public val game: Game? = null,
    /**
     * *Optional*. Message is a native poll, information about the poll
     */
    @get:JsonProperty("poll")
    @param:JsonProperty("poll")
    public val poll: Poll? = null,
    /**
     * *Optional*. Message is a venue, information about the venue. For backward compatibility, when
     * this field is set, the *location* field will also be set
     */
    @get:JsonProperty("venue")
    @param:JsonProperty("venue")
    public val venue: Venue? = null,
    /**
     * *Optional*. Message is a shared location, information about the location
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location? = null,
    /**
     * *Optional*. New members that were added to the group or supergroup and information about them
     * (the bot itself may be one of these members)
     */
    @get:JsonProperty("new_chat_members")
    @param:JsonProperty("new_chat_members")
    public val newChatMembers: List<User>? = null,
    /**
     * *Optional*. A member was removed from the group, information about them (this member may be
     * the bot itself)
     */
    @get:JsonProperty("left_chat_member")
    @param:JsonProperty("left_chat_member")
    public val leftChatMember: User? = null,
    /**
     * *Optional*. A chat title was changed to this value
     */
    @get:JsonProperty("new_chat_title")
    @param:JsonProperty("new_chat_title")
    public val newChatTitle: String? = null,
    /**
     * *Optional*. A chat photo was change to this value
     */
    @get:JsonProperty("new_chat_photo")
    @param:JsonProperty("new_chat_photo")
    public val newChatPhoto: List<PhotoSize>? = null,
    /**
     * *Optional*. Service message: the chat photo was deleted
     */
    @get:JsonProperty("delete_chat_photo")
    @param:JsonProperty("delete_chat_photo")
    public val deleteChatPhoto: Boolean? = null,
    /**
     * *Optional*. Service message: the group has been created
     */
    @get:JsonProperty("group_chat_created")
    @param:JsonProperty("group_chat_created")
    public val groupChatCreated: Boolean? = null,
    /**
     * *Optional*. Service message: the supergroup has been created. This field can't be received in
     * a message coming through updates, because bot can't be a member of a supergroup when it is
     * created. It can only be found in reply_to_message if someone replies to a very first message in
     * a directly created supergroup.
     */
    @get:JsonProperty("supergroup_chat_created")
    @param:JsonProperty("supergroup_chat_created")
    public val supergroupChatCreated: Boolean? = null,
    /**
     * *Optional*. Service message: the channel has been created. This field can't be received in a
     * message coming through updates, because bot can't be a member of a channel when it is created.
     * It can only be found in reply_to_message if someone replies to a very first message in a
     * channel.
     */
    @get:JsonProperty("channel_chat_created")
    @param:JsonProperty("channel_chat_created")
    public val channelChatCreated: Boolean? = null,
    /**
     * *Optional*. Service message: auto-delete timer settings changed in the chat
     */
    @get:JsonProperty("message_auto_delete_timer_changed")
    @param:JsonProperty("message_auto_delete_timer_changed")
    public val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged? = null,
    /**
     * *Optional*. The group has been migrated to a supergroup with the specified identifier. This
     * number may have more than 32 significant bits and some programming languages may have
     * difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
     * signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    @get:JsonProperty("migrate_to_chat_id")
    @param:JsonProperty("migrate_to_chat_id")
    public val migrateToChatId: Long? = null,
    /**
     * *Optional*. The supergroup has been migrated from a group with the specified identifier. This
     * number may have more than 32 significant bits and some programming languages may have
     * difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
     * signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    @get:JsonProperty("migrate_from_chat_id")
    @param:JsonProperty("migrate_from_chat_id")
    public val migrateFromChatId: Long? = null,
    /**
     * *Optional*. Specified message was pinned. Note that the
     * [Message](https://core.telegram.org/bots/api/#message) object in this field will not contain
     * further *reply_to_message* fields even if it itself is a reply.
     */
    @get:JsonProperty("pinned_message")
    @param:JsonProperty("pinned_message")
    public val pinnedMessage: MaybeInaccessibleMessage? = null,
    /**
     * *Optional*. Message is an invoice for a
     * [payment](https://core.telegram.org/bots/api/#payments), information about the invoice. [More
     * about payments ](https://core.telegram.org/bots/api/#payments)
     */
    @get:JsonProperty("invoice")
    @param:JsonProperty("invoice")
    public val invoice: Invoice? = null,
    /**
     * *Optional*. Message is a service message about a successful payment, information about the
     * payment. [More about payments ](https://core.telegram.org/bots/api/#payments)
     */
    @get:JsonProperty("successful_payment")
    @param:JsonProperty("successful_payment")
    public val successfulPayment: SuccessfulPayment? = null,
    /**
     * *Optional*. Message is a service message about a refunded payment, information about the
     * payment. [More about payments ](https://core.telegram.org/bots/api/#payments)
     */
    @get:JsonProperty("refunded_payment")
    @param:JsonProperty("refunded_payment")
    public val refundedPayment: RefundedPayment? = null,
    /**
     * *Optional*. Service message: users were shared with the bot
     */
    @get:JsonProperty("users_shared")
    @param:JsonProperty("users_shared")
    public val usersShared: UsersShared? = null,
    /**
     * *Optional*. Service message: a chat was shared with the bot
     */
    @get:JsonProperty("chat_shared")
    @param:JsonProperty("chat_shared")
    public val chatShared: ChatShared? = null,
    /**
     * *Optional*. Service message: a regular gift was sent or received
     */
    @get:JsonProperty("gift")
    @param:JsonProperty("gift")
    public val gift: GiftInfo? = null,
    /**
     * *Optional*. Service message: a unique gift was sent or received
     */
    @get:JsonProperty("unique_gift")
    @param:JsonProperty("unique_gift")
    public val uniqueGift: UniqueGiftInfo? = null,
    /**
     * *Optional*. The domain name of the website on which the user has logged in. [More about
     * Telegram Login ](https://core.telegram.org/widgets/login)
     */
    @get:JsonProperty("connected_website")
    @param:JsonProperty("connected_website")
    public val connectedWebsite: String? = null,
    /**
     * *Optional*. Service message: the user allowed the bot to write messages after adding it to
     * the attachment or side menu, launching a Web App from a link, or accepting an explicit request
     * from a Web App sent by the method
     * [requestWriteAccess](https://core.telegram.org/bots/webapps#initializing-mini-apps)
     */
    @get:JsonProperty("write_access_allowed")
    @param:JsonProperty("write_access_allowed")
    public val writeAccessAllowed: WriteAccessAllowed? = null,
    /**
     * *Optional*. Telegram Passport data
     */
    @get:JsonProperty("passport_data")
    @param:JsonProperty("passport_data")
    public val passportData: PassportData? = null,
    /**
     * *Optional*. Service message. A user in the chat triggered another user's proximity alert
     * while sharing Live Location.
     */
    @get:JsonProperty("proximity_alert_triggered")
    @param:JsonProperty("proximity_alert_triggered")
    public val proximityAlertTriggered: ProximityAlertTriggered? = null,
    /**
     * *Optional*. Service message: user boosted the chat
     */
    @get:JsonProperty("boost_added")
    @param:JsonProperty("boost_added")
    public val boostAdded: ChatBoostAdded? = null,
    /**
     * *Optional*. Service message: chat background set
     */
    @get:JsonProperty("chat_background_set")
    @param:JsonProperty("chat_background_set")
    public val chatBackgroundSet: ChatBackground? = null,
    /**
     * *Optional*. Service message: some tasks in a checklist were marked as done or not done
     */
    @get:JsonProperty("checklist_tasks_done")
    @param:JsonProperty("checklist_tasks_done")
    public val checklistTasksDone: ChecklistTasksDone? = null,
    /**
     * *Optional*. Service message: tasks were added to a checklist
     */
    @get:JsonProperty("checklist_tasks_added")
    @param:JsonProperty("checklist_tasks_added")
    public val checklistTasksAdded: ChecklistTasksAdded? = null,
    /**
     * *Optional*. Service message: the price for paid messages in the corresponding direct messages
     * chat of a channel has changed
     */
    @get:JsonProperty("direct_message_price_changed")
    @param:JsonProperty("direct_message_price_changed")
    public val directMessagePriceChanged: DirectMessagePriceChanged? = null,
    /**
     * *Optional*. Service message: forum topic created
     */
    @get:JsonProperty("forum_topic_created")
    @param:JsonProperty("forum_topic_created")
    public val forumTopicCreated: ForumTopicCreated? = null,
    /**
     * *Optional*. Service message: forum topic edited
     */
    @get:JsonProperty("forum_topic_edited")
    @param:JsonProperty("forum_topic_edited")
    public val forumTopicEdited: ForumTopicEdited? = null,
    /**
     * *Optional*. Service message: forum topic closed
     */
    @get:JsonProperty("forum_topic_closed")
    @param:JsonProperty("forum_topic_closed")
    public val forumTopicClosed: ForumTopicClosed? = null,
    /**
     * *Optional*. Service message: forum topic reopened
     */
    @get:JsonProperty("forum_topic_reopened")
    @param:JsonProperty("forum_topic_reopened")
    public val forumTopicReopened: ForumTopicReopened? = null,
    /**
     * *Optional*. Service message: the 'General' forum topic hidden
     */
    @get:JsonProperty("general_forum_topic_hidden")
    @param:JsonProperty("general_forum_topic_hidden")
    public val generalForumTopicHidden: GeneralForumTopicHidden? = null,
    /**
     * *Optional*. Service message: the 'General' forum topic unhidden
     */
    @get:JsonProperty("general_forum_topic_unhidden")
    @param:JsonProperty("general_forum_topic_unhidden")
    public val generalForumTopicUnhidden: GeneralForumTopicUnhidden? = null,
    /**
     * *Optional*. Service message: a scheduled giveaway was created
     */
    @get:JsonProperty("giveaway_created")
    @param:JsonProperty("giveaway_created")
    public val giveawayCreated: GiveawayCreated? = null,
    /**
     * *Optional*. The message is a scheduled giveaway message
     */
    @get:JsonProperty("giveaway")
    @param:JsonProperty("giveaway")
    public val giveaway: Giveaway? = null,
    /**
     * *Optional*. A giveaway with public winners was completed
     */
    @get:JsonProperty("giveaway_winners")
    @param:JsonProperty("giveaway_winners")
    public val giveawayWinners: GiveawayWinners? = null,
    /**
     * *Optional*. Service message: a giveaway without public winners was completed
     */
    @get:JsonProperty("giveaway_completed")
    @param:JsonProperty("giveaway_completed")
    public val giveawayCompleted: GiveawayCompleted? = null,
    /**
     * *Optional*. Service message: the price for paid messages has changed in the chat
     */
    @get:JsonProperty("paid_message_price_changed")
    @param:JsonProperty("paid_message_price_changed")
    public val paidMessagePriceChanged: PaidMessagePriceChanged? = null,
    /**
     * *Optional*. Service message: a suggested post was approved
     */
    @get:JsonProperty("suggested_post_approved")
    @param:JsonProperty("suggested_post_approved")
    public val suggestedPostApproved: SuggestedPostApproved? = null,
    /**
     * *Optional*. Service message: approval of a suggested post has failed
     */
    @get:JsonProperty("suggested_post_approval_failed")
    @param:JsonProperty("suggested_post_approval_failed")
    public val suggestedPostApprovalFailed: SuggestedPostApprovalFailed? = null,
    /**
     * *Optional*. Service message: a suggested post was declined
     */
    @get:JsonProperty("suggested_post_declined")
    @param:JsonProperty("suggested_post_declined")
    public val suggestedPostDeclined: SuggestedPostDeclined? = null,
    /**
     * *Optional*. Service message: payment for a suggested post was received
     */
    @get:JsonProperty("suggested_post_paid")
    @param:JsonProperty("suggested_post_paid")
    public val suggestedPostPaid: SuggestedPostPaid? = null,
    /**
     * *Optional*. Service message: payment for a suggested post was refunded
     */
    @get:JsonProperty("suggested_post_refunded")
    @param:JsonProperty("suggested_post_refunded")
    public val suggestedPostRefunded: SuggestedPostRefunded? = null,
    /**
     * *Optional*. Service message: video chat scheduled
     */
    @get:JsonProperty("video_chat_scheduled")
    @param:JsonProperty("video_chat_scheduled")
    public val videoChatScheduled: VideoChatScheduled? = null,
    /**
     * *Optional*. Service message: video chat started
     */
    @get:JsonProperty("video_chat_started")
    @param:JsonProperty("video_chat_started")
    public val videoChatStarted: VideoChatStarted? = null,
    /**
     * *Optional*. Service message: video chat ended
     */
    @get:JsonProperty("video_chat_ended")
    @param:JsonProperty("video_chat_ended")
    public val videoChatEnded: VideoChatEnded? = null,
    /**
     * *Optional*. Service message: new participants invited to a video chat
     */
    @get:JsonProperty("video_chat_participants_invited")
    @param:JsonProperty("video_chat_participants_invited")
    public val videoChatParticipantsInvited: VideoChatParticipantsInvited? = null,
    /**
     * *Optional*. Service message: data sent by a Web App
     */
    @get:JsonProperty("web_app_data")
    @param:JsonProperty("web_app_data")
    public val webAppData: WebAppData? = null,
    /**
     * *Optional*. Inline keyboard attached to the message. `login_url` buttons are represented as
     * ordinary `url` buttons.
     */
    @get:JsonProperty("reply_markup")
    @param:JsonProperty("reply_markup")
    public val replyMarkup: InlineKeyboardMarkup? = null,
) : MaybeInaccessibleMessage
