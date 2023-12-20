package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class WebAppInfo(
    @get:JsonProperty("url") @param:JsonProperty("url") val url: String,
)

public data class SentWebAppMessage(
    @get:JsonProperty("inline_message_id") @param:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
)

public data class WebAppData(
    @get:JsonProperty("data") @param:JsonProperty("data") val data: String,
    @get:JsonProperty("button_text") @param:JsonProperty("button_text") val buttonText: String,
)