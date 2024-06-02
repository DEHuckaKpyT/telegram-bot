package io.github.dehuckakpyt.telegrambot.model.telegram.`internal`

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * @author KScript
 */
internal data class SetGameScoreByInlineMessageId(
    @get:JsonProperty("user_id")
    public val userId: Long,
    @get:JsonProperty("score")
    public val score: Int,
    @get:JsonProperty("inline_message_id")
    public val inlineMessageId: String,
    @get:JsonProperty("force")
    public val force: Boolean? = null,
    @get:JsonProperty("disable_edit_message")
    public val disableEditMessage: Boolean? = null,
)
