package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.collections.List

/**
 * This object contains information about a message that is being replied to, which may come from
 * another chat or forum topic.
 *
 * @see [ExternalReplyInfo] (https://core.telegram.org/bots/api/#externalreplyinfo)
 *
 * @author KScript
 *
 * @param origin Origin of the message replied to by the given message
 * @param chat *Optional*. Chat the original message belongs to. Available only if the chat is a
 * supergroup or a channel.
 * @param messageId *Optional*. Unique message identifier inside the original chat. Available only
 * if the original chat is a supergroup or a channel.
 * @param linkPreviewOptions *Optional*. Options used for link preview generation for the original
 * message, if it is a text message
 * @param animation *Optional*. Message is an animation, information about the animation
 * @param audio *Optional*. Message is an audio file, information about the file
 * @param document *Optional*. Message is a general file, information about the file
 * @param paidMedia *Optional*. Message contains paid media; information about the paid media
 * @param photo *Optional*. Message is a photo, available sizes of the photo
 * @param sticker *Optional*. Message is a sticker, information about the sticker
 * @param story *Optional*. Message is a forwarded story
 * @param video *Optional*. Message is a video, information about the video
 * @param videoNote *Optional*. Message is a [video
 * note](https://telegram.org/blog/video-messages-and-telescope), information about the video message
 * @param voice *Optional*. Message is a voice message, information about the file
 * @param hasMediaSpoiler *Optional*. *True*, if the message media is covered by a spoiler animation
 * @param contact *Optional*. Message is a shared contact, information about the contact
 * @param dice *Optional*. Message is a dice with random value
 * @param game *Optional*. Message is a game, information about the game. [More about games
 * ](https://core.telegram.org/bots/api/#games)
 * @param giveaway *Optional*. Message is a scheduled giveaway, information about the giveaway
 * @param giveawayWinners *Optional*. A giveaway with public winners was completed
 * @param invoice *Optional*. Message is an invoice for a
 * [payment](https://core.telegram.org/bots/api/#payments), information about the invoice. [More about
 * payments ](https://core.telegram.org/bots/api/#payments)
 * @param location *Optional*. Message is a shared location, information about the location
 * @param poll *Optional*. Message is a native poll, information about the poll
 * @param venue *Optional*. Message is a venue, information about the venue
 */
public data class ExternalReplyInfo(
    /**
     * Origin of the message replied to by the given message
     */
    @get:JsonProperty("origin")
    @param:JsonProperty("origin")
    public val origin: MessageOrigin,
    /**
     * *Optional*. Chat the original message belongs to. Available only if the chat is a supergroup
     * or a channel.
     */
    @get:JsonProperty("chat")
    @param:JsonProperty("chat")
    public val chat: Chat? = null,
    /**
     * *Optional*. Unique message identifier inside the original chat. Available only if the
     * original chat is a supergroup or a channel.
     */
    @get:JsonProperty("message_id")
    @param:JsonProperty("message_id")
    public val messageId: Long? = null,
    /**
     * *Optional*. Options used for link preview generation for the original message, if it is a
     * text message
     */
    @get:JsonProperty("link_preview_options")
    @param:JsonProperty("link_preview_options")
    public val linkPreviewOptions: LinkPreviewOptions? = null,
    /**
     * *Optional*. Message is an animation, information about the animation
     */
    @get:JsonProperty("animation")
    @param:JsonProperty("animation")
    public val animation: Animation? = null,
    /**
     * *Optional*. Message is an audio file, information about the file
     */
    @get:JsonProperty("audio")
    @param:JsonProperty("audio")
    public val audio: Audio? = null,
    /**
     * *Optional*. Message is a general file, information about the file
     */
    @get:JsonProperty("document")
    @param:JsonProperty("document")
    public val document: Document? = null,
    /**
     * *Optional*. Message contains paid media; information about the paid media
     */
    @get:JsonProperty("paid_media")
    @param:JsonProperty("paid_media")
    public val paidMedia: PaidMediaInfo? = null,
    /**
     * *Optional*. Message is a photo, available sizes of the photo
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: List<PhotoSize>? = null,
    /**
     * *Optional*. Message is a sticker, information about the sticker
     */
    @get:JsonProperty("sticker")
    @param:JsonProperty("sticker")
    public val sticker: Sticker? = null,
    /**
     * *Optional*. Message is a forwarded story
     */
    @get:JsonProperty("story")
    @param:JsonProperty("story")
    public val story: Story? = null,
    /**
     * *Optional*. Message is a video, information about the video
     */
    @get:JsonProperty("video")
    @param:JsonProperty("video")
    public val video: Video? = null,
    /**
     * *Optional*. Message is a [video
     * note](https://telegram.org/blog/video-messages-and-telescope), information about the video
     * message
     */
    @get:JsonProperty("video_note")
    @param:JsonProperty("video_note")
    public val videoNote: VideoNote? = null,
    /**
     * *Optional*. Message is a voice message, information about the file
     */
    @get:JsonProperty("voice")
    @param:JsonProperty("voice")
    public val voice: Voice? = null,
    /**
     * *Optional*. *True*, if the message media is covered by a spoiler animation
     */
    @get:JsonProperty("has_media_spoiler")
    @param:JsonProperty("has_media_spoiler")
    public val hasMediaSpoiler: Boolean? = null,
    /**
     * *Optional*. Message is a shared contact, information about the contact
     */
    @get:JsonProperty("contact")
    @param:JsonProperty("contact")
    public val contact: Contact? = null,
    /**
     * *Optional*. Message is a dice with random value
     */
    @get:JsonProperty("dice")
    @param:JsonProperty("dice")
    public val dice: Dice? = null,
    /**
     * *Optional*. Message is a game, information about the game. [More about games
     * ](https://core.telegram.org/bots/api/#games)
     */
    @get:JsonProperty("game")
    @param:JsonProperty("game")
    public val game: Game? = null,
    /**
     * *Optional*. Message is a scheduled giveaway, information about the giveaway
     */
    @get:JsonProperty("giveaway")
    @param:JsonProperty("giveaway")
    public val giveaway: Giveaway? = null,
    /**
     * *Optional*. A giveaway with public winners was completed
     */
    @get:JsonProperty("giveaway_winners")
    @param:JsonProperty("giveaway_winners")
    public val giveawayWinners: GiveawayWinners? = null,
    /**
     * *Optional*. Message is an invoice for a
     * [payment](https://core.telegram.org/bots/api/#payments), information about the invoice. [More
     * about payments ](https://core.telegram.org/bots/api/#payments)
     */
    @get:JsonProperty("invoice")
    @param:JsonProperty("invoice")
    public val invoice: Invoice? = null,
    /**
     * *Optional*. Message is a shared location, information about the location
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: Location? = null,
    /**
     * *Optional*. Message is a native poll, information about the poll
     */
    @get:JsonProperty("poll")
    @param:JsonProperty("poll")
    public val poll: Poll? = null,
    /**
     * *Optional*. Message is a venue, information about the venue
     */
    @get:JsonProperty("venue")
    @param:JsonProperty("venue")
    public val venue: Venue? = null,
)
