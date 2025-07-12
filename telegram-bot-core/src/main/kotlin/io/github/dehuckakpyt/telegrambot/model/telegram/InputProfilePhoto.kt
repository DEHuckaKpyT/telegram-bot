package io.github.dehuckakpyt.telegrambot.model.telegram

import kotlin.String

/**
 * This object describes a profile photo to set. Currently, it can be one of
 *
 * * [InputProfilePhotoStatic](https://core.telegram.org/bots/api/#inputprofilephotostatic)
 * * [InputProfilePhotoAnimated](https://core.telegram.org/bots/api/#inputprofilephotoanimated)
 *
 * @see [InputProfilePhoto] (https://core.telegram.org/bots/api/#inputprofilephoto)
 *
 * @author KScript
 */
public sealed interface InputProfilePhoto {
    public val type: String
}
