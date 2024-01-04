package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class Game(
    @param:JsonProperty("title") val title: String,
    @param:JsonProperty("description") val description: String,
    @param:JsonProperty("photo") val photo: List<PhotoSize>,
    @param:JsonProperty("text") val text: String? = null,
    @param:JsonProperty("text_entities") val textEntities: List<MessageEntity>? = null,
    @param:JsonProperty("animation") val animation: Animation? = null,
)

public data class GameHighScore(
    @param:JsonProperty("position") val position: Int,
    @param:JsonProperty("user") val user: User,
    @param:JsonProperty("score") val score: Int
)

public class CallbackGame