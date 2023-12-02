package io.github.dehuckakpyt.telegrambot.context

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatformTools


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal interface InternalKoinComponent : KoinComponent {

    /**
     * Get the associated internal Koin instance
     */
    fun getInternalKoin(): Koin = InternalKoinContext.koin
}

/**
 * Get instance from internal Koin
 * @param qualifier
 * @param parameters
 */
internal inline fun <reified T : Any> InternalKoinComponent.getInternal(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = getInternalKoin().get(qualifier, parameters)

/**
 * Lazy inject instance from internal Koin
 * @param qualifier
 * @param mode - LazyThreadSafetyMode
 * @param parameters
 */
internal inline fun <reified T : Any> InternalKoinComponent.injectInternal(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = KoinPlatformTools.defaultLazyMode(),
    noinline parameters: ParametersDefinition? = null
): Lazy<T> = lazy(mode) { getInternal<T>(qualifier, parameters) }