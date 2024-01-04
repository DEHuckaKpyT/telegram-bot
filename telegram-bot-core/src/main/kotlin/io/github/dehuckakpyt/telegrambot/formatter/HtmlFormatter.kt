package io.github.dehuckakpyt.telegrambot.formatter


/**
 * Created on 07.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface HtmlFormatter {
    fun cleanHtml(text: String): String
    fun escapeHtml(text: String): String
}