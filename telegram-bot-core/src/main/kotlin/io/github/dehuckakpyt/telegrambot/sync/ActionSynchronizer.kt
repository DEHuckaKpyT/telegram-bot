package io.github.dehuckakpyt.telegrambot.sync

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap


/**
 * Created on 06.06.2024.
 *
 * Synchronizer of actions by key.
 * Executes actions with the same key sequentially, and actions with different keys independently of each other.
 *
 * @author Denis Matytsin
 */
internal class ActionSynchronizer<T>(
    private val scope: CoroutineScope,
) {
    private val lastJobsById = ConcurrentHashMap<T, Job>()

    suspend fun parallelisedBy(identifier: T, action: suspend () -> Unit) {
        val lastJob = lastJobsById[identifier]

        val currentJob = scope.launch {
            lastJob?.join()
            action()
        }

        lastJobsById[identifier] = currentJob

        currentJob.invokeOnCompletion {
            lastJobsById.remove(identifier, currentJob)
        }
    }
}
