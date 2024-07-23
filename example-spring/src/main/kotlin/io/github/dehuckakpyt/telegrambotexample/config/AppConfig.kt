package io.github.dehuckakpyt.telegrambotexample.config

import io.github.dehuckakpyt.telegrambotexample.holder.MessageTemplateHolder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


/**
 * Created on 23.07.2024.
 *
 * @author Denis Matytsin
 */
@Configuration
@EnableConfigurationProperties(MessageTemplateHolder::class)
class AppConfig