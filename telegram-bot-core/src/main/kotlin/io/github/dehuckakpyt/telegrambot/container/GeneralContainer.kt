package io.github.dehuckakpyt.telegrambot.container


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class GeneralContainer(
    public override val step: String?,
    internal val content: String?,
) : Container {
    internal var nextStep: String? = null
    internal var nextStepInstance: Any? = null
}