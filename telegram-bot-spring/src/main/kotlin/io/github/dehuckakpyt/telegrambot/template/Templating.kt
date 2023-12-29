package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.context.SpringContext.autowired


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Templating {
    companion object : Templater by autowired()
}