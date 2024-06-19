package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.collections.List

/**
 * This object represents a service message about new members invited to a video chat.
 *
 * @see [VideoChatParticipantsInvited]
 * (https://core.telegram.org/bots/api/#videochatparticipantsinvited)
 *
 * @author KScript
 *
 * @param users New members that were invited to the video chat
 */
public data class VideoChatParticipantsInvited(
    /**
     * New members that were invited to the video chat
     */
    @get:JsonProperty("users")
    @param:JsonProperty("users")
    public val users: List<User>,
)
