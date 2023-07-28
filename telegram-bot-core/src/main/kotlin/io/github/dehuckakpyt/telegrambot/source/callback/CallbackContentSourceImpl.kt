package io.github.dehuckakpyt.telegrambot.source.callback

import com.dehucka.exposed.ext.execute
import com.dehucka.exposed.ext.read
import com.dehucka.microservice.exception.CustomException
import io.github.dehuckakpyt.telegrambot.model.CallbackContent
import java.util.*

class CallbackContentSourceImpl : CallbackContentSource {

    override suspend fun save(content: String): CallbackContent = execute {
        CallbackContent.new {
            this.content = content
        }
    }

    override suspend fun get(id: UUID): CallbackContent = read {
        CallbackContent.findById(id)
            ?: throw CustomException("Содержание для callback'а не найдено :(")
    }
}