package io.github.dehuckakpyt.telegrambot

import com.dehucka.microservice.exception.CustomException
import com.dehucka.microservice.ext.shortMapper
import com.elbekd.bot.Bot
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.elbekd.bot.types.ParseMode.MarkdownV2
import com.elbekd.bot.types.Update
import com.elbekd.bot.types.UpdateMessage
import freemarker.template.Configuration
import io.github.dehuckakpyt.telegrambot.data.CommandInput
import io.github.dehuckakpyt.telegrambot.ext.chatId
import io.github.dehuckakpyt.telegrambot.ext.fetchCommand
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.ktor.server.application.*


/**
 * Created on 21.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class TelegramBotChaining(
    application: Application,
    bot: Bot,
    val username: String,
    private val messageSource: MessageSource,
    val chainSource: ChainSource,
    val callbackContentSource: CallbackContentSource,
    templateConfiguration: Configuration
) : TelegramBot(application, bot, messageSource, templateConfiguration) {

    val actionByCommand: MutableMap<String, suspend Message.(Pair<String?, String?>) -> Unit> = hashMapOf()
    val actionByStep: MutableMap<String, suspend Message.(String?) -> Unit> = hashMapOf()
    val actionByCallback: MutableMap<String, suspend CallbackQuery.(String) -> Unit> = hashMapOf()

    val callbackDataDelimiter: Char = '|'

    private val whenCommandNotFound: suspend (Long, String) -> Unit = { chatId, command ->
        sendMessage(
            chatId = chatId,
            text = "Введена неизвестная команда `$command`\\. Посмотреть возможные действия можно, вызвав команду /help\\.",
            parseMode = MarkdownV2
        )
    }
    private val whenKnownError: suspend (Long, String) -> Unit = { chatId, message ->
        sendMessage(chatId, text = message)
    }
    private val whenUnknownError: suspend (Long) -> Unit = { chatId ->
        sendMessage(chatId, "Произошла непредвиденная ошибка. Обратитесь к разработчику.")
    }
    private val whenStepNotFound: suspend (Long) -> Unit = { chatId ->
        sendMessage(chatId, "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help.")
    }

    init {
        bot.onCallbackQuery { callbackQuery ->
            processCallback(callbackQuery)
        }

        bot.onAnyUpdate { update ->
            processUpdate(update)
        }
    }

    suspend fun nextStep(chatId: Long, handler: String) {
        chainSource.save(chatId, handler)
    }

    suspend fun finalizeChain(chatId: Long) {
        chainSource.save(chatId, null)
    }

    suspend fun toNextStep(chatId: Long, instance: Any) {
        shortMapper.writeValueAsString(instance).let {
            chainSource.saveContent(chatId, it)
        }
    }

    private suspend fun processUpdate(update: Update) {
        if (update !is UpdateMessage) return

        val message = update.message
        val text = message.text ?: return
        val chatId = message.chatId

        tryExecute(chatId) {
            messageSource.save(chatId, message.from?.id, message.messageId, text)

            fetchCommand(text)?.let {
                processCommand(it, message)
            } ?: processMessage(message)
        }
    }

    private suspend fun processCommand(input: CommandInput, message: Message) = with(input) {
        actionByCommand[command]
            ?.invoke(message, pathParam to lineParam)
            ?: whenCommandNotFound(message.chatId, command)
    }

    private suspend fun processMessage(message: Message) = with(message) {
        val chainLink = chainSource.get(chatId)
        chainLink.step?.let { step ->
            actionByStep[step]
        }?.run {
            this(chainLink.content)
        } ?: whenStepNotFound(chatId)
    }

    private suspend fun processCallback(callback: CallbackQuery) = with(callback) {
        val data = data ?: return

        val indexOfDelimiter = data.indexOf(callbackDataDelimiter)
        if (indexOfDelimiter == -1) return

        val callbackName = data.substring(0, indexOfDelimiter)
        val callbackContent = data.substring(indexOfDelimiter + 1)

        tryExecute(chatId) {
            actionByCallback[callbackName]?.invoke(callback, callbackContent)
        }
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
}