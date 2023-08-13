package io.github.dehuckakpyt.telegrambot.data.container

import com.dehucka.microservice.ext.shortMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class Container(
    val chatId: Long,
    val content: String?,
) : KoinComponent {

    private val bot = get<TelegramBot>()
    private val chainSource = get<ChainSource>()

    private var nextStep: String? = null
    private var nextStepInstance: Any? = null

    suspend fun sendMessage(text: String) {
        bot.sendMessage(chatId, text)
    }

    fun next(step: String?) {
        nextStep = step
    }

    fun next(step: String, instance: Any) {
        nextStep = step
        nextStepInstance = instance
    }

    fun transferToNext(instance: Any) {
        nextStepInstance = instance
    }

    inline fun <reified T> transferred(): T {
        return content?.let { shortMapper.readValue(it) }
            ?: throw RuntimeException("Ожидается экземпляр класса ${T::class.simpleName}, но в chainSource.content ничего не сохранено.")
    }

    inline fun <reified T> transferredOrNull(): T? {
        return content?.let { shortMapper.readValue(it) }
    }

    internal suspend fun finalize() {
        chainSource.save(chatId, nextStep, nextStepInstance.toContent())
    }

    private fun Any?.toContent(): String? {
        return this?.let { shortMapper.writeValueAsString(it) }
    }
}