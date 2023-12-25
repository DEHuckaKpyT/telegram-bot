package io.github.dehuckakpyt.telegrambotexample.factory

import org.koin.core.annotation.Single


/**
 * Created on 24.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Single
class SimpleTextFactory {
    fun getSimpleText(): String = "Simple text from dependency"
}