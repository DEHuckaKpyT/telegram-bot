package io.github.dehuckakpyt.telegrambot.test

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.dehuckakpyt.telegrambot.model.type.Update
import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager.objectMapper
import io.github.dehuckakpyt.telegrambot.test.TelegramBotUpdateManager.updatesChannel


/**
 * Created on 13.02.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public suspend fun sendUpdate(update: Update): Unit {
    updatesChannel.send(listOf(update))
}

public suspend fun sendUpdates(updates: List<Update>): Unit {
    updatesChannel.send(updates)
}

public suspend fun sendUpdate(json: String): Unit {
    updatesChannel.send(listOf(objectMapper.readValue<Update>(json)))
}

public suspend fun sendUpdates(json: String): Unit {
    updatesChannel.send(objectMapper.readValue<List<Update>>(json))
}