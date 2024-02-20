package io.github.dehuckakpyt.telegrambot.test.mock

import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager.updatesChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


/**
 * Created on 19.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
class MockUpdateReceiver(
    private val updateResolver: UpdateResolver,
) : UpdateReceiver {
    private val scope = CoroutineScope(Dispatchers.Default)

    override fun start() {
        scope.launch { receiveUpdates() }
    }

    private suspend fun receiveUpdates() {
        while (true) {
            val updates = updatesChannel.receive()

            updates.forEach { updateResolver.processUpdate(it) }
        }
    }

    override fun stop() {
        scope.cancel()
    }
}