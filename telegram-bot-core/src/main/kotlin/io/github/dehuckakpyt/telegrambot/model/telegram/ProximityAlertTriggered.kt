package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Int

/**
 * This object represents the content of a service message, sent whenever a user in the chat
 * triggers a proximity alert set by another user.
 *
 * @see [ProximityAlertTriggered] (https://core.telegram.org/bots/api/#proximityalerttriggered)
 *
 * @author KScript
 *
 * @param traveler User that triggered the alert
 * @param watcher User that set the alert
 * @param distance The distance between the users
 */
public data class ProximityAlertTriggered(
    /**
     * User that triggered the alert
     */
    @get:JsonProperty("traveler")
    @param:JsonProperty("traveler")
    public val traveler: User,
    /**
     * User that set the alert
     */
    @get:JsonProperty("watcher")
    @param:JsonProperty("watcher")
    public val watcher: User,
    /**
     * The distance between the users
     */
    @get:JsonProperty("distance")
    @param:JsonProperty("distance")
    public val distance: Int,
)
