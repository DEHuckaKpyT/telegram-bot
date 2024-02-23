package io.github.dehuckakpyt.telegrambot.annotation

import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.lang.annotation.Inherited


/**
 * Created on 29.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Component
@Scope(value = SCOPE_PROTOTYPE)
annotation class HandlerComponent
