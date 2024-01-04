package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.File


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public sealed class InputMedia {
    public abstract val type: String
    public abstract val media: String
    public abstract val attachment: File?
    public abstract val thumb: Any?
}

public data class InputMediaPhoto(
    @get:JsonProperty("media") override val media: String,
    @Transient override val attachment: File? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia() {
    @Transient
    override val thumb: Any? = null

    @get:JsonProperty("type")
    override val type: String = "photo"
}

public data class InputMediaVideo(
    @get:JsonProperty("media") override val media: String,
    @Transient override val attachment: File? = null,
    @Transient override val thumb: Any? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("width") val width: Int? = null,
    @get:JsonProperty("height") val height: Int? = null,
    @get:JsonProperty("duration") val duration: Int? = null,
    @get:JsonProperty("supports_streaming") val supportsStreaming: Boolean? = null,
    @get:JsonProperty("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia() {
    @get:JsonProperty("type")
    override val type: String = "video"
}

public data class InputMediaAnimation(
    @get:JsonProperty("media") override val media: String,
    @Transient override val attachment: File? = null,
    @Transient override val thumb: Any? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("width") val width: Int? = null,
    @get:JsonProperty("height") val height: Int? = null,
    @get:JsonProperty("duration") val duration: Int? = null,
    @get:JsonProperty("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia() {
    @get:JsonProperty("type")
    override val type: String = "animation"
}

public data class InputMediaAudio(
    @get:JsonProperty("media") override val media: String,
    @Transient override val attachment: File? = null,
    @Transient override val thumb: Any? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("duration") val duration: Int? = null,
    @get:JsonProperty("performer") val performer: String? = null,
    @get:JsonProperty("title") val title: String? = null,
) : InputMedia() {
    @get:JsonProperty("type")
    override val type: String = "audio"
}

public data class InputMediaDocument(
    @get:JsonProperty("media") override val media: String,
    @Transient override val attachment: File? = null,
    @Transient override val thumb: Any? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("disable_content_type_detection") val disableContentTypeDetection: Boolean? = null,
) : InputMedia() {
    @get:JsonProperty("type")
    override val type: String = "document"
}