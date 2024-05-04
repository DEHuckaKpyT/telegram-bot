package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.model.internal.AllowedUpdate
import io.github.dehuckakpyt.telegrambot.model.type.Update
import io.github.dehuckakpyt.telegrambot.model.type.WebhookInfo
import io.github.dehuckakpyt.telegrambot.model.type.supplement.input.NamedContentInput


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 */
interface TelegramUpdatesApi {
    suspend fun getUpdates(
        offset: Int? = null,
        limit: Int? = null,
        timeout: Int? = null,
        allowedUpdates: Iterable<AllowedUpdate>? = null
    ): List<Update>

    suspend fun setWebhook(
        url: String,
        certificate: NamedContentInput? = null,
        ipAddress: String? = null,
        maxConnections: Int? = null,
        allowedUpdates: List<AllowedUpdate>? = null,
        dropPendingUpdates: Boolean? = null,
        secretToken: String? = null,
    ): Boolean

    suspend fun deleteWebhook(dropPendingUpdates: Boolean? = null): Boolean

    suspend fun getWebhookInfo(): WebhookInfo
}