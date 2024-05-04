package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.github.dehuckakpyt.telegrambot.model.type.supplement.input.NamedContentInput


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
    public abstract val mediaContent: NamedContentInput?
    public abstract val thumbnail: String?
    public abstract val thumbnailContent: NamedContentInput?
}

public data class InputMediaPhoto private constructor(
    @get:JsonProperty("media") override val media: String,
    @get:JsonIgnore override val mediaContent: NamedContentInput? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia() {

    public constructor(
        media: String,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
    ) : this(media, null, caption, parseMode, captionEntities, hasSpoiler)

    public constructor(
        media: NamedContentInput,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        hasSpoiler: Boolean? = null,
    ) : this("attach://${media.name}", media, caption, parseMode, captionEntities, hasSpoiler)

    @get:JsonProperty("thumbnail")
    override val thumbnail: String? = null

    @get:JsonIgnore
    override val thumbnailContent: NamedContentInput? = null

    @get:JsonProperty("type")
    override val type: String = "photo"
}

public data class InputMediaVideo private constructor(
    @get:JsonProperty("media") override val media: String,
    @get:JsonIgnore override val mediaContent: NamedContentInput? = null,
    @get:JsonProperty("thumbnail") override val thumbnail: String? = null,
    @get:JsonIgnore override val thumbnailContent: NamedContentInput? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("width") val width: Int? = null,
    @get:JsonProperty("height") val height: Int? = null,
    @get:JsonProperty("duration") val duration: Int? = null,
    @get:JsonProperty("supports_streaming") val supportsStreaming: Boolean? = null,
    @get:JsonProperty("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia() {

    public constructor(
        media: String,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        width: Int? = null,
        height: Int? = null,
        duration: Int? = null,
        supportsStreaming: Boolean? = null,
        hasSpoiler: Boolean? = null,
    ) : this(media, null, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, width, height, duration, supportsStreaming, hasSpoiler)

    public constructor(
        media: NamedContentInput,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        width: Int? = null,
        height: Int? = null,
        duration: Int? = null,
        supportsStreaming: Boolean? = null,
        hasSpoiler: Boolean? = null,
    ) : this("attach://${media.name}", media, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, width, height, duration, supportsStreaming, hasSpoiler)

    @get:JsonProperty("type")
    override val type: String = "video"
}

public data class InputMediaAnimation private constructor(
    @get:JsonProperty("media") override val media: String,
    @get:JsonIgnore override val mediaContent: NamedContentInput? = null,
    @get:JsonProperty("thumbnail") override val thumbnail: String? = null,
    @get:JsonIgnore override val thumbnailContent: NamedContentInput? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("width") val width: Int? = null,
    @get:JsonProperty("height") val height: Int? = null,
    @get:JsonProperty("duration") val duration: Int? = null,
    @get:JsonProperty("has_spoiler") val hasSpoiler: Boolean? = null,
) : InputMedia() {

    public constructor(
        media: String,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        width: Int? = null,
        height: Int? = null,
        duration: Int? = null,
        hasSpoiler: Boolean? = null,
    ) : this(media, null, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, width, height, duration, hasSpoiler)

    public constructor(
        media: NamedContentInput,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        width: Int? = null,
        height: Int? = null,
        duration: Int? = null,
        hasSpoiler: Boolean? = null,
    ) : this("attach://${media.name}", media, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, width, height, duration, hasSpoiler)

    @get:JsonProperty("type")
    override val type: String = "animation"
}

public data class InputMediaAudio private constructor(
    @get:JsonProperty("media") override val media: String,
    @get:JsonIgnore override val mediaContent: NamedContentInput? = null,
    @get:JsonProperty("thumbnail") override val thumbnail: String? = null,
    @get:JsonIgnore override val thumbnailContent: NamedContentInput? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("duration") val duration: Int? = null,
    @get:JsonProperty("performer") val performer: String? = null,
    @get:JsonProperty("title") val title: String? = null,
) : InputMedia() {

    public constructor(
        media: String,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
    ) : this(media, null, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, duration, performer, title)

    public constructor(
        media: NamedContentInput,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        duration: Int? = null,
        performer: String? = null,
        title: String? = null,
    ) : this("attach://${media.name}", media, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, duration, performer, title)

    @get:JsonProperty("type")
    override val type: String = "audio"
}

public data class InputMediaDocument private constructor(
    @get:JsonProperty("media") override val media: String,
    @get:JsonIgnore override val mediaContent: NamedContentInput? = null,
    @get:JsonProperty("thumbnail") override val thumbnail: String? = null,
    @get:JsonIgnore override val thumbnailContent: NamedContentInput? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("disable_content_type_detection") val disableContentTypeDetection: Boolean? = null,
) : InputMedia() {

    public constructor(
        media: String,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
    ) : this(media, null, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, disableContentTypeDetection)

    public constructor(
        media: NamedContentInput,
        thumbnail: NamedContentInput? = null,
        caption: String? = null,
        parseMode: String? = null,
        captionEntities: List<MessageEntity>? = null,
        disableContentTypeDetection: Boolean? = null,
    ) : this("attach://${media.name}", media, thumbnail?.let { "attach://${thumbnail.name}" }, thumbnail, caption, parseMode, captionEntities, disableContentTypeDetection)

    @get:JsonProperty("type")
    override val type: String = "document"
}