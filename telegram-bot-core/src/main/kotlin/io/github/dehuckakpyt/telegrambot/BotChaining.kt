package io.github.dehuckakpyt.telegrambot

import com.dehucka.microservice.exception.CustomException
import com.dehucka.microservice.logger.Logging
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.elbekd.bot.types.ParseMode.MarkdownV2
import com.elbekd.bot.types.Update
import com.elbekd.bot.types.UpdateMessage
import io.github.dehuckakpyt.telegrambot.container.*
import io.github.dehuckakpyt.telegrambot.container.CommandMassageContainer.Companion.fetchCommand
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.ext.callbackContent
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.template.*
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance


/**
 * Created on 21.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotChaining(
    val application: Application,
    private val bot: TelegramBot,
    private val username: String
) : KoinComponent, Templating, Logging {

    val callbackContentSource = get<CallbackContentSource>()
    val chainSource = get<ChainSource>()
    val messageSource = get<MessageSource>()

    val callbackDataDelimiter: Char = '|'

    protected val actionByCommand: MutableMap<String, suspend CommandMassageContainer.() -> Unit> = hashMapOf()
    protected val actionByStep: MutableMap<String, MutableMap<KClass<out MassageContainer>, suspend MassageContainer.() -> Unit>> =
        hashMapOf()
    protected val actionByCallback: MutableMap<String, suspend CallbackMassageContainer.() -> Unit> = hashMapOf()

    private val whenCommandNotFound: suspend (Long, String) -> Unit = { chatId, command ->
        bot.sendMessage(chatId, whenCommandNotFoundTemplate with ("command" to command), parseMode = MarkdownV2)
    }
    private val whenKnownError: suspend (Long, String) -> Unit = { chatId, message ->
        bot.sendMessage(chatId, whenKnownErrorTemplate with ("message" to message))
    }
    private val whenUnknownError: suspend (Long) -> Unit = { chatId ->
        if (chatId > 0) {// если это личный чат
            bot.sendMessage(chatId, whenUnknownErrorTemplate)
        }
    }
    private val whenUnexpectedMessageType: suspend (Long, Set<KClass<out MassageContainer>>) -> Unit =
        { chatId, expectedMessageTypes ->
            val expectedMessageNames = expectedMessageTypes.joinToString(", ") {
                (it.companionObjectInstance as MessageContainerFactory).typeName
            }
            bot.sendMessage(
                chatId,
                whenUnexpectedMessageTypeTemplate with ("expectedMessageNames" to expectedMessageNames)
            )
        }
    private val whenStepNotFound: suspend (Long) -> Unit = { chatId ->
        if (chatId > 0) {// если это личный чат
            bot.sendMessage(chatId, whenStepNotFoundTemplate)
        }
    }

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
        val text = message.text
        val chatId = message.chatId

        tryExecute(chatId) {
            messageSource.save(chatId, message.from?.id, message.messageId, text)

            fetchCommand(text, username)?.let {
                processCommand(it, message)
            } ?: processMessage(message)
        }
    }

    private suspend fun processCommand(command: String, message: Message) = with(message) {
        actionByCommand[command]
            ?.invoke(CommandMassageContainer(chatId, message, chainSource, bot))
            ?: whenCommandNotFound(chatId, command)
    }

    private suspend fun processMessage(message: Message) = with(message) {
        val chainLink = chainSource.get(chatId, from?.id)

        val step = chainLink?.step ?: let {
            whenStepNotFound(chatId)
            return
        }

        val actionByMessageType = actionByStep[step] ?: let {
            whenStepNotFound(chatId)
            return
        }

        val factory = message.containerFactory

        actionByMessageType[factory.type]?.invoke(
            factory.create(chatId, message, chainLink.content, chainSource, bot)
        ) ?: whenUnexpectedMessageType(chatId, actionByMessageType.keys)
    }

    private suspend fun processCallback(callback: CallbackQuery) = with(callback) {
        val data = data ?: return

        val indexOfDelimiter = data.indexOf(callbackDataDelimiter)
        if (indexOfDelimiter == -1) return

        val callbackName = data.substring(0, indexOfDelimiter)
        val callbackContent = callbackContent(data.substring(indexOfDelimiter + 1))

        tryExecute(chatId) {
            actionByCallback[callbackName]?.invoke(
                CallbackMassageContainer(chatId, callback, callbackContent, chainSource, bot)
            )
        }
    }

    private suspend fun tryExecute(chatId: Long, block: suspend () -> Unit) {
        try {
            block()
        } catch (exc: CustomException) {
            whenKnownError(chatId, exc.localizedMessage)
        } catch (throwable: Throwable) {
            logger.error("Unexpected error while handling message in chat $chatId", throwable)
            whenUnknownError(chatId)
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