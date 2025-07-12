package io.github.dehuckakpyt.telegrambot.model.telegram

import kotlin.String

/**
 * This object describes the content of a story to post. Currently, it can be one of
 *
 * * [InputStoryContentPhoto](https://core.telegram.org/bots/api/#inputstorycontentphoto)
 * * [InputStoryContentVideo](https://core.telegram.org/bots/api/#inputstorycontentvideo)
 *
 * @see [InputStoryContent] (https://core.telegram.org/bots/api/#inputstorycontent)
 *
 * @author KScript
 */
public sealed interface InputStoryContent {
    public val type: String
}
