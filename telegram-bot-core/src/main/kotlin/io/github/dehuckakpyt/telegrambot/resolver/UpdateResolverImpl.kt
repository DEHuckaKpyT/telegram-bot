package io.github.dehuckakpyt.telegrambot.resolver

import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import org.slf4j.LoggerFactory


/**
 * Created on 08.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class UpdateResolverImpl internal constructor(
    private val dialogUpdateResolver: DialogUpdateResolver,
    private val eventUpdateResolver: EventUpdateResolver,
) : UpdateResolver {
    private val logger = LoggerFactory.getLogger(UpdateResolverImpl::class.java)

    override suspend fun processUpdate(update: Update): Unit = with(update) {
        try {
            eventUpdateResolver.processUpdate(this)

            when {
                message != null -> dialogUpdateResolver.processMessage(message)
                callbackQuery != null -> dialogUpdateResolver.processCallback(callbackQuery)
                myChatMember != null -> dialogUpdateResolver.processMyChatMember(myChatMember)
            }
        } catch (throwable: Throwable) {
            logger.error("Error while processing Update:\n$update", throwable)
        }
    }

    override val allowedUpdates: Set<String>
        get() = buildSet {
            addAll(eventUpdateResolver.allowedUpdates)
            addAll(dialogUpdateResolver.allowedUpdates)
        }
}