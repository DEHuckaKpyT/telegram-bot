# Kotlin Telegram Bot


Kotlin library for creating Telegram Bots. You can use clean version, with implementation for [Spring](https://spring.io/), [Ktor](https://ktor.io/)+[Koin](https://insert-koin.io/) or create with you own implementation.
Now it have also possibility to save state in database with [Exposed](https://github.com/JetBrains/Exposed).

Easy to handle dialogs with users. 
Supporting message templates and other helpful features.
Working on coroutines.

Example of applications in [example-spring](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-spring), [example-ktor](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-ktor), [example-core](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-core) directories. 

## Examples

Примеры построения диалогов:

Без фреймворков:
```kotlin
fun BotHandling.registrationHandler() {
    val phonePattern = Regex("^\\+?[78]?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}([\\s\\-]?\\d{2}){2}$")

    command("/register", next = "get_contact") {
        sendMessage("Для регистрации введите номер или поделитесь своим контактом", 
            replyMarkup = contactKeyboard("Поделиться контактом"))
    }

    step("get_contact", type = CONTACT) {
        sendMessage("${contact.firstName}, Вы успешно зарегистрировались по номеру ${contact.phoneNumber}!", 
            replyMarkup = removeKeyboard())
    }

    step("get_contact", type = TEXT, next = "get_firstname") {
        phonePattern.find(text) ?: throw ChatException("Неверный формат номера телефона")
        val phone: String = text

        sendMessage("Напишите, как к Вам обращаться", replyMarkup = removeKeyboard())
        transfer(phone)
    }

    step("get_firstname") {
        val phone = transferred<String>()
        sendMessage("${text}, Вы успешно зарегистрировались по номеру ${phone}!")
    }
}
```

С использованием Ktor + Koin:
```kotlin
@Factory
class PurchaseHandler(
    private val itemService: ItemService,
    private val feedbackService: FeedbackService,
) : BotHandler({
    command("/buy") {
        sendMessage("Выберите действие", replyMarkup = inlineKeyboard(
            callbackButton("Купить тапочки", next = "buy", content = "some id 1"),
            callbackButton("Купить шапочки", next = "buy", content = "some id 2"),
            callbackButton("Оставить отзыв", next = "get_feedback_intro")))
    }
    
    callback("buy") {
        val itemId = transferred<String>()
        val item = itemService.get(itemId)
        
        sendInvoice(
            title = item.title,
            description = item.description,
            payload = "...",
            providerToken = "...",
            currency = "rub",
            prices = item.prices
        )
    }
    
    callback("get_feedback_intro", next = "get_feedback") {
        sendMessage("Напишите, пожалуйста, что вы думаете о нашем приложении.")
    }
    
    step("get_feedback") {
        feedbackService.save(chatId, text)

        sendMessage("Спасибо за отзыв!")
        sendSticker(...)
    }
})
```

С использованием Spring:
```kotlin
@HandlerComponent
class GameChainHandler : BotHandler({

    val startSum = 0

    command("/chain", next = "get target") {
        bot.sendMessage(chatId, chain)
    }

    step("get target", next = "sum numbers") {
        val target = text.toIntOrNull() ?: throw ChatException("Ожидается целое число")
        if (target < 1) throw ChatException("Ожидается положительное число")

        sendMessage(chainStartSum with ("target" to target))
        transfer(target to startSum)
    }

    step("sum numbers") {
        val value = text.toIntOrNull() ?: throw ChatException("Ожидается целое число")
        var (target, sum) = transferred<Pair<Int, Int>>()
        sum += value

        if (sum < target) {
            sendMessage(chainOneMore with mapOf("sum" to sum, "target" to target))
            next("sum numbers", target to sum)
            return@step
        }

        sendMessage(chainEnd with mapOf("sum" to sum, "target" to target))
    }
})
```

Больше примеров [здесь](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-spring/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/handler)

## Get started

### Without frameworks

Необходимо всего лишь добавить одну зависимость, создать конфиг, указать token и создать контекст с ботом

```Gradle
repositories {
    mavenCentral()
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.6.0")
}
```
```kotlin
fun main(args: Array<String>): Unit {
    val config = TelegramBotConfig().apply {
        token = "<bot token required>"
        username = "<bot username required>"

        receiving {
            handling {
                startCommand()
                exceptionCommand()
            }
        }
    }

    val context = TelegramBotFactory.createTelegramBotContext(config)
    val bot = context.telegramBot
    val updateReceiver = context.updateReceiver

    updateReceiver.start()
    readlnOrNull()
    updateReceiver.stop()
}

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage("Hello! My name is \${botUsername} :-)" with ("botUsername" to bot.username))
    }
}

fun BotHandling.exceptionCommand() {
    command("/exception") {
        throw CustomException("text from exception message")
    }
}
```

### Ktor + Koin

Для запуска и конфигурирования бота необходимо
добавить зависимости, задать в конфигурации `telegram-bot.username` и `telegram-bot.token` и установить бота, как ktor-плагин:
```Gradle
repositories {
    mavenCentral()
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.6.0")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-ktor:0.6.0")
}
```
```kotlin
fun Application.configureTelegramBot() {
    install(TelegramBot) {
        handling {
            // регистрация обработчиков 
            startCommand()
        }
    }
}

@Factory
class StartHandler : BotHandler({
    val friendChatId = 123L
    command("/start") {
        sendMessage("Привет, меня зовут ${bot.username} :-)")
        bot.sendMessage(friendChatId, "И тебе привет)")
    }
})
```
*Также нужно не забыть установить плагин с Koin ([пример](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example-ktor/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/plugin/DependencyInjection.kt)) до инициализации бота.


### Spring

Для запуска и конфигурирования бота необходимо
добавить зависимости, задать в конфигурации `telegram-bot.username` и `telegram-bot.token` и повесить аннотацию `@EnableTelegramBot`:
```Gradle
repositories {
    mavenCentral()
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.6.0")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:0.6.0")
}
```
```kotlin
@EnableTelegramBot
@Configuration
class BotConfig

@HandlerComponent
class StartHandler : BotHandler({
    val friendChatId = 123L
    command("/start") {
        sendMessage("Привет, меня зовут ${bot.username} :-)")
        bot.sendMessage(friendChatId, "И тебе привет)")
    }
})
```
Для настройки бота можно добавить бин:
```kotlin
@EnableTelegramBot
@Configuration
class BotConfig{

    @Bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        receiving {
            exceptionHandler = { CustomExceptionHandler(telegramBot, receiving.messageTemplate, templating.templater) }
        }
    }
}
```

## Цепочки сообщений

Самое ценное в библиотеке - удобное построение диалога с пользователем (далее - цепочка). 

### С помощью методов расширения

Создание цепочек осуществляется с помощью метода расширения у класса `BotHandling`:
```kotlin
fun BotHandling.startCommand() {
    // здесь будут обработчики команд и других событий
}
```

Сообщение, с которого начинается цепочка - это команда. Она обозначается с помощью метода `command()`.
```kotlin
fun BotHandling.startCommand() {
    command("/start") {
    // здесь действия при вызове команды /start
    }
}
```

### С помощью наследования (Ktor + Koin)

Также есть возможность создавать цепочки с помощью наследования от `BotHandler`. Можно указывать всё в конструкторе:
```kotlin
@Factory
class StartBotHandler : BotHandler({
    command("/start") {
        // здесь действия при вызове команды /start
    }
})
```

### С помощью наследования (Spring)

Также есть возможность создавать цепочки с помощью наследования от `BotHandler`. Можно указывать всё в конструкторе:
```kotlin
@HandlerComponent
class StartBotHandler : BotHandler({
    command("/start") {
        // здесь действия при вызове команды /start
    }
})
```

Преимущество в том, что не нужно больше нигде никакие методы вызывать, не нужно никак обозначать этот класс. Достаточно просто повесить аннотацию @Factory.

### Статические цепочки

Следующие шаги цепочки обозначаются методом `step()`:
```kotlin
    step("get_name") {
    // здесь действия при вызове шага get_name
    }
```

Связать команду и шаг в цепочку можно с помощью аргумента `next`, и тогда получится цепочка:
```kotlin
fun BotHandling.startCommand() {
    command("/start", next = "get_name") {
        sendMessage("Введите своё имя.")
    }

    step("get_name") {
        // можно обработать, сохранить введённые данные
        sendMessage("Привет, $text!")
    }
}
```

Шагов может быть неограниченное количество, они идут прям друг за другом:
```kotlin
fun BotHandling.startCommand() {
    command("/start", next = "get_name") {
        sendMessage("Введите своё имя")
    }

    step("get_name", next = "get_age") {
        sendMessage("Привет, $text!")
        sendMessage("Введите свой возраст, пожалуйста.")
    }

    step("get_age") {
        val age = text.toIntOrNull() ?: throw ChatException("Ожидается целое число")
        
        sendMessage("$age лет - записано.")
    }
}
```

Последний шаг null, поэтому на ещё сообщение пользователя будет человекочитаемая ошибка. Подробнее об ошибках ниже.

> **Note**
> Наименования аргументов (`next`, `type`) указывать необязательно, но рекомендуется для повышения читаемости кода.

### Динамические цепочки

С помощью метода `next()` можно динамически выбирать следующий шаг.
В примере показано, как можно строить цепочки исходя из определённых условий:
```kotlin
fun BotHandling.isDenisCommand() {
    command("/is_denis", next = "is_denis") {
        sendMessage("Тебя зовут Денис?")
    }
    
    step("is_denis") {
        if (text.lowercase() == "да") {
            sendMessage("Красавчик!")
        } else {
            // если пользователя зовут не Денис, то добавляем ещё один шаг и спрашиваем его имя
            next("get_name")
            sendMessage("А какое у тебя имя?")
        }
    }
    
    step("get_name") {
        sendMessage("Имя $text тоже ничего.")
    }
}
```

Параметр `next` и метод `next()` можно комбинировать. Они взаимозаменимые.
Также можно вызвать метод `finalizeChain()`, чтобы завершить текущую цепочку (нужно только если задан параметр `next`).
```kotlin
fun BotHandling.isDenisCommand() {
    command("/is_denis") {
        next("is_denis")
        sendMessage("Тебя зовут Денис?")
    }
    
    step("is_denis", next = "get_name") {
        if (text.lowercase() == "да") {
            sendMessage("Красавчик!")
            finalizeChain()
        } else {
            sendMessage("А какое у тебя имя?")
        }
    }
    
    step("get_name") {
        sendMessage("Имя $text тоже ничего.")
    }
}
```

### Передача объектов между шагами

С помощью методов `next()` и `transfer()` можно передать любой объект в следующий шаг:
```kotlin
    step("get_name", next = "get_age") {
        sendMessage("Привет, $text!")
        transfer(text)
    }
```

А с помощью методов `transferred()` и `transferredOrNull()` можно получить этот объект в следующем шаге:
```kotlin
    step("get_age") {
        val age = text.toIntOrNull() ?: throw ChatException("Ожидается целое число")
        val name = transferred<String>()

        sendMessage("Твоё имя $name, и тебе $age лет - записано.")
    }
```

### Определение типа сообщения

По умолчанию метод `step()` ожидает текстовое сообщение.
С помощью параметра `type` можно задать ожидаемый тип сообщения от пользователя
```kotlin
    step("get contact", type = CONTACT) {
        sendMessage("Твой номер ${contact.phoneNumber}")
    }
```

Для одного шага можно задать несколько типов сообщения. Показательный пример с получением номера телефона.
Здесь и динамические цепочки, и передача объекта между шагами:
```kotlin
@Factory
class RegistrationHandler : BotHandler({
    val phonePattern = Regex("\\+?[78]?[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}([\\s\\-]?\\d{2}){2}")

    command("/register", next = "get contact") {
        sendMessage("Для регистрации введите номер или поделитесь своим контактом.", 
            replyMarkup = contactButton("Поделиться контактом"))
    }

    step("get contact", type = CONTACT) {
        sendMessage("${contact.firstName}, Вы успешно зарегистрировались по номеру ${contact.phoneNumber}!", 
            replyMarkup = removeKeyboard())
    }

    step("get contact", type = TEXT, next = "get firstname") {
        phonePattern.find(text) ?: throw ChatException("Неверный формат номера телефона.")

        sendMessage("Напишите, как к Вам обращаться.", replyMarkup = removeKeyboard())
        transfer(text)
    }

    step("get firstname") {
        val phone = transferred<String>()
        sendMessage("$text, Вы успешно зарегистрировались по номеру $phone!")
    }
})
```

Доступные типы на данный момент:
```kotlin
object MessageType {
    val TEXT = TextMessageArgument::class
    val PHOTO = PhotoMessageArgument::class
    val AUDIO = AudioMessageArgument::class
    val VOICE = VoiceMessageArgument::class
    val CONTACT = ContactMessageArgument::class
    val DOCUMENT = DocumentMessageArgument::class
}
```

## Обработка команд

У обработчиков команд есть все поля обычного текстового сообщения.
Но в дополнение к ним доступно получение аргументов.

Например, из строки `/some_command__abc_123 987 cxz` будут получены команда `/some_command`, параметр из команды `abc_123` и аргумент команды `987 cxz`.
В обработчике получить их можно из полей `commandPathParam` (`abc_123`) и `commandArgument` (`987 cxz`).

```kotlin
fun BotHandling.withArgsCommand() {
    command("/with_args") {
        // при вызове команды строкой "/with_args__arg1_0 arg2 0" будет выведено
        // "commandPathParam = arg1_0, commandArgument = arg2 0"
        sendMessage("commandPathParam = $commandPathParam, commandArgument = $commandArgument")
    }
}
```

Параметр из команды может понадобиться в случае, например, выбора товара из списка. 
Команда будет выглядеть примерно так: `/item__a67sdfnas5`. 
Когда пользователь нажмёт на команду, Вы сможете вывести конкретный item по условному id `a67sdfnas5`.

Аргумент команды может понадобиться, например, при создании обсуждения. 
Пользователь может ввести `/topic Kotlin vs Java`, и вы сможете создать обсуждение с названием `Kotlin vs Java`.

## Создание кнопок с callback'ом

Помимо методов для сообщений `command()` и `step()`,
есть ещё и метод для обработки callback'ов `callback()` (неожиданно, правда?).
Есть возможность также передавать объекты, продолжать цепочки:
```kotlin
fun BotHandling.callbackCommand() {
    command("/callback") {
        sendMessage("Выберите действие", replyMarkup = inlineKeyboard(
            callbackButton("Купить тапочки", next = "buy", content = "some id 1"),
            callbackButton("Купить шапочки", next = "buy", content = "some id 2"),
            callbackButton("Оставить отзыв", next = "get_feedback_intro")))
    }
    
    callback("buy") {
        val itemId = transferred<String>()

        sendMessage("Выбран товар с идентификатором $itemId")
    }
    
    callback("get_feedback_intro", next = "get_feedback") {
        sendMessage("Напишите, пожалуйста, что вы думаете о нашем приложении.")
    }
    
    step("get_feedback") {
        saveКудаНибудьСебе(text)

        sendMessage("Спасибо за отзыв!")
    }
}
```

> **Note**
> Если строка callback'а не больше допустимых 64 символов, то она передаётся в callback'е.
Иначе объект кладётся в БД, а в callback кладётся id на сохранённый объект.

## Шаблоны

### Получение шаблонов (Ktor + Koin, Spring)
С помощью метода с делегатами `TemplateFactory.property()` можно взять шаблон из конфига.

Без параметров метод получает наименование переменной, переводит его в kebab-case и берёт значение из конфига.

```kotlin
// Значение возьмётся из telegram-bot.from-field-name
val BotHandling.fromFieldName by property()
// Значение возьмётся из telegram-bot.from-param
val BotHandling.fromParam by property("from-param")
// Значение возьмётся из telegram-bot.from-param. 
// Если оно в конфиге не задано значение, то подставится "default template when null"
val BotHandling.fromParamOrDefault by property("from-param", "default template when null")
```

Шаблон можно получить везде, где только вздумается:
```kotlin
import io.github.dehuckakpyt.telegrambot.factory.template.TemplateFactory.property

val fileWithoutClass by property()

val BotHandling.extendedField by property()

class SomeClass {
    val inAnyClass by property()
}

fun BotHandling.startCommand() {
    val insideMethods by property()
    
    command("/start") {
        val insideMethodsInMethods by property()
        
        sendMessage(insideMethods)
        sendMessage(insideMethodsInMethods)
    }
}
```

### Подстановка значений в шаблоны 

Доступна подстановка с помощью короткого метода `with()` с перегрузками. По умолчанию в классе BotHandling. А также для Ktor + Koin, Spring и в других классах с помощью интерфейса `Templating`.
```kotlin
class PresentationTestClass : Templating {
    private val testTemplate by property()
    private val instance = PresentationTestModel("some name")
    private val simpleValue = 1

    val example1 = testTemplate with instance
    val example2 = testTemplate with mapOf("value" to simpleValue)
    val example3 = testTemplate with ("value" to simpleValue)

    val example4 = testTemplate.with(instance)
}
data class PresentationTestModel(
    val value: String
)
```


### Спец. методы в шаблоне

В шаблоне доступны методы `escapeHtml()` и `cleanHtml()` ([реализация](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/telegram-bot-core/src/main/kotlin/io/github/dehuckakpyt/telegrambot/formatter/HtmlFormatterImpl.kt)). 
Их можно использовать при отправке сообщения с `parseMode = Html`.


Метод `escapeHtml()` экранирует все html символы.
Метод `cleanHtml()` оставляет только форматируемые теги telegram'ом (см https://core.telegram.org/bots/api#html-style).
```
telegram-bot {
    template {
        escape-example = "formatted text: ${escapeHtml(param)}"
        clean-example = "formatted text: ${cleanHtml(param)}"
    }
}
```
```kotlin
fun BotHandling.templateCommand() {
    val htmlFormattedString = "<b><u>formatted</u></b> <center>ignored</center><br>new line"
    command("/template_html_formatted") {
        // текст сообщения: formatted text: <b><u>formatted</u></b> <center>ignored</center><br>new line
        sendMessage(cleanExample with ("param" to htmlFormattedString), parseMode = Html)
        // текст сообщения: formatted text: formatted ignored new line (с underline и переносом строки)
        sendMessage(escapeExample with ("param" to htmlFormattedString), parseMode = Html)
    }
}
```

## Использование вне BotHandling (Ktor + Koin)

Самым обычным образом можно получить бота и вызывать его методы:
```kotlin
@Single
class NotifyWhenStartedRunner : Runner, Templating {
    // всегда доступен TelegramBot для отправки сообщений
    // достаточно просто воспользоваться Koin
    private val bot = get<TelegramBot>()
    private val chatIdToNotify = 1165327523L

    override suspend fun execute() {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted with ("botUsername" to bot.username), parseMode = HTML)
    }
}
```

## Использование вне BotHandling (Spring)

Бот доступен, как бин:
```kotlin
@Component
class NotifyWhenStartedRunner(
    // всегда доступен TelegramBot для отправки сообщений
    private val bot: TelegramBot,
) : CommandLineRunner, Templating {
    private val chatIdToNotify = 1165327523L

    override fun run(vararg args: String): Unit = runBlocking {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted with ("botUsername" to bot.username), parseMode = HTML)
    }
}
```

## Обработка ошибок

### Ожидаемые (выводимые) исключения

Внутри обработчиков команд, шагов, callback'ов можно бросать исключения. 

`ChatException` напишет сообщение об ошибке обратно в чат, из которого пришёл запрос.

`PrivateChatException` напишет сообщение об ошибке обратно в чат, из которого пришёл запрос, только если это личный чат. 
При исключениях в групповых чатах бот не выведет эту ошибку.

```kotlin
fun BotHandling.exceptionCommand() {
    command("/exception") {
        throw ChatException("Обычная ошибка")
    }
    command("/private_exception") {
        throw PrivateChatException("Ошибка для личного чата")
    }
}
```

При любом другом исключении пользователю выведется сообщение "Произошла непредвиденная ошибка. Обратитесь к разработчику."

> **Note**
> Все текстовки настраиваются. Новые исключения и их обработчики добавляются.


## Конфигурация

### Все доступные настройки
```kotlin
class TelegramBotConfig {
    var enabled: String
    var token: String
    var username: String
    var pollingOptions: PollingOptions.() -> Unit
    var configureBot: TelegramBot.() -> Unit
    var handling: BotHandling.() -> Unit
    var templateConfig: Configuration
    var callbackContentSource: CallbackContentSource
    var chainSource: ChainSource
    var messageSource: MessageSource
    var callbackDataDelimiter: Char 
    var contentConverter: ContentConverter 
    var callbackSerializer: CallbackSerializer 
    var htmlFormatter: HtmlFormatter 
    var exceptionHandler: ExceptionHandler 
    var chainExceptionHandler: ChainExceptionHandler 
}
```

### Настройка хранения в БД (Exposed)

`callbackContentSource: CallbackContentSource` - используется для хранения callback'ов, у которых длина больше 64 символов.

`chainSource: ChainSource` - используется для хранения текущего состояния цепочек (следующий шаг, контент для следующего шага).

`messageSource: MessageSource` - используется для хранения истории всех сообщений.


По умолчанию всё хранится в памяти и станет недоступно после завершения работы приложения.
Может быть удобно для быстрого тестирования работы бота.
```kotlin
    install(TelegramBot) {
        messageSource = { MessageSource.empty }
        receiving {
            callbackContentSource = { CallbackContentSource.inMemory }
            chainSource = { ChainSource.inMemory }
        }
    }
```

Для сохранения состояния в БД необходимо подключиться к ней с помощью [Exposed](https://github.com/JetBrains/Exposed) ([пример](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/plugin/DatabaseConnection.kt)).
Затем добавить зависимость и указать source'ы:
```Gradle
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-database-source:0.6.0")
}
```
```kotlin
    install(TelegramBot) {
    messageSource = { MessageSource.inDatabase }
    receiving {
        callbackContentSource = {
            CallbackContentSource.inDatabase(
                maxCallbackContentsPerUser = 2
            )
        }
        chainSource = { ChainSource.inDatabase }
    }
}
```
Также можно комбинировать сохранения и можно делать свои реализации.

### Настройка сериализации callback'ов

По умолчанию в callback записывается строка `<next step>|s<json content string>`. 
Но если строка больше 64 символов, то в строке будет `<next step>|i<id CallbackContent>`.

`callbackDataDelimiter: Char` - символ, который разделяет step и callback. По умолчанию - `|`. 

`contentConverter: ContentConverter` - конвертер, сериализирующий объект в строку (по умолчанию в json).

`callbackSerializer: CallbackSerializer` - сериализатор, складывающий в строку step и объект.

Чтобы сериализовать в более компактную строку, можно написать свою реализацию для сериализации. 
Просто реализовать интерфейс `CallbackSerializer` и задать его в конфиге плагина:

```kotlin
class CustomCallbackSerializer : CallbackSerializer {

    override suspend fun toCallback(next: String, instance: Any?): String {
        // Кастомная сериализация следующего шага и объекта.
    }

    override suspend fun fromCallback(callbackData: String): CallbackDataInfo {
        // Кастомная десериализация следующего шага и объекта.
    }

    override fun validateCallbackName(name: String) {
        // Метод для проверки названия callback'ов. Вызывается при создании обработчика callback'а. 
        // На случай выбора недопустимых символов.
    }
}
```
```kotlin
    install(TelegramBot) {
        receiving {
            callbackSerializer = CustomCallbackSerializer()
        }
    }
```

### Настройка обработки исключений
`chainExceptionHandler: ChainExceptionHandler` - обработчик исключений в цепочках. 
Можно настроить поведение при отсутствии команды, отсутствии следующего шага и отсутствии ожидаемого типа сообщения.
```kotlin
// или наследовать от ChainExceptionHandlerImpl, чтобы переопределить только часть методов
class CustomChainExceptionHandler : ChainExceptionHandler {
    override fun whenCommandNotFound(command: String): Nothing {
        // когда не найдена команда
    }

    override fun whenStepNotFound(): Nothing {
        // когда не найден следующий шаг
    }

    override fun whenUnexpectedMessageType(expectedMessageTypes: Set<KClass<out MessageContainer>>): Nothing {
        // когда пользователь отправил неожидаемый тип сообщения
    }
}
```
```kotlin
    install(TelegramBot) {
        receiving {
            chainExceptionHandler = CustomChainExceptionHandler()
        }
    }
```

`exceptionHandler: ExceptionHandler` - общий обработчик исключений. 
Можно настроить поведение при ожидаемых и неожидаемых ошибках во время обработки команд, шагов и т д. 
В том числе добавить свои типы исключений (пример [добавления](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/exception/CustomExceptionHandler.kt) и [использования](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/handler/ExceptionCommand.kt))
```kotlin
class CustomExceptionHandler : ExceptionHandlerImpl() {
    override suspend fun caught(chatId: Long, ex: Throwable) {
        // Сюда придут все исключения, возникшие во время обработки запросов.
        // Можно отправлять что-то в чат, можно писать в логи.
    }
}
```
```kotlin
    install(TelegramBot) {
        receiving {
            exceptionHandler = CustomExceptionHandler()
        }
    }
```


## Что обязательно нужно сделать ещё:
- Реализацию со Spring
- Добавить тесты
- Добавить webhook


## Фишки, которые хочется сделать:
- Придумать что-то с обработкой сообщений с длиной более 4096 символов
- Придумать, как реализовать "мультиязычность"
- Добавить возможность указывать action ('typing..' и т. п.)
- Сделать отключаемыми методы cleanHtml и escapeHtml в шаблонах
- Разобрать, как сделать нормально разные реализации для запросов (ktor и spring своими инструментами чтобы делали запросы, а не всегда ktor-client) 
