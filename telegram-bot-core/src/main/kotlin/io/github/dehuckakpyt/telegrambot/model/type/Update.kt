package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
data class UpdateResponse(
    @param:JsonProperty("update_id") val updateId: Int,
    @param:JsonProperty("message") val message: Message? = null,
    @param:JsonProperty("edited_message") val editedMessage: Message? = null,
    @param:JsonProperty("channel_post") val channelPost: Message? = null,
    @param:JsonProperty("edited_channel_post") val editedChannelPost: Message? = null,
    @param:JsonProperty("message_reaction") val messageReaction: MessageReactionUpdated? = null,
    @param:JsonProperty("message_reaction_count ") val messageReactionCount : MessageReactionCountUpdated? = null,
    @param:JsonProperty("inline_query") val inlineQuery: InlineQuery? = null,
    @param:JsonProperty("chosen_inline_result") val chosenInlineResult: ChosenInlineResult? = null,
    @param:JsonProperty("callback_query") val callbackQuery: CallbackQuery? = null,
    @param:JsonProperty("shipping_query") val shippingQuery: ShippingQuery? = null,
    @param:JsonProperty("pre_checkout_query") val preCheckoutQuery: PreCheckoutQuery? = null,
    @param:JsonProperty("poll") val poll: Poll? = null,
    @param:JsonProperty("poll_answer") val pollAnswer: PollAnswer? = null,
    @param:JsonProperty("my_chat_member") val myChatMember: ChatMemberUpdated? = null,
    @param:JsonProperty("chat_member") val chatMember: ChatMemberUpdated? = null,
    @param:JsonProperty("chat_join_request") val chatJoinRequest: ChatJoinRequest? = null,
    @param:JsonProperty("chat_boost") val chatBoost: ChatBoostUpdated? = null,
    @param:JsonProperty("removed_chat_boost") val removedChatBoost: ChatBoostRemoved? = null,
)