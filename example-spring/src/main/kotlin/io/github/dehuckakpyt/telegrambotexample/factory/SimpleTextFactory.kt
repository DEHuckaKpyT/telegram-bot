package io.github.dehuckakpyt.telegrambotexample.factory

import org.springframework.stereotype.Component


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Component
class SimpleTextFactory {
    fun getSimpleText(): String = "Simple text from dependency"
}