# telegram-bot

Библиотека для написания telegram-ботов. За основу взят
репозиторий [kt-telegram-bot](https://github.com/elbekD/kt-telegram-bot).

## Get started

Бот работает с [Ktor](https://ktor.io/) + [Koin](https://insert-koin.io/).
Пример готового приложения в [example](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example).

Для запуска и конфигурирования бота необходимо
добавить зависимость, задать в конфигурации `telegram-bot.username` и `telegram-bot.token` и установить бота, как ktor-плагин:
```Gradle
repositories {
    // не нужен будет, когда в основе не будет библиотеки kt-telegram-bot
    maven("https://jitpack.io") 
    // не нужен будет, когда получится залить в maven central (очень надеюсь, что получится)
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.2.4-SNAPSHOT")
}
```
```
    
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

fun BotHandling.startCommand() {
    command("/start") {
        sendMessage("Привет, меня зовут $username :-)")
    }
}
```

## Цепочки сообщений

Самое ценное в библиотеке - удобное построение диалога с пользователем (далее - цепочка). 

Создание цепочек осуществляется с помощью метода расширения у класса `BotHandling`:
```kotlin
fun BotHandling.startCommand() {
    // здесь будут обработчики команд и других событий
}
```

Сообщение, с которого начинается цепочка - это команда. Она обозначается с помощью метода `command()`.
```kotlin
    command("/start") {
    // здесь действия при вызове команды старт
    }
```

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
fun BotHandling.registerCommand() {
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
}
```

Доступные типы на данный момент:
```kotlin
val TEXT = TextMassageContainer::class
val PHOTO = PhotoMassageContainer::class
val AUDIO = AudioMassageContainer::class
val VOICE = VoiceMessageContainer::class
val CONTACT = ContactMessageContainer::class
val DOCUMENT = DocumentMessageContainer::class
```

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

### Получение шаблонов
С помощью метода с делегатами `template()` можно взять шаблон из конфига.

Без параметров метод получает наименование переменной, переводит его в kebab-case и берёт значение из конфига.

```kotlin
// Значение возьмётся из telegram-bot.from-field-name
val BotHandling.fromFieldName by template()
// Значение возьмётся из telegram-bot.from-param
val BotHandling.fromParam by template("from-param")
// Значение возьмётся из telegram-bot.from-param. 
// Если оно в конфиге не задано значение, то подставится "default template when null"
val BotHandling.fromParamOrDefault by template("from-param", "default template when null")
```

Шаблон можно получить везде, где только вздумается:
```kotlin
val fileWithoutClass by template()

val BotHandling.extendedField by template()

class SomeClass {
    val inAnyClass by template()
}

fun BotHandling.startCommand() {
    val insideMethods by template()
    
    command("/start") {
        val insideMethodsInMethods by template()
        
        sendMessage(insideMethods)
        sendMessage(insideMethodsInMethods)
    }
}
```

### Подстановка значений в шаблоны

С помощью наследования от интерфейса `Templating` доступна подстановка с помощью короткого метода `with()` с перегрузками:
```kotlin
class PresentationTestClass : Templating {
    private val testTemplate by template()
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

Но можно пойти ещё дальше и не использовать даже метод `with()`, а просто скобочки:
```kotlin
class PresentationTestClassExtended : TemplatingEx {
    private val testTemplate by template()
    private val instance = PresentationTestModel("some name")
    private val simpleValue = 1

    val example1 = testTemplate(instance)
    val example2 = testTemplate(mapOf("value" to simpleValue))
    val example3 = testTemplate("value" to simpleValue)
}
```

> **Note**
> Внутри BotHandling доступно использование только интерфейса `Templating`.


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

## Использование вне BotHandling

Самым обычным образом можно получить бота и вызывать его методы:
```kotlin
class NotifyWhenStartedRunner : TemplatingEx {
    private val bot = get<TelegramBot>()
    private val chatIdToNotify = 1165327523L

    suspend fun execute() {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted("botUsername" to bot.username), parseMode = Html)
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

### Настройка FreeMarker

```kotlin
        configureTemplating {
            defaultEncoding = "UTF-8"
            dateFormat = "yyyy-MM-dd"
            // и остальное из документации FreeMarker
        }
```

### Настройка хранения в БД

`callbackContentSource: CallbackContentSource` - используется для хранения callback'ов, у которых длина больше 64 символов.

`chainSource: ChainSource` - используется для хранения текущего состояния цепочек (следующий шаг, контент для следующего шага).

`messageSource: MessageSource` - используется для хранения истории всех сообщений.


По умолчанию всё хранится в памяти и станет недоступно после завершения работы приложения.
Может быть удобно для быстрого тестирования работы бота.
```kotlin
    install(TelegramBot) {
        callbackContentSource = CallbackContentSource.inMemory
        chainSource = ChainSource.inMemory
        messageSource = MessageSource.empty
    }
```

Для сохранения состояния в БД необходимо подключиться к ней с помощью [Exposed](https://github.com/JetBrains/Exposed) ([пример](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/plugin/DatabaseConnection.kt)).
Затем добавить зависимость и указать source'ы:
```Gradle
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-database-source:0.2.4-SNAPSHOT")
}
```
```kotlin
    install(TelegramBot) {
        callbackContentSource = CallbackContentSource.inDatabase
        chainSource = ChainSource.inDatabase
        messageSource = MessageSource.inDatabase

        // или с помощью одного метода
        databaseSources()
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
       // Метод для проверки названия callback'ов. Вызывается при создании обработчика callback'а. На случай выбора недопустимых символов.
    }
}
```
```kotlin
    install(TelegramBot) {
        callbackSerializer = CustomCallbackSerializer()
    }
```

### Настройка обработки исключений
`chainExceptionHandler: ChainExceptionHandler` - обработчик исключений в цепочках. 
Можно настроить поведение при отсутствии команды, отсутствии следующего шага и отсутствии ожидаемого типа сообщения.

`exceptionHandler: ExceptionHandler` - общий обработчик исключений. 
В нём Можно настроить поведение при ожидаемых и неожидаемых ошибках во время обработки команд, шагов и т д. 
В том числе добавить свои типы исключений (пример [добавления](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/exception/CustomExceptionHandler.kt) и [использования](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example/src/main/kotlin/io/github/dehuckakpyt/telegrambotexample/handler/ExceptionCommand.kt))


## Что обязательно нужно сделать ещё:
- Немного отрефакторить [свою либу в основе](https://github.com/DEHuckaKpyT/microservice-extensions) (получение конфига в json вместо properties, переименовать методы для транзакций, переделать в плагин подключение к бд, добавить аналог феинов)
- Решить вопрос с лицензией (можно ли просто скопировать себе код из [либы бота в основе](https://github.com/elbekD/kt-telegram-bot)). Если нет, то решить вопрос как-то ещё
- Добавить тесты


## Фишки, которые хочется сделать:
- Придумать что-то с обработкой сообщений с длиной более 4096 символов
- Добавить возможность указывать action ('typing..' и т. п.)
- Сделать отключаемыми методы cleanHtml и escapeHtml в шаблонах
- Удаление callback'ов из БД по cron'у или как-то ещё
- Возможность добавлять обработчики не только с помощью методов расширения `BotHandling`, но и с помощью создания классов (идея есть)
