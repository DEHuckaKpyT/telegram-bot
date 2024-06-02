package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object describes the way a background is filled based on the selected colors. Currently, it
 * can be one of
 *
 * * [BackgroundFillSolid](https://core.telegram.org/bots/api/#backgroundfillsolid)
 * * [BackgroundFillGradient](https://core.telegram.org/bots/api/#backgroundfillgradient)
 * *
 * [BackgroundFillFreeformGradient](https://core.telegram.org/bots/api/#backgroundfillfreeformgradient)
 *
 * @see [BackgroundFill] (https://core.telegram.org/bots/api/#backgroundfill)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = BackgroundFillSolid::class, name = "solid"),
    JsonSubTypes.Type(value = BackgroundFillGradient::class, name = "gradient"),
    JsonSubTypes.Type(value = BackgroundFillFreeformGradient::class, name = "freeform_gradient"),
)
public sealed interface BackgroundFill {
    public val type: String
}
