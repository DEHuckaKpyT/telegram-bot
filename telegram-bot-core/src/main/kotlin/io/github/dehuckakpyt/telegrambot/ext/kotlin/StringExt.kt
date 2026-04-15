package io.github.dehuckakpyt.telegrambot.ext.kotlin

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import java.util.*


/**
 * @author Denis Matytsin
 */
private val translator = PropertyNamingStrategies.KebabCaseStrategy()

/** Converts camelCase/PascalCase string to kebab-case. */
public fun String.toKebabCase(): String = translator.translate(this)

/** Parses UUID from canonical string representation. */
public fun String.toUUID(): UUID = UUID.fromString(this)

/** Ensures that string starts with [prefix], adding it if needed. */
public fun String.ensurePrefix(prefix: String): String = if (startsWith(prefix)) this else prefix + this
