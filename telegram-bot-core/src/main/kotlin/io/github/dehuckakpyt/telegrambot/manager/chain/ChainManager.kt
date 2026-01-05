package io.github.dehuckakpyt.telegrambot.manager.chain

import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import org.jetbrains.annotations.ApiStatus.Experimental
import kotlin.reflect.KClass


/**
 * Class for saving the state of the dialog between the user and the bot.
 * Dialogs are separated by chat and by the user who wrote the message.
 *
 * This means that one user will have different dialogs in different chats.
 * And it means that in a group chat each user will have a different dialog.
 *
 * @author Denis Matytsin
 */
@Experimental
class ChainManager(
    private val chainSource: ChainSource,
    private val contentConverter: ContentConverter,
) {

    /**
     * Save the step for next in the dialog.
     *
     * @param chatId which chat to set step
     * @param fromId which user in chat to set step (in private chats equals to chatId)
     * @param step name of the next step for selected dialog
     * @param instance instance of any class for transfer
     *
     * @see io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
     */
    public suspend fun setNextStep(chatId: Long, fromId: Long, step: String?, instance: Any? = null) {
        val content = instance?.let(contentConverter::toContent)

        chainSource.save(chatId, fromId, step, content)
    }

    /**
     * Get the current saved step by chat and by user.
     *
     * @param chatId which chat to get step
     * @param fromId which user in chat to get step (in private chats equals to chatId)
     *
     * @return step value.
     */
    public suspend fun getCurrentStep(chatId: Long, fromId: Long): String? {
        return chainSource.get(chatId, fromId)?.step
    }

    /**
     * Get the current step and instance by chat and by user.
     *
     * @param chatId which chat to get step
     * @param fromId which user in chat to get step (in private chats equals to chatId)
     * @param clazz any class for convert transferred instance
     *
     * @return step and instance.
     */
    public suspend fun <T : Any> getCurrentStepAndInstance(chatId: Long, fromId: Long, clazz: KClass<T>): Pair<String?, T?>? {
        val chain = chainSource.get(chatId, fromId) ?: return null

        val instance = chain.content?.let { content -> contentConverter.fromContent(content, clazz) }

        return chain.step to instance
    }
}

/**
 * Get the current step and instance by chat and by user.
 *
 * @param chatId which chat to get step
 * @param fromId which user in chat to get step (in private chats equals to chatId)
 * @param T any class for convert transferred instance
 *
 * @return step and instance.
 */
suspend inline fun <reified T : Any> ChainManager.getCurrentStepAndInstance(chatId: Long, fromId: Long): Pair<String?, T?>? {
    return getCurrentStepAndInstance(chatId, fromId, T::class)
}