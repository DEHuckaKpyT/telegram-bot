package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Long

/**
 * This [object](https://core.telegram.org/bots/api/#available-types) represents an incoming update.
 *  
 * At most **one** of the optional parameters can be present in any given update.
 *
 * @see [Update] (https://core.telegram.org/bots/api/#update)
 *
 * @author KScript
 *
 * @param updateId The update's unique identifier. Update identifiers start from a certain positive
 * number and increase sequentially. This identifier becomes especially handy if you're using
 * [webhooks](https://core.telegram.org/bots/api/#setwebhook), since it allows you to ignore repeated
 * updates or to restore the correct update sequence, should they get out of order. If there are no new
 * updates for at least a week, then identifier of the next update will be chosen randomly instead of
 * sequentially.
 * @param message *Optional*. New incoming message of any kind - text, photo, sticker, etc.
 * @param editedMessage *Optional*. New version of a message that is known to the bot and was
 * edited. This update may at times be triggered by changes to message fields that are either
 * unavailable or not actively used by your bot.
 * @param channelPost *Optional*. New incoming channel post of any kind - text, photo, sticker, etc.
 * @param editedChannelPost *Optional*. New version of a channel post that is known to the bot and
 * was edited. This update may at times be triggered by changes to message fields that are either
 * unavailable or not actively used by your bot.
 * @param businessConnection *Optional*. The bot was connected to or disconnected from a business
 * account, or a user edited an existing connection with the bot
 * @param businessMessage *Optional*. New message from a connected business account
 * @param editedBusinessMessage *Optional*. New version of a message from a connected business
 * account
 * @param deletedBusinessMessages *Optional*. Messages were deleted from a connected business
 * account
 * @param messageReaction *Optional*. A reaction to a message was changed by a user. The bot must be
 * an administrator in the chat and must explicitly specify `"message_reaction"` in the list of
 * *allowed_updates* to receive these updates. The update isn't received for reactions set by bots.
 * @param messageReactionCount *Optional*. Reactions to a message with anonymous reactions were
 * changed. The bot must be an administrator in the chat and must explicitly specify
 * `"message_reaction_count"` in the list of *allowed_updates* to receive these updates. The updates
 * are grouped and can be sent with delay up to a few minutes.
 * @param inlineQuery *Optional*. New incoming
 * [inline](https://core.telegram.org/bots/api/#inline-mode) query
 * @param chosenInlineResult *Optional*. The result of an
 * [inline](https://core.telegram.org/bots/api/#inline-mode) query that was chosen by a user and sent
 * to their chat partner. Please see our documentation on the [feedback
 * collecting](https://core.telegram.org/bots/inline#collecting-feedback) for details on how to enable
 * these updates for your bot.
 * @param callbackQuery *Optional*. New incoming callback query
 * @param shippingQuery *Optional*. New incoming shipping query. Only for invoices with flexible
 * price
 * @param preCheckoutQuery *Optional*. New incoming pre-checkout query. Contains full information
 * about checkout
 * @param poll *Optional*. New poll state. Bots receive only updates about manually stopped polls
 * and polls, which are sent by the bot
 * @param pollAnswer *Optional*. A user changed their answer in a non-anonymous poll. Bots receive
 * new votes only in polls that were sent by the bot itself.
 * @param myChatMember *Optional*. The bot's chat member status was updated in a chat. For private
 * chats, this update is received only when the bot is blocked or unblocked by the user.
 * @param chatMember *Optional*. A chat member's status was updated in a chat. The bot must be an
 * administrator in the chat and must explicitly specify `"chat_member"` in the list of
 * *allowed_updates* to receive these updates.
 * @param chatJoinRequest *Optional*. A request to join the chat has been sent. The bot must have
 * the *can_invite_users* administrator right in the chat to receive these updates.
 * @param chatBoost *Optional*. A chat boost was added or changed. The bot must be an administrator
 * in the chat to receive these updates.
 * @param removedChatBoost *Optional*. A boost was removed from a chat. The bot must be an
 * administrator in the chat to receive these updates.
 */
public data class Update(
    /**
     * The update's unique identifier. Update identifiers start from a certain positive number and
     * increase sequentially. This identifier becomes especially handy if you're using
     * [webhooks](https://core.telegram.org/bots/api/#setwebhook), since it allows you to ignore
     * repeated updates or to restore the correct update sequence, should they get out of order. If
     * there are no new updates for at least a week, then identifier of the next update will be chosen
     * randomly instead of sequentially.
     */
    @get:JsonProperty("update_id")
    @param:JsonProperty("update_id")
    public val updateId: Long,
    /**
     * *Optional*. New incoming message of any kind - text, photo, sticker, etc.
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    public val message: Message? = null,
    /**
     * *Optional*. New version of a message that is known to the bot and was edited. This update may
     * at times be triggered by changes to message fields that are either unavailable or not actively
     * used by your bot.
     */
    @get:JsonProperty("edited_message")
    @param:JsonProperty("edited_message")
    public val editedMessage: Message? = null,
    /**
     * *Optional*. New incoming channel post of any kind - text, photo, sticker, etc.
     */
    @get:JsonProperty("channel_post")
    @param:JsonProperty("channel_post")
    public val channelPost: Message? = null,
    /**
     * *Optional*. New version of a channel post that is known to the bot and was edited. This
     * update may at times be triggered by changes to message fields that are either unavailable or not
     * actively used by your bot.
     */
    @get:JsonProperty("edited_channel_post")
    @param:JsonProperty("edited_channel_post")
    public val editedChannelPost: Message? = null,
    /**
     * *Optional*. The bot was connected to or disconnected from a business account, or a user
     * edited an existing connection with the bot
     */
    @get:JsonProperty("business_connection")
    @param:JsonProperty("business_connection")
    public val businessConnection: BusinessConnection? = null,
    /**
     * *Optional*. New message from a connected business account
     */
    @get:JsonProperty("business_message")
    @param:JsonProperty("business_message")
    public val businessMessage: Message? = null,
    /**
     * *Optional*. New version of a message from a connected business account
     */
    @get:JsonProperty("edited_business_message")
    @param:JsonProperty("edited_business_message")
    public val editedBusinessMessage: Message? = null,
    /**
     * *Optional*. Messages were deleted from a connected business account
     */
    @get:JsonProperty("deleted_business_messages")
    @param:JsonProperty("deleted_business_messages")
    public val deletedBusinessMessages: BusinessMessagesDeleted? = null,
    /**
     * *Optional*. A reaction to a message was changed by a user. The bot must be an administrator
     * in the chat and must explicitly specify `"message_reaction"` in the list of *allowed_updates* to
     * receive these updates. The update isn't received for reactions set by bots.
     */
    @get:JsonProperty("message_reaction")
    @param:JsonProperty("message_reaction")
    public val messageReaction: MessageReactionUpdated? = null,
    /**
     * *Optional*. Reactions to a message with anonymous reactions were changed. The bot must be an
     * administrator in the chat and must explicitly specify `"message_reaction_count"` in the list of
     * *allowed_updates* to receive these updates. The updates are grouped and can be sent with delay
     * up to a few minutes.
     */
    @get:JsonProperty("message_reaction_count")
    @param:JsonProperty("message_reaction_count")
    public val messageReactionCount: MessageReactionCountUpdated? = null,
    /**
     * *Optional*. New incoming [inline](https://core.telegram.org/bots/api/#inline-mode) query
     */
    @get:JsonProperty("inline_query")
    @param:JsonProperty("inline_query")
    public val inlineQuery: InlineQuery? = null,
    /**
     * *Optional*. The result of an [inline](https://core.telegram.org/bots/api/#inline-mode) query
     * that was chosen by a user and sent to their chat partner. Please see our documentation on the
     * [feedback collecting](https://core.telegram.org/bots/inline#collecting-feedback) for details on
     * how to enable these updates for your bot.
     */
    @get:JsonProperty("chosen_inline_result")
    @param:JsonProperty("chosen_inline_result")
    public val chosenInlineResult: ChosenInlineResult? = null,
    /**
     * *Optional*. New incoming callback query
     */
    @get:JsonProperty("callback_query")
    @param:JsonProperty("callback_query")
    public val callbackQuery: CallbackQuery? = null,
    /**
     * *Optional*. New incoming shipping query. Only for invoices with flexible price
     */
    @get:JsonProperty("shipping_query")
    @param:JsonProperty("shipping_query")
    public val shippingQuery: ShippingQuery? = null,
    /**
     * *Optional*. New incoming pre-checkout query. Contains full information about checkout
     */
    @get:JsonProperty("pre_checkout_query")
    @param:JsonProperty("pre_checkout_query")
    public val preCheckoutQuery: PreCheckoutQuery? = null,
    /**
     * *Optional*. New poll state. Bots receive only updates about manually stopped polls and polls,
     * which are sent by the bot
     */
    @get:JsonProperty("poll")
    @param:JsonProperty("poll")
    public val poll: Poll? = null,
    /**
     * *Optional*. A user changed their answer in a non-anonymous poll. Bots receive new votes only
     * in polls that were sent by the bot itself.
     */
    @get:JsonProperty("poll_answer")
    @param:JsonProperty("poll_answer")
    public val pollAnswer: PollAnswer? = null,
    /**
     * *Optional*. The bot's chat member status was updated in a chat. For private chats, this
     * update is received only when the bot is blocked or unblocked by the user.
     */
    @get:JsonProperty("my_chat_member")
    @param:JsonProperty("my_chat_member")
    public val myChatMember: ChatMemberUpdated? = null,
    /**
     * *Optional*. A chat member's status was updated in a chat. The bot must be an administrator in
     * the chat and must explicitly specify `"chat_member"` in the list of *allowed_updates* to receive
     * these updates.
     */
    @get:JsonProperty("chat_member")
    @param:JsonProperty("chat_member")
    public val chatMember: ChatMemberUpdated? = null,
    /**
     * *Optional*. A request to join the chat has been sent. The bot must have the
     * *can_invite_users* administrator right in the chat to receive these updates.
     */
    @get:JsonProperty("chat_join_request")
    @param:JsonProperty("chat_join_request")
    public val chatJoinRequest: ChatJoinRequest? = null,
    /**
     * *Optional*. A chat boost was added or changed. The bot must be an administrator in the chat
     * to receive these updates.
     */
    @get:JsonProperty("chat_boost")
    @param:JsonProperty("chat_boost")
    public val chatBoost: ChatBoostUpdated? = null,
    /**
     * *Optional*. A boost was removed from a chat. The bot must be an administrator in the chat to
     * receive these updates.
     */
    @get:JsonProperty("removed_chat_boost")
    @param:JsonProperty("removed_chat_boost")
    public val removedChatBoost: ChatBoostRemoved? = null,
)
