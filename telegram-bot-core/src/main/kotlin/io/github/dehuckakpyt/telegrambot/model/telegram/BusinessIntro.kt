package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * Contains information about the start page settings of a Telegram Business account.
 *
 * @see [BusinessIntro] (https://core.telegram.org/bots/api/#businessintro)
 *
 * @author KScript
 *
 * @param title *Optional*. Title text of the business intro
 * @param message *Optional*. Message text of the business intro
 * @param sticker *Optional*. Sticker of the business intro
 */
public data class BusinessIntro(
    /**
     * *Optional*. Title text of the business intro
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Message text of the business intro
     */
    @get:JsonProperty("message")
    @param:JsonProperty("message")
    public val message: String? = null,
    /**
     * *Optional*. Sticker of the business intro
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Sticker? = null,
)
