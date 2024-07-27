package io.github.dehuckakpyt.telegrambot.template.formatter

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Document.OutputSettings
import org.jsoup.nodes.Entities
import org.jsoup.safety.Safelist


/**
 * Created on 07.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class HtmlFormatterImpl(
    // Allowed tags described in documentation https://core.telegram.org/bots/api#html-style
    allowedTags: Array<String> = arrayOf("b", "strong", "i", "em", "u", "ins", "s", "strike", "del", "a", "code", "pre"),
    allowedAttributes: Map<String, Array<String>> = mapOf("a" to arrayOf("href")),
) : HtmlFormatter {
    protected val safelist: Safelist = Safelist()
    protected val settings: OutputSettings = OutputSettings().prettyPrint(false)

    init {
        safelist.addTags(*allowedTags)
        allowedAttributes.forEach { (tag, attributes) ->
            safelist.addAttributes(tag, *attributes)
        }
    }

    override fun cleanHtml(text: String): String {
        val document: Document = Jsoup.parse(text)
        document.outputSettings(settings)

        document.select("br").before("\n")
        document.select("p").before("\n")
        document.select("h1").before("\n")
        document.select("h2").before("\n")
        document.select("h3").before("\n")
        document.select("h4").before("\n")
        document.select("h5").before("\n")
        document.select("h6").before("\n")
        document.select("blockquote").before("\n")
        document.select("li").before("\n- ")

        return Jsoup.clean(document.html(), "", safelist, settings)
    }

    override fun escapeHtml(text: String): String = Entities.escape(text)
}