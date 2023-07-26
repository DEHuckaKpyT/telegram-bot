package com.dehucka.telegrambot

import com.dehucka.microservice.ext.shortMapper
import com.dehucka.microservice.ext.toUUID
import com.dehucka.telegrambot.ext.chatId
import com.dehucka.telegrambot.source.callback.CallbackContentSource
import com.dehucka.telegrambot.source.callback.CallbackContentSourceImpl
import com.dehucka.telegrambot.source.chain.ChainSource
import com.dehucka.telegrambot.source.chain.ChainSourceImpl
import com.dehucka.telegrambot.source.message.MessageSource
import com.dehucka.telegrambot.source.message.MessageSourceImpl
import com.elbekd.bot.Bot
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import com.fasterxml.jackson.module.kotlin.readValue
import freemarker.template.Configuration
import freemarker.template.Version
import io.ktor.server.application.*


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class BotHandling(
    application: Application,
    bot: Bot,
    username: String,
    messageSource: MessageSource = MessageSourceImpl(),
    chainSource: ChainSource = ChainSourceImpl(),
    callbackContentSource: CallbackContentSource = CallbackContentSourceImpl(),
    templateConfiguration: Configuration = Configuration(Version("2.3.32"))
) : TelegramBotChaining(
    application,
    bot,
    username,
    messageSource,
    chainSource,
    callbackContentSource,
    templateConfiguration
) {

    inline fun command(
        command: String, nextStep: String? = null,
        enableCustomSteps: Boolean = false,
        crossinline action: suspend Message.(Pair<String?, String?>) -> Unit
    ) {
        actionByCommand[command] = {
            this.action(it)
            if (!enableCustomSteps) {
                chainSource.save(chatId, nextStep)
            }
        }
    }

    inline fun step(
        step: String,
        nextStep: String? = null,
        enableCustomSteps: Boolean = false,
        crossinline action: suspend Message.() -> Unit
    ) {
        actionByStep[step] = {
            this.action()

            if (!enableCustomSteps) {
                chainSource.save(chatId, nextStep)
            }
        }
    }

    inline fun <reified T> step(
        step: String,
        nextStep: String? = null,
        enableCustomSteps: Boolean = false,
        crossinline action: suspend Message.(T) -> Unit
    ) {
        actionByStep[step] = { content ->
            content
                ?: throw RuntimeException("Ожидается экземпляр класса ${T::class.simpleName}, но в chainSource.content ничего не сохранено.")

            val instance = shortMapper.readValue<T>(content)
            this.action(instance)

            if (!enableCustomSteps) {
                chainSource.save(chatId, nextStep)
            }
        }
    }

    inline fun callback(
        callback: String,
        nextStep: String? = null,
        enableCustomSteps: Boolean = false,
        crossinline action: suspend CallbackQuery.() -> Unit
    ) {
        actionByCallback[callback] = {
            this.action()
            if (!enableCustomSteps) {
                chainSource.save(chatId, nextStep)
            }
        }
    }

    inline fun <reified T> callback(
        callback: String,
        nextStep: String? = null,
        enableCustomSteps: Boolean = false,
        crossinline action: suspend CallbackQuery.(T) -> Unit
    ) {
        actionByCallback[callback] = { content ->
            val instance = if (content.startsWith('{') || content.startsWith('[')) {
                shortMapper.readValue<T>(content)
            } else {
                callbackContentSource.get(content.toUUID()).let {
                    shortMapper.readValue<T>(it.content)
                }
            }

            this.action(instance)

            if (!enableCustomSteps) {
                chainSource.save(chatId, nextStep)
            }
        }
    }
}
