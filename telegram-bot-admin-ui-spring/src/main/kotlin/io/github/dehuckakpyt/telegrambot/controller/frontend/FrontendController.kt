package io.github.dehuckakpyt.telegrambot.controller.frontend

import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.TEXT_HTML_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Denis Matytsin
 */
@RestController
class FrontendController {
    private val indexHtml = ClassPathResource("/static/index.html")

    @GetMapping("\${telegram-bot.administration.admin-panel.prefix:/admin-ui}/**", produces = [TEXT_HTML_VALUE])
    suspend fun forward(): ClassPathResource = indexHtml
}