package io.github.dehuckakpyt.telegrambot.handling

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.model.type.*
import io.github.dehuckakpyt.telegrambot.resolver.EventUpdateResolver
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.template.Templater


/**
 * Created on 31.01.2024.
 *<p>
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

    suspend fun next(userId: Long, step: String?, content: String? = null): Unit {
        chainSource.save(userId, userId, step, content)
    }

    suspend fun next(chatId: Long, userId: Long, step: String?, content: String? = null): Unit {
        chainSource.save(chatId, userId, step, content)
    }
}