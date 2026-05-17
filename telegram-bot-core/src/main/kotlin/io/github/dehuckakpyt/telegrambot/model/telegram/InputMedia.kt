package io.github.dehuckakpyt.telegrambot.model.telegram

import io.github.dehuckakpyt.telegrambot.model.telegram.input.Input
import kotlin.String
import kotlin.collections.List

/**
 * This object represents the content of a media message to be sent. It should be one of
 *
 * * [InputMediaAnimation](https://core.telegram.org/bots/api/#inputmediaanimation)
 * * [InputMediaAudio](https://core.telegram.org/bots/api/#inputmediaaudio)
 * * [InputMediaDocument](https://core.telegram.org/bots/api/#inputmediadocument)
 * * [InputMediaLivePhoto](https://core.telegram.org/bots/api/#inputmedialivephoto)
 * * [InputMediaPhoto](https://core.telegram.org/bots/api/#inputmediaphoto)
 * * [InputMediaVideo](https://core.telegram.org/bots/api/#inputmediavideo)
 *
 * @see [InputMedia] (https://core.telegram.org/bots/api/#inputmedia)
 *
 * @author KScript
 */
public sealed interface InputMedia {
    public val type: String

    public val media: Input

    public val thumbnail: Input?

    public val caption: String?

    public val parseMode: String?

    public val captionEntities: List<MessageEntity>?

    public val photo: Input?

    public val cover: Input?
}
