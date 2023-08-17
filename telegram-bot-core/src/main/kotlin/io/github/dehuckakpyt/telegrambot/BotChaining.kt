package io.github.dehuckakpyt.telegrambot

import com.dehucka.microservice.exception.CustomException
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.elbekd.bot.types.ParseMode.MarkdownV2
import com.elbekd.bot.types.Update
import com.elbekd.bot.types.UpdateMessage
import freemarker.template.Configuration
import freemarker.template.Template
import io.github.dehuckakpyt.telegrambot.container.*
import io.github.dehuckakpyt.telegrambot.container.CommandMassageContainer.Companion.fetchCommand
import io.github.dehuckakpyt.telegrambot.container.factory.MessageContainerFactory
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.ktor.server.application.*
import java.io.StringWriter
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance


/**
 * Created on 21.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class BotChaining(
    private val application: Application,
    private val bot: TelegramBot,
    private val username: String,
    private val messageSource: MessageSource,
    private val chainSource: ChainSource,
    val callbackContentSource: CallbackContentSource,
    private val templateConfiguration: Configuration
) {

    val callbackDataDelimiter: Char = '|'

    protected val actionByCommand: MutableMap<String, suspend CommandMassageContainer.() -> Unit> = hashMapOf()
    protected val actionByStep: MutableMap<String, MutableMap<KClass<out MassageContainer>, suspend MassageContainer.() -> Unit>> =
        hashMapOf()
    protected val actionByCallback: MutableMap<String, suspend CallbackMassageContainer.() -> Unit> = hashMapOf()

    private val whenCommandNotFound: suspend (Long, String) -> Unit = { chatId, command ->
        bot.sendMessage(
            chatId = chatId,
            text = "Введена неизвестная команда `$command`\\. Посмотреть возможные действия можно, вызвав команду /help\\.",
            parseMode = MarkdownV2
        )
    }
    private val whenKnownError: suspend (Long, String) -> Unit = { chatId, message ->
        bot.sendMessage(chatId, text = message)
    }
    private val whenUnknownError: suspend (Long) -> Unit = { chatId ->
        bot.sendMessage(chatId, "Произошла непредвиденная ошибка. Обратитесь к разработчику.")
    }
    private val whenUnexpectedMessageType: suspend (Long, Set<KClass<out MassageContainer>>) -> Unit =
        { chatId, expectedMessageTypes ->
            val expectedMessageNames = expectedMessageTypes.joinToString(", ") {
                (it.companionObjectInstance as MessageContainerFactory).typeName
            }
            bot.sendMessage(
                chatId,
                "Неподходящий тип сообщения.\nОжидаемые типы: $expectedMessageNames"
            )
        }
    private val whenStepNotFound: suspend (Long) -> Unit = { chatId ->
        bot.sendMessage(
            chatId,
            "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help."
        )
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
        val chainLink = chainSource.get(chatId)
        val step = chainLink.step ?: whenStepNotFound(chatId)

        val actionByMessageType = actionByStep[step] ?: let {
            whenStepNotFound(chatId)
            return
        }

        val factory = message.containerFactory
        actionByMessageType[factory.type]?.invoke(
            factory.create(
                chatId,
                message,
                chainLink.content,
                chainSource,
                bot
            )
        ) ?: whenUnexpectedMessageType(chatId, actionByMessageType.keys)
    }

    private suspend fun processCallback(callback: CallbackQuery) = with(callback) {
        val data = data ?: return

        val indexOfDelimiter = data.indexOf(callbackDataDelimiter)
        if (indexOfDelimiter == -1) return

        val callbackName = data.substring(0, indexOfDelimiter)
        val callbackContent = data.substring(indexOfDelimiter + 1).takeIf { it.isNotBlank() }

        tryExecute(chatId) {
            actionByCallback[callbackName]?.invoke(
                CallbackMassageContainer(
                    chatId,
                    callback,
                    callbackContent,
                    chainSource,
                    bot
                )
            )
        }
    }

    infix fun String.with(instance: Any): String {
        val writer = StringWriter()

        try {
            val markerTemplate = Template("template", this, templateConfiguration)
            markerTemplate.process(instance, writer)
        } catch (exc: Exception) {
            throw RuntimeException(exc)
        }

        return writer.toString()
    }

    private suspend fun tryExecute(chatId: Long, block: suspend () -> Unit) {
        try {
            block()
        } catch (exc: CustomException) {
            whenKnownError(chatId, exc.localizedMessage)
        } catch (throwable: Throwable) {
            application.log.error("Unexpected error while handling message in chat $chatId", throwable)
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