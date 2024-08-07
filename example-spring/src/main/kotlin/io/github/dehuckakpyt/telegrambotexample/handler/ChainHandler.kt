package io.github.dehuckakpyt.telegrambotexample.handler

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.ext.container.chatId
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import io.github.dehuckakpyt.telegrambotexample.holder.MessageTemplateHolder

@HandlerComponent
class ChainHandler(
    template: MessageTemplateHolder,
) : BotHandler({

    val startSum = 0

    // next - указание следующего шага
    command("/chain", next = "get target") {
        // чтобы отправить сообщение, можно указать chatId (в этом контексте chatId - чат, в котором пришло сообщение)
        // chain - шаблон сообщения, заданный val BotHandling.chain by template()
        bot.sendMessage(chatId, template.chain)
    }

    step("get target", next = "sum numbers") {
        // исключения ChatException выведутся пользователю
        val target = text.toIntOrNull() ?: throw ChatException("Ожидается целое число")
        if (target < 1) throw ChatException("Ожидается положительное число")

        // метод with подставляет в шаблон chainStartSum значение переменной target (с помощью FreeMarker)
        sendMessage(template.chainStartSum with ("target" to target))
        // метод transferToNext передаёт переменную в следующий шаг
        transfer(target to startSum)
    }

    // указать следующий шаг можно с помощью метода next()
    // если следующий шаг не задан в параметре и методом, то цепочка оборвётся
    step("sum numbers") {
        val value = text.toIntOrNull() ?: throw ChatException("Ожидается целое число")
        // метод transferred позволяет получить переданную переменную из предыдущего шага
        var (target, sum) = transferred<Pair<Int, Int>>()
        sum += value

        if (sum < target) {
            sendMessage(template.chainOneMore with mapOf("sum" to sum, "target" to target))
            // метод next указывает следующий шаг цепочки
            next("sum numbers", target to sum)
            return@step
        }

        sendMessage(template.chainEnd with mapOf("sum" to sum, "target" to target))
    }
})