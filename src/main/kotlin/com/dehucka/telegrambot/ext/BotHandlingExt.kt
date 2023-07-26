package com.dehucka.telegrambot.ext

import com.dehucka.telegrambot.BotHandling
import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.Message
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */

/**
 * Help work on ModuleDefinition
 */
fun BotHandling.getKoin(): Koin = GlobalContext.get()

/**
 * inject lazily given dependency
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> BotHandling.inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { get<T>(qualifier, parameters) }

/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param scope
 * @param parameters
 */
inline fun <reified T : Any> BotHandling.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = getKoin().get<T>(qualifier, parameters)

/**
 * Retrieve given property for KoinComponent
 * @param key - key property
 */
fun <T : Any> BotHandling.getProperty(key: String) =
    getKoin().getProperty<T>(key)

/**
 * Retrieve given property for KoinComponent
 * give a default value if property is missing
 *
 * @param key - key property
 * @param defaultValue - default value if property is missing
 *
 */
fun BotHandling.getProperty(key: String, defaultValue: String) =
    getKoin().getProperty(key) ?: defaultValue

val Message.chatId: Long
    get() = chat.id
val CallbackQuery.chatId: Long
    get() = message?.chat?.id ?: from.id

