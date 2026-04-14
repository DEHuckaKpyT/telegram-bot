package io.github.dehuckakpyt.telegrambot.ext.kotlin

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import java.util.*


/**
 * @author Denis Matytsin
 */
private val translator = PropertyNamingStrategies.KebabCaseStrategy()

public fun String.toKebabCase(): String = translator.translate(this)

public fun String.toUUID(): UUID = UUID.fromString(this)

public fun String.ensurePrefix(prefix: String): String = if (startsWith(prefix)) this else prefix + this
