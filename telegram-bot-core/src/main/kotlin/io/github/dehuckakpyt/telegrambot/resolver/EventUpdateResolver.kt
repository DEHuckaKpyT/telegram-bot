package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.telegram.*
import org.slf4j.LoggerFactory


/**
 * Created on 31.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class EventUpdateResolver {

    private val logger = LoggerFactory.getLogger(javaClass)

    internal var message: (suspend Message.() -> Unit)? = null
    internal var editedMessage: (suspend Message.() -> Unit)? = null
    internal var channelPost: (suspend Message.() -> Unit)? = null
    internal var editedChannelPost: (suspend Message.() -> Unit)? = null
    internal var messageReaction: (suspend MessageReactionUpdated.() -> Unit)? = null
    internal var messageReactionCount: (suspend MessageReactionCountUpdated.() -> Unit)? = null
    internal var inlineQuery: (suspend InlineQuery.() -> Unit)? = null
    internal var chosenInlineResult: (suspend ChosenInlineResult.() -> Unit)? = null
    internal var callbackQuery: (suspend CallbackQuery.() -> Unit)? = null
    internal var shippingQuery: (suspend ShippingQuery.() -> Unit)? = null
    internal var preCheckoutQuery: (suspend PreCheckoutQuery.() -> Unit)? = null
    internal var poll: (suspend Poll.() -> Unit)? = null
    internal var pollAnswer: (suspend PollAnswer.() -> Unit)? = null
    internal var myChatMember: (suspend ChatMemberUpdated.() -> Unit)? = null
    internal var chatMember: (suspend ChatMemberUpdated.() -> Unit)? = null
    internal var chatJoinRequest: (suspend ChatJoinRequest.() -> Unit)? = null
    internal var chatBoost: (suspend ChatBoostUpdated.() -> Unit)? = null
    internal var removedChatBoost: (suspend ChatBoostRemoved.() -> Unit)? = null

    suspend fun processUpdate(update: Update): Unit {
        try {
            when {
                update.message != null -> message?.invoke(update.message)
                update.editedMessage != null -> editedMessage?.invoke(update.editedMessage)
                update.channelPost != null -> channelPost?.invoke(update.channelPost)
                update.editedChannelPost != null -> editedChannelPost?.invoke(update.editedChannelPost)
                update.messageReaction != null -> messageReaction?.invoke(update.messageReaction)
                update.messageReactionCount != null -> messageReactionCount?.invoke(update.messageReactionCount)
                update.inlineQuery != null -> inlineQuery?.invoke(update.inlineQuery)
                update.chosenInlineResult != null -> chosenInlineResult?.invoke(update.chosenInlineResult)
                update.callbackQuery != null -> callbackQuery?.invoke(update.callbackQuery)
                update.shippingQuery != null -> shippingQuery?.invoke(update.shippingQuery)
                update.preCheckoutQuery != null -> preCheckoutQuery?.invoke(update.preCheckoutQuery)
                update.poll != null -> poll?.invoke(update.poll)
                update.pollAnswer != null -> pollAnswer?.invoke(update.pollAnswer)
                update.myChatMember != null -> myChatMember?.invoke(update.myChatMember)
                update.chatMember != null -> chatMember?.invoke(update.chatMember)
                update.chatJoinRequest != null -> chatJoinRequest?.invoke(update.chatJoinRequest)
                update.chatBoost != null -> chatBoost?.invoke(update.chatBoost)
                update.removedChatBoost != null -> removedChatBoost?.invoke(update.removedChatBoost)
            }
        } catch (throwable: Throwable) {
            logger.error("Failed to handle update.", throwable)
        }
    }

    internal val allowedUpdates: Set<String>
        get() = buildSet {
            if (message != null) add("message")
            if (editedMessage != null) add("edited_message")
            if (channelPost != null) add("channel_post")
            if (editedChannelPost != null) add("edited_channel_post")
            if (messageReaction != null) add("message_reaction")
            if (messageReactionCount != null) add("message_reaction_count")
            if (inlineQuery != null) add("inline_query")
            if (chosenInlineResult != null) add("chosen_inline_query")
            if (callbackQuery != null) add("callback_query")
            if (shippingQuery != null) add("shipping_query")
            if (preCheckoutQuery != null) add("pre_checkout_query")
            if (poll != null) add("poll")
            if (pollAnswer != null) add("poll_answer")
            if (myChatMember != null) add("my_chat_member")
            if (chatMember != null) add("chat_member")
            if (chatJoinRequest != null) add("chat_join_request")
            if (chatBoost != null) add("chat_boost")
            if (removedChatBoost != null) add("removed_chat_boost")
        }
}