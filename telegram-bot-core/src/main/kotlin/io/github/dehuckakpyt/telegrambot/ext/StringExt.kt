package io.github.dehuckakpyt.telegrambot.ext

import com.fasterxml.jackson.databind.PropertyNamingStrategies


/**
 * Created on 18.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
private val translator = PropertyNamingStrategies.KebabCaseStrategy()
internal fun String.toKebabCase(): String = translator.translate(this)