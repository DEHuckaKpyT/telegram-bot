# Templates

### Substituting values into templates

Substitution is available using the short `with()` method with overloads. 
By default in the BotHandling and BotUpdateHandling classes. 
And also for Ktor + Koin and in other classes using `Templating` interface.


<tabs id="factory-tenplate" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code-block lang="kotlin">
            @Component
            class PresentationTestClass(
                private val templater: Templater,
            ) {
                val testTemplate = "testing value is '\${value}'"
                val instance = PresentationTestModel("some name")
                val simpleValue = 1
                with(templater) {
                    val example1 = testTemplate with instance
                    println(example1) // prints "testing value is 'some name'"
                    val example2 = testTemplate with mapOf("value" to simpleValue)
                    println(example2) // prints "testing value is '1'"
                    val example3 = testTemplate with ("value" to simpleValue)
                    println(example3) // prints "testing value is '1'"
                }
            }
            data class PresentationTestModel(
                val value: String
            )
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code-block lang="kotlin">
            @Single
            class PresentationTestClass : Templating {
                val testTemplate = "testing value is '\${value}'"
                val instance = PresentationTestModel("some name")
                val simpleValue = 1
                val example1 = testTemplate with instance
                println(example1) // prints "testing value is 'some name'"
                val example2 = testTemplate with mapOf("value" to simpleValue)
                println(example2) // prints "testing value is '1'"
                val example3 = testTemplate with ("value" to simpleValue)
                println(example3) // prints "testing value is '1'"
            }
            data class PresentationTestModel(
                val value: String
            )
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code-block lang="kotlin">
            class PresentationTestClass(
                private val templater: Templater,
            ) : Templater by templater {
                val testTemplate = "testing value is '\${value}'"
                val instance = PresentationTestModel("some name")
                val simpleValue = 1
                val example1 = testTemplate with instance
                println(example1) // prints "testing value is 'some name'"
                val example2 = testTemplate with mapOf("value" to simpleValue)
                println(example2) // prints "testing value is '1'"
                val example3 = testTemplate with ("value" to simpleValue)
                println(example3) // prints "testing value is '1'"
            }
            data class PresentationTestModel(
                val value: String
            )
        </code-block>
    </tab>
</tabs>

### Integration with FreeMarker

#### Configuration

```kotlin
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-templater-freemarker:%current_version%")
}
```

```kotlin
val config = TelegramBotConfig().apply {
    templater = { Templater.dynamicFreeMarker }
}
```

#### Usage

The `escapeHtml()` and `cleanHtml()` methods are available in the template ([implementation](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/telegram-bot-core/src/main/kotlin/io/github/dehuckakpyt/telegrambot/formatter/HtmlFormatterImpl.kt)).
These can be used when sending a message with `parseMode = HTML`.

The `escapeHtml()` method escapes all html characters.
The `cleanHtml()` method leaves only telegram formatted tags (see [official docs](https://core.telegram.org/bots/api#html-style)).
```kotlin
fun BotHandling.templateCommand() {
    val cleanExample = "formatted text: \${cleanHtml(param)}"
    val escapeExample = "formatted text: \${escapeHtml(param)}"
    val htmlFormattedString = "<b><u>formatted</u></b> <center>ignored</center><br>new line"

    command("/template_html_formatted") {
        // message text: formatted text: <b><u>formatted</u></b> <center>ignored</center><br>new line
        sendMessage(cleanExample with ("param" to htmlFormattedString), parseMode = Html)
        // message text: formatted text: formatted ignored new line (with underline и line break)
        sendMessage(escapeExample with ("param" to htmlFormattedString), parseMode = Html)
    }
}
```

### Receiving templates

<tabs id="template-factory-receiving-templates" group="telegram-bot-code">
    <tab title="Ktor + Koin" group-key="ktor"></tab>
</tabs>

Using a method with delegates `TemplateFactory.property()` you can take a template from the config.
Without parameters, the method gets the variable name, translates it into kebab-case and takes the value from the config.

```kotlin
// the value will be received from telegram-bot.from-field-name
val BotHandling.fromFieldName by TemplateFactory.property()
// the value will be received from telegram-bot.from-custom-param
val BotHandling.fromParam by TemplateFactory.property("from-custom-param")
// the value will be received from telegram-bot.from-param. 
// if no value is specified in the config, "default template when null" will be substituted
val BotHandling.fromParamOrDefault by TemplateFactory.property("from-param", "default template when null")
```

You can get the template anywhere you want it:
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