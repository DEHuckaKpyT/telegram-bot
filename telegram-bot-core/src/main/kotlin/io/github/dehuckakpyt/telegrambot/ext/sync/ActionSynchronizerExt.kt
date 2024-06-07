package io.github.dehuckakpyt.telegrambot.ext.sync

import io.github.dehuckakpyt.telegrambot.sync.ActionSynchronizer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


/**
 * Created on 06.06.2024.
 *
 * @author Denis Matytsin
 */
@Suppress("FunctionName")
internal fun <T> ASync(scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())) = ActionSynchronizer<T>(scope)