package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * Describes the current status of a webhook.
 *
 * @see [WebhookInfo] (https://core.telegram.org/bots/api/#webhookinfo)
 *
 * @author KScript
 *
 * @param url Webhook URL, may be empty if webhook is not set up
 * @param hasCustomCertificate *True*, if a custom certificate was provided for webhook certificate
 * checks
 * @param pendingUpdateCount Number of updates awaiting delivery
 * @param ipAddress *Optional*. Currently used webhook IP address
 * @param lastErrorDate *Optional*. Unix time for the most recent error that happened when trying to
 * deliver an update via webhook
 * @param lastErrorMessage *Optional*. Error message in human-readable format for the most recent
 * error that happened when trying to deliver an update via webhook
 * @param lastSynchronizationErrorDate *Optional*. Unix time of the most recent error that happened
 * when trying to synchronize available updates with Telegram datacenters
 * @param maxConnections *Optional*. The maximum allowed number of simultaneous HTTPS connections to
 * the webhook for update delivery
 * @param allowedUpdates *Optional*. A list of update types the bot is subscribed to. Defaults to
 * all update types except *chat_member*
 */
public data class WebhookInfo(
    /**
     * Webhook URL, may be empty if webhook is not set up
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
    /**
     * *True*, if a custom certificate was provided for webhook certificate checks
     */
    @get:JsonProperty("has_custom_certificate")
    @param:JsonProperty("has_custom_certificate")
    public val hasCustomCertificate: Boolean,
    /**
     * Number of updates awaiting delivery
     */
    @get:JsonProperty("pending_update_count")
    @param:JsonProperty("pending_update_count")
    public val pendingUpdateCount: Int,
    /**
     * *Optional*. Currently used webhook IP address
     */
    @get:JsonProperty("ip_address")
    @param:JsonProperty("ip_address")
    public val ipAddress: String? = null,
    /**
     * *Optional*. Unix time for the most recent error that happened when trying to deliver an
     * update via webhook
     */
    @get:JsonProperty("last_error_date")
    @param:JsonProperty("last_error_date")
    public val lastErrorDate: Long? = null,
    /**
     * *Optional*. Error message in human-readable format for the most recent error that happened
     * when trying to deliver an update via webhook
     */
    @get:JsonProperty("last_error_message")
    @param:JsonProperty("last_error_message")
    public val lastErrorMessage: String? = null,
    /**
     * *Optional*. Unix time of the most recent error that happened when trying to synchronize
     * available updates with Telegram datacenters
     */
    @get:JsonProperty("last_synchronization_error_date")
    @param:JsonProperty("last_synchronization_error_date")
    public val lastSynchronizationErrorDate: Long? = null,
    /**
     * *Optional*. The maximum allowed number of simultaneous HTTPS connections to the webhook for
     * update delivery
     */
    @get:JsonProperty("max_connections")
    @param:JsonProperty("max_connections")
    public val maxConnections: Int? = null,
    /**
     * *Optional*. A list of update types the bot is subscribed to. Defaults to all update types
     * except *chat_member*
     */
    @get:JsonProperty("allowed_updates")
    @param:JsonProperty("allowed_updates")
    public val allowedUpdates: List<String>? = null,
)
