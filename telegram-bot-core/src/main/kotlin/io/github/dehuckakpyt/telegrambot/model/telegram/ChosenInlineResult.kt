package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * Represents a [result](https://core.telegram.org/bots/api/#inlinequeryresult) of an inline query
 * that was chosen by the user and sent to their chat partner.
 *
 * @see [ChosenInlineResult] (https://core.telegram.org/bots/api/#choseninlineresult)
 *
 * @author KScript
 *
 * @param resultId The unique identifier for the result that was chosen
 * @param from The user that chose the result
 * @param location *Optional*. Sender location, only for bots that require user location
 * @param inlineMessageId *Optional*. Identifier of the sent inline message. Available only if there
 * is an [inline keyboard](https://core.telegram.org/bots/api/#inlinekeyboardmarkup) attached to the
 * message. Will be also received in [callback
 * queries](https://core.telegram.org/bots/api/#callbackquery) and can be used to
 * [edit](https://core.telegram.org/bots/api/#updating-messages) the message.
 * @param query The query that was used to obtain the result
 */
public data class ChosenInlineResult(
    /**
     * The unique identifier for the result that was chosen
     */
    @get:JsonProperty("result_id")
    @param:JsonProperty("result_id")
    public val resultId: String,
    /**
     * The user that chose the result
     */
    @get:JsonProperty("from")
    @param:JsonProperty("from")
    public val from: User,
    /**
     * *Optional*. Sender location, only for bots that require user location
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location? = null,
    /**
     * *Optional*. Identifier of the sent inline message. Available only if there is an [inline
     * keyboard](https://core.telegram.org/bots/api/#inlinekeyboardmarkup) attached to the message.
     * Will be also received in [callback queries](https://core.telegram.org/bots/api/#callbackquery)
     * and can be used to [edit](https://core.telegram.org/bots/api/#updating-messages) the message.
     */
    @get:JsonProperty("inline_message_id")
    @param:JsonProperty("inline_message_id")
    public val inlineMessageId: String? = null,
    /**
     * The query that was used to obtain the result
     */
    @get:JsonProperty("query")
    @param:JsonProperty("query")
    public val query: String,
)
