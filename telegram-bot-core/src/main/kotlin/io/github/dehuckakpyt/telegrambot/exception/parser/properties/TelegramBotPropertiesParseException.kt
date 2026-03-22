package io.github.dehuckakpyt.telegrambot.exception.parser.properties

class TelegramBotPropertiesParseException(
    val reason: Reason,
    val path: String? = null,
    val placeholder: String? = null,
    details: String? = null,
    cause: Throwable? = null,
) : IllegalArgumentException(buildMessage(reason, path, placeholder, details), cause) {

    enum class Reason {
        MISSING_PLACEHOLDER_VALUE,
        CYCLIC_REFERENCE,
        COMPLEX_REFERENCE_NOT_SUPPORTED,
        INVALID_DURATION,
    }

    companion object {
        private fun buildMessage(
            reason: Reason,
            path: String?,
            placeholder: String?,
            details: String?,
        ): String = when (reason) {
            Reason.CYCLIC_REFERENCE -> {
                val where = path ?: "<root>"
                val ref = placeholder ?: "<unknown>"
                "Cyclic placeholder reference detected for '$ref' while resolving '$where'."
            }
            else -> buildString {
                append("Failed to parse TelegramBotProperties")
                append(": reason=").append(reason.name)
                if (path != null) append(", path='").append(path).append("'")
                if (placeholder != null) append(", placeholder='").append(placeholder).append("'")
                if (details != null) append(", details='").append(details).append("'")
            }
        }
    }
}