package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.User
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class Container(
    chatId: Long,
    val content: String?,
    private val chainSource: ChainSource,
    val contentConverter: ContentConverter,
    bot: TelegramBot,
) : TelegramApiContainer(chatId, bot) {

    val username = bot.username
    abstract val from: User

    private var nextStep: String? = null
    private var nextStepInstance: Any? = null

    fun next(step: String?) {
        nextStep = step
    }

    fun next(step: String, instance: Any) {
        nextStep = step
        nextStepInstance = instance
    }

    fun finalizeChain() = next(null)

    fun transfer(instance: Any) {
        nextStepInstance = instance
    }

    inline fun <reified T> transferred(): T {
        return transferredOrNull()
            ?: throw RuntimeException("Ожидается экземпляр класса ${T::class.simpleName}, но в chainSource.content ничего не сохранено.")
    }

    inline fun <reified T : Any> transferredOrNull(): T? = contentConverter.fromContentOrNull(content, T::class)

    internal suspend fun finalize() {
        chainSource.save(chatId, from.id, nextStep, nextStepInstance.toContent())
    }

    private fun Any?.toContent(): String? = contentConverter.toContentOrNull(this)
}