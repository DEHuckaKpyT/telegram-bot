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
)

public sealed class Update {
    public abstract val updateId: Int
}

public data class UpdateMessage(
    override val updateId: Int,
    val message: Message
) : Update()

public data class UpdateEditedMessage(
    override val updateId: Int,
    val editedMessage: Message
) : Update()

public data class UpdateChannelPost(
    override val updateId: Int,
    val channelPost: Message
) : Update()

public data class UpdateEditedChannelPost(
    override val updateId: Int,
    val editedChannelPost: Message
) : Update()

public data class UpdateInlineQuery(
    override val updateId: Int,
    val inlineQuery: InlineQuery
) : Update()

public data class UpdateChosenInlineResult(
    override val updateId: Int,
    val chosenInlineResult: ChosenInlineResult
) : Update()

public data class UpdateCallbackQuery(
    override val updateId: Int,
    val callbackQuery: CallbackQuery
) : Update()

public data class UpdateShippingQuery(
    override val updateId: Int,
    val shippingQuery: ShippingQuery
) : Update()

public data class UpdatePreCheckoutQuery(
    override val updateId: Int,
    val preCheckoutQuery: PreCheckoutQuery
) : Update()

public data class UpdatePoll(
    override val updateId: Int,
    val poll: Poll
) : Update()

public data class UpdatePollAnswer(
    override val updateId: Int,
    val pollAnswer: PollAnswer
) : Update()

public data class UpdateMyChatMember(
    override val updateId: Int,
    val myChatMember: ChatMemberUpdated
) : Update()

public data class UpdateChatMember(
    override val updateId: Int,
    val chatMember: ChatMemberUpdated
) : Update()

public data class UpdateChatJoinRequest(
    override val updateId: Int,
    val chatJoinRequest: ChatJoinRequest
) : Update()