package io.github.dehuckakpyt.telegrambot.template

import freemarker.template.Configuration
import freemarker.template.Template
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.io.StringWriter


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Templating {
    infix fun String.with(pair: Pair<String, Any>): String = buildTemplate(this, mapOf(pair))

    fun String.with(vararg pairs: Pair<String, Any>): String = buildTemplate(this, mapOf(*pairs))

    infix fun String.with(instance: Any): String = buildTemplate(this, instance)

    companion object : KoinComponent {
        private val templateConfiguration = get<Configuration>()

        fun buildTemplate(template: String, instance: Any): String {
            val writer = StringWriter()

            try {
                val markerTemplate = Template("template", template, templateConfiguration)
                markerTemplate.process(instance, writer)
            } catch (exc: Exception) {
                throw RuntimeException(exc)
            }

            return writer.toString()
        }
    }
}