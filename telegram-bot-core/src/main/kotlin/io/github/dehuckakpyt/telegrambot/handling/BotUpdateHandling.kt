package io.github.dehuckakpyt.telegrambot.handling

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.model.telegram.*
import io.github.dehuckakpyt.telegrambot.resolver.EventUpdateResolver
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 31.01.2024.
 *
 * Class for creating handlers on any update.
 *
 * This handlers will be invoked before dialog handlers (BotHandling).
 *
 * @see io.github.dehuckakpyt.telegrambot.handling.BotHandling
 *
 * @author Denis Matytsin
 */
class BotUpdateHandling internal constructor(
    public val bot: TelegramBot,
    private val resolver: EventUpdateResolver,
    private val chainSource: ChainSource,
    templater: Templater,
    buttonFactory: ButtonFactory,
) : Templater by templater,
    ButtonFactory by buttonFactory {

    fun message(block: suspend Message.() -> Unit): Unit {
        resolver.message = block
    }

    fun editedMessage(block: suspend Message.() -> Unit): Unit {
        resolver.editedMessage = block
    }

    fun channelPost(block: suspend Message.() -> Unit): Unit {
        resolver.channelPost = block
    }

    fun editedChannelPost(block: suspend Message.() -> Unit): Unit {
        resolver.editedChannelPost = block
    }

    fun messageReaction(block: suspend MessageReactionUpdated.() -> Unit): Unit {
        resolver.messageReaction = block
    }

    fun messageReactionCount(block: suspend MessageReactionCountUpdated.() -> Unit): Unit {
        resolver.messageReactionCount = block
    }

    fun inlineQuery(block: suspend InlineQuery.() -> Unit): Unit {
        resolver.inlineQuery = block
    }

    fun chosenInlineResult(block: suspend ChosenInlineResult.() -> Unit): Unit {
        resolver.chosenInlineResult = block
    }

    fun callbackQuery(block: suspend CallbackQuery.() -> Unit): Unit {
        resolver.callbackQuery = block
    }

    fun shippingQuery(block: suspend ShippingQuery.() -> Unit): Unit {
        resolver.shippingQuery = block
    }

    fun preCheckoutQuery(block: suspend PreCheckoutQuery.() -> Unit): Unit {
        resolver.preCheckoutQuery = block
    }

    fun poll(block: suspend Poll.() -> Unit): Unit {
        resolver.poll = block
    }

    fun pollAnswer(block: suspend PollAnswer.() -> Unit): Unit {
        resolver.pollAnswer = block
    }

    fun myChatMember(block: suspend ChatMemberUpdated.() -> Unit): Unit {
        resolver.myChatMember = block
    }

    fun chatMember(block: suspend ChatMemberUpdated.() -> Unit): Unit {
        resolver.chatMember = block
    }

    fun chatJoinRequest(block: suspend ChatJoinRequest.() -> Unit): Unit {
        resolver.chatJoinRequest = block
    }

    fun chatBoost(block: suspend ChatBoostUpdated.() -> Unit): Unit {
        resolver.chatBoost = block
    }

    fun removedChatBoost(block: suspend ChatBoostRemoved.() -> Unit): Unit {
        resolver.removedChatBoost = block
    }

    /**
     * Set the next step in the dialog.
     *
     * @param userId which user in chat to set step (in private chats equals to chatId)
     * @param step name of the next step for selected dialog
     * @param content stringified object for transfer (defaults json from JsonContentConverter)
     *
     * @see io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
     */
    suspend fun next(userId: Long, step: String?, content: String? = null): Unit {
        chainSource.save(userId, userId, step, content)
    }

    /**
     * Set the next step in the dialog.
     *
     * @param chatId which chat to set step
     * @param userId which user in chat to set step (in private chats equals to chatId)
     * @param step name of the next step for selected dialog
     * @param content stringified object for transfer (defaults json from JsonContentConverter)
     *
     * @see io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
     */
    suspend fun next(chatId: Long, userId: Long, step: String?, content: String? = null): Unit {
        chainSource.save(chatId, userId, step, content)
    }
}