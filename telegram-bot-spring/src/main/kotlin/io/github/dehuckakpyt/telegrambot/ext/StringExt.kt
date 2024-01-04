package io.github.dehuckakpyt.telegrambot.ext

import com.fasterxml.jackson.databind.PropertyNamingStrategies


/**
 * Created on 25.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal fun String.toKebabCase(): String =
    PropertyNamingStrategies.KebabCaseStrategy.INSTANCE.translate(this)