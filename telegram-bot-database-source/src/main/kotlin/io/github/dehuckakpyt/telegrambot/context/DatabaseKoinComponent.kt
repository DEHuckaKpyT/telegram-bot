package io.github.dehuckakpyt.telegrambot.context

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools


/**
 * Created on 07.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal interface DatabaseKoinComponent : KoinComponent {

    /**
     * Get the associated internal Koin instance
     */
    fun getDatabaseKoin(): Koin = DatabaseKoinContext.koin
}

/**
 * Get instance from internal Koin
 * @param qualifier
 * @param parameters
 */
internal inline fun <reified T : Any> DatabaseKoinComponent.getDatabase(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = getDatabaseKoin().get(qualifier, parameters)

/**
 * Lazy inject instance from internal Koin
 * @param qualifier
 * @param mode - LazyThreadSafetyMode
 * @param parameters
 */
internal inline fun <reified T : Any> DatabaseKoinComponent.injectDatabase(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = KoinPlatformTools.defaultLazyMode(),
    noinline parameters: ParametersDefinition? = null
): Lazy<T> = lazy(mode) { getDatabase<T>(qualifier, parameters) }