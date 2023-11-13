package io.github.dehuckakpyt.telegrambot.resolver

import com.dehucka.microservice.exception.CustomException
import com.dehucka.microservice.logger.Logging
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.elbekd.bot.types.Update
import com.elbekd.bot.types.UpdateMessage
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.container.*
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.exception.chat.PrivateChatException
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.Templating
import io.github.dehuckakpyt.telegrambot.template.whenKnownErrorTemplate
import io.github.dehuckakpyt.telegrambot.template.whenUnknownErrorTemplate
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


/**
 * Created on 12.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class UpdateResolver(
    private val contentConverter: ContentConverter,
    private val bot: TelegramBot,
    private val chainResolver: ChainResolver,
    private val username: String,
) : KoinComponent, Templating, Logging {

    private val callbackSerializer = get<CallbackSerializer>()
    private val chainSource = get<ChainSource>()
    private val messageSource = get<MessageSource>()

    init {
        bot.onCallbackQuery { callbackQuery ->
            processCallback(callbackQuery)
        }

        bot.onAnyUpdate { update ->
            processUpdate(update)
        }
    }

    private suspend fun processUpdate(update: Update) {
        if (update !is UpdateMessage) return

        val message = update.message
        val from = message.from
        val text = message.text
        val chatId = message.chatId

        if (from == null) {
            logger.warn("Don't expect message without fromId.\nchatId = '$chatId'\ntext = $text")
            return
        }

        messageSource.save(chatId, from.id, message.messageId, text)

        tryExecute(chatId) {
            CommandMassageContainer.fetchCommand(text, username)?.let {
                processCommand(it, message)
            } ?: processMessage(message)
        }
    }

    private suspend fun processCommand(command: String, message: Message) = with(message) {
        chainResolver.getCommand(command)
            .invoke(CommandMassageContainer(chatId, message, chainSource, contentConverter, bot))
    }

    private suspend fun processMessage(message: Message) = with(message) {
        val chain = chainSource.get(chatId, from!!.id)
        val factory = message.containerFactory
        val action = chainResolver.getStep(chain?.step, factory.type)

        action.invoke(factory.create(chatId, message, chain!!.content, chainSource, contentConverter, bot))
    }

    private suspend fun processCallback(callback: CallbackQuery) = with(callback) {
        val data = data ?: return

        tryExecute(chatId) {
            val (callbackName, callbackContent) = callbackSerializer.fromCallback(data)

            chainResolver.getCallback(callbackName)?.invoke(
                CallbackMassageContainer(chatId, callback, callbackContent, chainSource, contentConverter, bot)
            )
        }
    }

    private suspend fun tryExecute(chatId: Long, block: suspend () -> Unit) {
        try {
            block()
        } catch (ex: PrivateChatException) {
            if (chatId > 0) {// если это личный чат
                bot.sendMessage(chatId, whenKnownErrorTemplate with ("message" to ex.localizedMessage))
            }
        } catch (ex: ChatException) {
            bot.sendMessage(chatId, whenKnownErrorTemplate with ("message" to ex.localizedMessage))
        } catch (ex: CustomException) {
            bot.sendMessage(chatId, whenUnknownErrorTemplate with ("message" to ex.localizedMessage))
        } catch (throwable: Throwable) {
            logger.error("Unexpected error while handling message in chat $chatId", throwable)
            bot.sendMessage(chatId, whenUnknownErrorTemplate)
        }
    }

    private val Message.containerFactory: MessageContainerFactory
        get() = when {
            TextMassageContainer.condition(this) -> TextMassageContainer.Companion
            AudioMassageContainer.condition(this) -> AudioMassageContainer.Companion
            VoiceMessageContainer.condition(this) -> VoiceMessageContainer.Companion
            ContactMessageContainer.condition(this) -> ContactMessageContainer.Companion
            DocumentMessageContainer.condition(this) -> DocumentMessageContainer.Companion
            PhotoMassageContainer.condition(this) -> PhotoMassageContainer.Companion
            else -> throw CustomException("Такой тип сообщения ещё не поддерживается.")
        }
}