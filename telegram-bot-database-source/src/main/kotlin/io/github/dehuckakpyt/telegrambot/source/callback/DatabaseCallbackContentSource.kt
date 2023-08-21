package io.github.dehuckakpyt.telegrambot.source.callback

import com.dehucka.exposed.ext.execute
import com.dehucka.exposed.ext.read
import com.dehucka.microservice.exception.CustomException
import io.github.dehuckakpyt.telegrambot.model.CallbackContent
import io.github.dehuckakpyt.telegrambot.model.DatabaseCallbackContent
import java.util.*

class DatabaseCallbackContentSource : CallbackContentSource {

    override suspend fun save(content: String): CallbackContent = execute {
        DatabaseCallbackContent.new {
            this.content = content
        }
    }

    override suspend fun get(identifier: UUID): CallbackContent = read {
        DatabaseCallbackContent.findById(identifier)
            ?: throw CustomException("Содержание для callback'а не найдено :(")
    }
}