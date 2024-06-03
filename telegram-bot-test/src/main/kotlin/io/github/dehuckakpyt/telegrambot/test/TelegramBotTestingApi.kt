package io.github.dehuckakpyt.telegrambot.test

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager.objectMapper
import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager.updateResolver
import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager.updatesChannel


/**
 * Created on 13.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public suspend fun sendUpdateAsync(update: Update): Unit {
    updatesChannel.send(listOf(update))
}

public suspend fun sendUpdatesAsync(updates: List<Update>): Unit {
    updatesChannel.send(updates)
}

public suspend fun sendUpdateAsync(json: String): Unit {
    updatesChannel.send(listOf(objectMapper.readValue<Update>(json)))
}

public suspend fun sendUpdatesAsync(json: String): Unit {
    updatesChannel.send(objectMapper.readValue<List<Update>>(json))
}

public suspend fun sendUpdate(update: Update): Unit {
    updateResolver.processUpdate(update)
}

public suspend fun sendUpdates(updates: List<Update>): Unit {
    updates.forEach { update -> updateResolver.processUpdate(update) }
}

public suspend fun sendUpdate(json: String): Unit {
    updateResolver.processUpdate(objectMapper.readValue<Update>(json))
}

public suspend fun sendUpdates(json: String): Unit {
    objectMapper.readValue<List<Update>>(json).forEach { update -> updateResolver.processUpdate(update) }
}