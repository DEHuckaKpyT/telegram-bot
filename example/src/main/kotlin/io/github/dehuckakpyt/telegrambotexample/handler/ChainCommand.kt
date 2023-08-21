package io.github.dehuckakpyt.telegrambotexample.handler

import com.dehucka.microservice.exception.CustomBadRequestException
import io.github.dehuckakpyt.telegrambot.BotHandling
import io.github.dehuckakpyt.telegrambotexample.template.chain
import io.github.dehuckakpyt.telegrambotexample.template.chainEnd
import io.github.dehuckakpyt.telegrambotexample.template.chainOneMore
import io.github.dehuckakpyt.telegrambotexample.template.chainStartSum

fun BotHandling.chainCommand() {
    // next - указание следующего шага
    command("/chain", next = "get target") {
        // чтобы отправить сообщение, можно указать chatId (в этом контексте chatId - чат, в котором пришло сообщение)
        // chain - шаблон сообщения, заданный val BotHandling.chain by template()
        sendMessage(chatId, chain)
    }

    step("get target", next = "sum numbers") {
        // исключения наследуемые от CustomException выведутся пользователю
        val target = text.toIntOrNull() ?: throw CustomBadRequestException("Ожидается целое число")
        if (target < 1) throw CustomBadRequestException("Ожидается положительное число")

        // метод with подставляет в шаблон chainStartSum значение переменной target (с помощью FreeMarker)
        sendMessage(chainStartSum with ("target" to target))
        // метод transferToNext передаёт переменную в следующий шаг
        transferToNext(target to 0)
    }

    // указать следующий шаг можно с помощью метода next()
    // если следующий шаг не задан в параметре и методом, то цепочка оборвётся
    step("sum numbers") {
        val value = text.toIntOrNull() ?: throw CustomBadRequestException("Ожидается целое число")
        // метод transferred позволяет получить переданную переменную из предыдущего шага
        var (target, sum) = transferred<Pair<Int, Int>>()
        sum += value

        if (sum < target) {
            sendMessage(chainOneMore with mapOf("sum" to sum, "target" to target))
            // метод next указывает следующий шаг цепочки
            next("sum numbers", target to sum)
            return@step
        }

        sendMessage(chainEnd with mapOf("sum" to sum, "target" to target))
    }
}