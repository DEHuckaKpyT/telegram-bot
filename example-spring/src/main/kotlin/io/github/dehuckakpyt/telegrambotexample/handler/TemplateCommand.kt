package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.handling.BotHandling


/**
 * Created on 07.10.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun BotHandling.templateCommand() {

    val usingTags = "so>me <b><u>value</u></b> <b>bold</b> and <u>underline</u>"
    val ignoreTags = "so>me<center>value</center> <h1>value</h1> <img src=\"image.jpg\" alt=\"alt\">"
    val breakLine = "te&xt<br>next line<p>new line"
    val mixed = "<b><u>formatted</u></b> <center>ignored</center><br>new line"

    val templateExample = "formatted text: \${cleanHtml(param)}"

    command("/template") {
        sendMessage(templateExample with ("param" to usingTags), parseMode = "HTML")
        sendMessage(templateExample with ("param" to ignoreTags), parseMode = "HTML")
        sendMessage(templateExample with ("param" to breakLine), parseMode = "HTML")
        sendMessage(templateExample with ("param" to mixed), parseMode = "HTML")
    }

    val templateEscapedExample = "formatted text: \${escapeHtml(param)}"

    command("/template_escaped") {
        sendMessage(templateEscapedExample with ("param" to usingTags), parseMode = "HTML")
        sendMessage(templateEscapedExample with ("param" to ignoreTags), parseMode = "HTML")
        sendMessage(templateEscapedExample with ("param" to breakLine), parseMode = "HTML")
        sendMessage(templateEscapedExample with ("param" to mixed), parseMode = "HTML")
    }
}