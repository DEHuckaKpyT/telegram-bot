package io.github.dehuckakpyt.telegrambot.template

import org.koin.mp.KoinPlatform.getKoin


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Templating {
    companion object : Templater by getKoin().get()
}