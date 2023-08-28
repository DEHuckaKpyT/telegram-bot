# telegram-bot

Библиотека для написания telegram-ботов. За основу взят
репозиторий [kt-telegram-bot](https://github.com/elbekD/kt-telegram-bot).

## Get started

Бот работает с [Ktor](https://ktor.io/) + [Koin](https://insert-koin.io/).
Пример готового приложения в репозитории example.

Для запуска и конфигурирования бота необходимо
задать в конфигурации telegram-bot.username и telegram-bot.token и установить бота, как ktor-плагин:
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

## Конфигурация

### Настройка FreeMarker

```kotlin
        configureTemplating {
            defaultEncoding = "UTF-8"
            dateFormat = "yyyy-MM-dd"
            // и остальное из документации FreeMarker
        }
```

### Настройка хранения состояния

По умолчанию всё хранится до завершения работы приложения - в памяти. Подключение к БД не нужно.
Это может быть удобно, если никакую информацию и не нужно хранить. 
Также это удобно в случае, если нужно быстро проверить работу бота.
```kotlin
    install(TelegramBot) {
        callbackContentSource = CallbackContentSource.inMemory
        chainSource = ChainSource.inMemory
        messageSource = MessageSource.empty
    }
```

Имеется возможность сохранять состояние в БД. 
Достаточно всего лишь добавить зависимость и написать следующий код:
```kotlin
    install(TelegramBot) {
        callbackContentSource = CallbackContentSource.inDatabase
        chainSource = ChainSource.inDatabase
        messageSource = MessageSource.inDatabase
    }
```

Или ещё короче:
```kotlin
    install(TelegramBot) {
        databaseSources()
    }
```
Также можно комбинировать сохранение информации, как только угодно.

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
}
```

## Цепочки сообщений

Самое ценное в библиотеке - удобное построение диалога с пользователем.
Для бота диалог является цепочкой сообщений.
И теперь её можно легко сделать.

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
        val age = text.toIntOrNull() ?: throw CustomException("Ожидается целое число")
        
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
        val age = text.toIntOrNull() ?: throw CustomException("Ожидается целое число")
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
        phonePattern.find(text) ?: throw CustomException("Неверный формат номера телефона.")

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

## Обработка кнопок с callback'ом

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
val BotHandling.fromFieldName by template()
val BotHandling.fromParam by template("from-param")
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
class PresentationTestClassExtended : TemplatingExtended {
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

## Использование вне BotHandling

Самым обычным образом можно получить бота и вызывать его методы:
```kotlin
class NotifyWhenStartedRunner : TemplatingExtended {
    private val bot = get<TelegramBot>()
    private val chatIdToNotify = 1165327523L

    suspend fun execute() {
        bot.sendMessage(chatIdToNotify, runnerNotifyWhenStarted("botUsername" to bot.username), parseMode = Html)
    }
}
```
