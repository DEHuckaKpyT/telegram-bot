# Templates

### Receiving templates

> Receiving templates available with Spring and Ktor+Koin only

Using a method with delegates `TemplateFactory.property()` you can take a template from the config.
Without parameters, the method gets the variable name, translates it into kebab-case and takes the value from the config.

```kotlin
// the value will be received from telegram-bot.from-field-name
val BotHandling.fromFieldName by property()
// the value will be received from telegram-bot.from-custom-param
val BotHandling.fromParam by property("from-custom-param")
// the value will be received from telegram-bot.from-param. 
// if no value is specified in the config, "default template when null" will be substituted
val BotHandling.fromParamOrDefault by property("from-param", "default template when null")
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

### Substituting values into templates

Substitution is available using the short `with()` method with overloads. 
By default in the BotHandling class. 
And also for Ktor + Koin, Spring and in other classes using `Templating` interface.
```kotlin
class PresentationTestClass : Templating {
    private val testTemplate by property()
    private val instance = PresentationTestModel("some name")
    private val simpleValue = 1

    val example1 = testTemplate with instance
    val example2 = testTemplate with mapOf("value" to simpleValue)
    val example3 = testTemplate with ("value" to simpleValue)
}
data class PresentationTestModel(
    val value: String
)
```

### Special methods in templates

The `escapeHtml()` and `cleanHtml()` methods are available in the template ([implementation](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/telegram-bot-core/src/main/kotlin/io/github/dehuckakpyt/telegrambot/formatter/HtmlFormatterImpl.kt)).
These can be used when sending a message with `parseMode = HTML`.

The `escapeHtml()` method escapes all html characters.
The `cleanHtml()` method leaves only telegram formatted tags (see [official docs](https://core.telegram.org/bots/api#html-style)).
```yaml
telegram-bot:
  template:
    escape-example: "formatted text: ${escapeHtml(param)}"
    clean-example: "formatted text: ${cleanHtml(param)}"
```
```kotlin
fun BotHandling.templateCommand() {
    val htmlFormattedString = "<b><u>formatted</u></b> <center>ignored</center><br>new line"
    command("/template_html_formatted") {
        // message text: formatted text: <b><u>formatted</u></b> <center>ignored</center><br>new line
        sendMessage(cleanExample with ("param" to htmlFormattedString), parseMode = Html)
        // message text: formatted text: formatted ignored new line (with underline и line break)
        sendMessage(escapeExample with ("param" to htmlFormattedString), parseMode = Html)
    }
}
```