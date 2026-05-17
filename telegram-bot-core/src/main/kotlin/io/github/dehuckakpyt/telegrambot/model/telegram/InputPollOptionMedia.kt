package io.github.dehuckakpyt.telegrambot.model.telegram

import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.String

/**
 * This object represents the content of a poll option to be sent. It should be one of
 *
 * * [InputMediaAnimation](https://core.telegram.org/bots/api/#inputmediaanimation)
 * * [InputMediaLivePhoto](https://core.telegram.org/bots/api/#inputmedialivephoto)
 * * [InputMediaLocation](https://core.telegram.org/bots/api/#inputmedialocation)
 * * [InputMediaPhoto](https://core.telegram.org/bots/api/#inputmediaphoto)
 * * [InputMediaSticker](https://core.telegram.org/bots/api/#inputmediasticker)
 * * [InputMediaVenue](https://core.telegram.org/bots/api/#inputmediavenue)
 * * [InputMediaVideo](https://core.telegram.org/bots/api/#inputmediavideo)
 *
 * @see [InputPollOptionMedia] (https://core.telegram.org/bots/api/#inputpolloptionmedia)
 *
 * @author KScript
 */
public sealed interface InputPollOptionMedia {
    public val type: String

    public val media: Input?

    public val thumbnail: Input?

    public val photo: Input?

    public val cover: Input?
}
