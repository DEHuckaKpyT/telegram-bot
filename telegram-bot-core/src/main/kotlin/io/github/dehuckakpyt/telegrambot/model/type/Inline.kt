package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public data class InlineQuery(
    @param:JsonProperty("id") val id: String,
    @param:JsonProperty("from") val from: User,
    @param:JsonProperty("query") val query: String,
    @param:JsonProperty("offset") val offset: String,
    @param:JsonProperty("chat_type") val chatType: String? = null,
    @param:JsonProperty("location") val location: Location? = null,
)

public sealed class InlineQueryResult {

    public abstract val type: String
}

public data class InlineQueryResultArticle(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("url") val url: String? = null,
    @get:JsonProperty("hide_url") val hideUrl: Boolean? = null,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("thumbnail_width") val thumbnailWidth: Int? = null,
    @get:JsonProperty("thumbnail_height") val thumbnailHeight: Int? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "article"
}

public data class InlineQueryResultPhoto(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("photo_url") val photoUrl: String,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("photo_width") val photoWidth: Int? = null,
    @get:JsonProperty("photo_height") val photoHeight: Int? = null,
    @get:JsonProperty("title") val title: String? = null,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "photo"
}

public data class InlineQueryResultGif(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("gif_url") val gifUrl: String,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("gif_width") val gifWidth: Int? = null,
    @get:JsonProperty("gif_height") val gifHeight: Int? = null,
    @get:JsonProperty("gif_duration") val gifDuration: Int? = null,
    @get:JsonProperty("thumbnail_mime_type") val thumbnailMimeType: String? = null,
    @get:JsonProperty("title") val title: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "gif"
}

public data class InlineQueryResultMpeg4Gif(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("mpeg4_url") val mpeg4Url: String,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("mpeg4_width") val mpeg4Width: Int? = null,
    @get:JsonProperty("mpeg4_height") val mpeg4Height: Int? = null,
    @get:JsonProperty("mpeg4_duration") val mpeg4Duration: Int? = null,
    @get:JsonProperty("thumbnail_mime_type") val thumbnailMimeType: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "mpeg4_gif"
}

public data class InlineQueryResultVideo(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("video_url") val videoUrl: String,
    @get:JsonProperty("mime_type") val mimeType: String,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("video_width") val videoWidth: Int? = null,
    @get:JsonProperty("video_height") val videoHeight: Int? = null,
    @get:JsonProperty("video_duration") val videoDuration: Int? = null,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "video"
}

public data class InlineQueryResultAudio(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("audio_url") val audioUrl: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("performer") val performer: String? = null,
    @get:JsonProperty("audio_duration") val audioDuration: Int? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "audio"
}

public data class InlineQueryResultVoice(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("voice_url") val voiceUrl: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("voice_duration") val voiceDuration: Int? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "voice"
}

public data class InlineQueryResultDocument(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("document_url") val documentUrl: String,
    @get:JsonProperty("mime_type") val mimeType: String,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("thumbnail_width") val thumbnailWidth: Int? = null,
    @get:JsonProperty("thumbnail_height") val thumbnailHeight: Int? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "document"
}

public data class InlineQueryResultLocation(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @get:JsonProperty("live_period") val live_period: Int? = null,
    @get:JsonProperty("heading") val heading: Int? = null,
    @get:JsonProperty("proximity_alert_radius") val proximityAlertRadius: Int? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("thumbnail_width") val thumbnailWidth: Int? = null,
    @get:JsonProperty("thumbnail_height") val thumbnailHeight: Int? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "location"
}

public data class InlineQueryResultVenue(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("address") val address: String,
    @get:JsonProperty("foursquare_id") val foursquareId: String? = null,
    @get:JsonProperty("foursquare_type") val foursquareType: String? = null,
    @get:JsonProperty("google_place_id") val googlePlaceId: String? = null,
    @get:JsonProperty("google_place_type") val googlePlaceType: String? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("thumbnail_width") val thumbnailWidth: Int? = null,
    @get:JsonProperty("thumbnail_height") val thumbnailHeight: Int? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "venue"
}

public data class InlineQueryResultContact(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("phone_number") val phoneNumber: String,
    @get:JsonProperty("first_name") val firstName: String,
    @get:JsonProperty("last_name") val lastName: String? = null,
    @get:JsonProperty("vcard") val vcard: String? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
    @get:JsonProperty("thumbnail_url") val thumbnailUrl: String? = null,
    @get:JsonProperty("thumbnail_width") val thumbnailWidth: Int? = null,
    @get:JsonProperty("thumbnail_height") val thumbnailHeight: Int? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "contact"
}

public data class InlineQueryResultGame(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("game_short_name") val gameShortName: String,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "game"
}

public data class InlineQueryResultCachedPhoto(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("photo_file_id") val photoFileId: String,
    @get:JsonProperty("title") val title: String? = null,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "photo"
}

public data class InlineQueryResultCachedGif(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("gif_file_id") val gifFileId: String,
    @get:JsonProperty("title") val title: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "gif"
}

public data class InlineQueryResultCachedMpeg4Gif(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("mpeg4_file_id") val mpeg4FileId: String,
    @get:JsonProperty("title") val title: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "mpeg4_gif"
}

public data class InlineQueryResultCachedSticker(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("sticker_file_id") val stickerFileId: String,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "sticker"
}

public data class InlineQueryResultCachedDocument(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("document_file_id") val documentFileId: String,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "document"
}

public data class InlineQueryResultCachedVideo(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("video_file_id") val videoFileId: String,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "video"
}

public data class InlineQueryResultCachedVoice(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("voice_file_id") val voiceFileId: String,
    @get:JsonProperty("description") val description: String? = null,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "voice"
}

public data class InlineQueryResultCachedAudio(
    @get:JsonProperty("id") val id: String,
    @get:JsonProperty("audio_file_id") val audioFileId: String,
    @get:JsonProperty("caption") val caption: String? = null,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("reply_markup") val replyMarkup: InlineKeyboardMarkup? = null,
    @get:JsonProperty("input_message_content") val inputMessageContent: InputMessageContent? = null,
) : InlineQueryResult() {
    @get:JsonProperty("type")
    override val type: String = "audio"
}

public data class InlineQueryResultsButton(
    @get:JsonProperty("text") val text: String,
    @get:JsonProperty("web_app") val webApp: WebAppInfo? = null,
    @get:JsonProperty("start_parameter") val startParameter: String? = null,
)

public sealed class InputMessageContent

public data class InputTextMessageContent(
    @get:JsonProperty("message_text") val messageText: String,
    @get:JsonProperty("parse_mode") val parseMode: String? = null,
    @get:JsonProperty("caption_entities") val captionEntities: List<MessageEntity>? = null,
    @get:JsonProperty("disable_web_page_preview") val disableWebPagePreview: Boolean? = null,
) : InputMessageContent()

public data class InputLocationMessageContent(
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("horizontal_accuracy") val horizontalAccuracy: Float? = null,
    @get:JsonProperty("live_period") val livePeriod: Int? = null,
    @get:JsonProperty("heading") val heading: Int? = null,
    @get:JsonProperty("proximity_alert_radius") val proximityAlertRadius: Int? = null,
) : InputMessageContent()

public data class InputVenueMessageContent(
    @get:JsonProperty("latitude") val latitude: Float,
    @get:JsonProperty("longitude") val longitude: Float,
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("address") val address: String,
    @get:JsonProperty("foursquare_id") val foursquareId: String? = null,
    @get:JsonProperty("foursquare_type") val foursquareType: String? = null,
    @get:JsonProperty("google_place_id") val googlePlaceId: String? = null,
    @get:JsonProperty("google_place_type") val googlePlaceType: String? = null,
) : InputMessageContent()

public data class InputContactMessageContent(
    @get:JsonProperty("phone_number") val phoneNumber: String,
    @get:JsonProperty("first_name") val firstName: String,
    @get:JsonProperty("last_name") val lastName: String? = null,
    @get:JsonProperty("vcard") val vcard: String? = null,
) : InputMessageContent()

public data class InputInvoiceMessageContent(
    @get:JsonProperty("title") val title: String,
    @get:JsonProperty("description") val description: String,
    @get:JsonProperty("payload") val payload: String,
    @get:JsonProperty("provider_token") val providerToken: String,
    @get:JsonProperty("currency") val currency: String,
    @get:JsonProperty("prices") val prices: List<LabeledPrice>,
    @get:JsonProperty("max_tip_amount") val maxTipAmount: Long? = null,
    @get:JsonProperty("suggested_tip_amounts") val suggestedTipAmounts: Long? = null,
    @get:JsonProperty("provider_data") val providerData: String? = null,
    @get:JsonProperty("photo_url") val photoUrl: String? = null,
    @get:JsonProperty("photo_size") val photoSize: Int? = null,
    @get:JsonProperty("photo_width") val photoWidth: Int? = null,
    @get:JsonProperty("photo_height") val photoHeight: Int? = null,
    @get:JsonProperty("need_name") val needName: Boolean? = null,
    @get:JsonProperty("need_phone_number") val needPhoneNumber: Boolean? = null,
    @get:JsonProperty("need_email") val needEmail: Boolean? = null,
    @get:JsonProperty("need_shipping_address") val needShippingAddress: Boolean? = null,
    @get:JsonProperty("send_phone_number_to_provider") val sendPhoneNumberToProvider: Boolean? = null,
    @get:JsonProperty("send_email_to_provider") val sendEmailToProvider: Boolean? = null,
    @get:JsonProperty("is_flexible") val isFlexible: Boolean? = null,
) : InputMessageContent()

public data class ChosenInlineResult(
    @param:JsonProperty("result_id") val resultId: String,
    @param:JsonProperty("from") val from: User,
    @param:JsonProperty("location") val location: Location? = null,
    @param:JsonProperty("inline_message_id") val inlineMessageId: String? = null,
    @param:JsonProperty("query") val query: String,
)