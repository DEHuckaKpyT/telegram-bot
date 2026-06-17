package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A block with a voice note, corresponding to the HTML tag `<audio>`.
 *
 * @see [RichBlockVoiceNote] (https://core.telegram.org/bots/api/#richblockvoicenote)
 *
 * @author KScript
 *
 * @param type Type of the block, always “voice_note”
 * @param voiceNote The voice note
 * @param caption *Optional*. Caption of the block
 */
public data class RichBlockVoiceNote(
    /**
     * Type of the block, always “voice_note”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * The voice note
     */
    @get:JsonProperty("voice_note")
    @param:JsonProperty("voice_note")
    public val voiceNote: Voice,
    /**
     * *Optional*. Caption of the block
     */
    @get:JsonProperty("caption")
    @param:JsonProperty("caption")
    public val caption: RichBlockCaption? = null,
) : RichBlock
