package io.github.dehuckakpyt.telegrambot.model.telegram

import kotlin.String

/**
 * This object represents an error in the Telegram Passport element which was submitted that should
 * be resolved by the user. It should be one of:
 *
 * *
 * [PassportElementErrorDataField](https://core.telegram.org/bots/api/#passportelementerrordatafield)
 * *
 * [PassportElementErrorFrontSide](https://core.telegram.org/bots/api/#passportelementerrorfrontside)
 * *
 * [PassportElementErrorReverseSide](https://core.telegram.org/bots/api/#passportelementerrorreverseside)
 * * [PassportElementErrorSelfie](https://core.telegram.org/bots/api/#passportelementerrorselfie)
 * * [PassportElementErrorFile](https://core.telegram.org/bots/api/#passportelementerrorfile)
 * * [PassportElementErrorFiles](https://core.telegram.org/bots/api/#passportelementerrorfiles)
 * *
 * [PassportElementErrorTranslationFile](https://core.telegram.org/bots/api/#passportelementerrortranslationfile)
 * *
 * [PassportElementErrorTranslationFiles](https://core.telegram.org/bots/api/#passportelementerrortranslationfiles)
 * *
 * [PassportElementErrorUnspecified](https://core.telegram.org/bots/api/#passportelementerrorunspecified)
 *
 * @see [PassportElementError] (https://core.telegram.org/bots/api/#passportelementerror)
 *
 * @author KScript
 */
public sealed interface PassportElementError {
    public val source: String

    public val type: String

    public val message: String
}
