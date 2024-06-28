package io.github.dehuckakpyt.telegrambot.formatter


/**
 * Created on 07.10.2023.
 *
 * Formatter for clean/escape html tags from string.
 *
 * @author Denis Matytsin
 */
interface HtmlFormatter {

    /**
     * Clean html string.
     *
     * @param text text with html tags
     *
     * @return text with allowed tags for telegram formatting
     */
    fun cleanHtml(text: String): String

    /**
     * Escape html string.
     *
     * @param text text with html tags
     *
     * @return text with escaped tags
     */
    fun escapeHtml(text: String): String
}