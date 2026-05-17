package io.github.dehuckakpyt.telegrambot.model.telegram

import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.String

/**
 * This object represents the content of a poll description or a quiz explanation to be sent. It
 * should be one of
 *
 * * [InputMediaAnimation](https://core.telegram.org/bots/api/#inputmediaanimation)
 * * [InputMediaAudio](https://core.telegram.org/bots/api/#inputmediaaudio)
 * * [InputMediaDocument](https://core.telegram.org/bots/api/#inputmediadocument)
 * * [InputMediaLivePhoto](https://core.telegram.org/bots/api/#inputmedialivephoto)
 * * [InputMediaLocation](https://core.telegram.org/bots/api/#inputmedialocation)
 * * [InputMediaPhoto](https://core.telegram.org/bots/api/#inputmediaphoto)
 * * [InputMediaVenue](https://core.telegram.org/bots/api/#inputmediavenue)
 * * [InputMediaVideo](https://core.telegram.org/bots/api/#inputmediavideo)
 *
 * @see [InputPollMedia] (https://core.telegram.org/bots/api/#inputpollmedia)
 *
 * @author KScript
 */
public sealed interface InputPollMedia {
    public val type: String

    public val media: Input?

    public val thumbnail: Input?

    public val photo: Input?

    public val cover: Input?
}
