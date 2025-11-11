package io.github.dehuckakpyt.telegrambotexample.config

import io.github.dehuckakpyt.telegrambotexample.holder.MessageTemplateHolder
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * Created on 23.07.2024.
 *
 * @author Denis Matytsin
 */
@Configuration
@EnableConfigurationProperties(MessageTemplateHolder::class)
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambotexample.model"])
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambotexample.repository"])
class AppConfig