# Kotlin Telegram Bot

Kotlin library for creating Telegram Bots. You can use clean version, with implementation for [Spring](https://spring.io/), [Ktor](https://ktor.io/)+[Koin](https://insert-koin.io/) or create with you own implementation.
It have also possibility to save state in database with [Spring JPA](https://spring.io/projects/spring-data-jpa/) or [Exposed](https://github.com/JetBrains/Exposed).

Easy to handle dialogs with users.
Supporting message templates and other helpful features.
Working on coroutines.

Example of applications in [example-spring](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-spring), [example-ktor](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-ktor), [example-core](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-core) directories. 

## Why this library
- Focused on building a **dialog with the user** (for example, no need to specify `chatId` in dialog chains).
- Has **many useful utilities** (such as templating, keyboard and button creating and other).
- Telegram API methods realization have **overloads** for more comfortable usage (like a `chatId` as `String` or `Long`).
- Working on **coroutines**.
- Has **clean** version or with **Spring** or **Ktor+Koin** frameworks.
- Has possibility to **save state in database** with **Spring JPA** or **Exposed**.
- Easy to **write tests** for your bot.

## Prerequisites

- JDK 17 or higher
- Kotlin 1.8 or higher
- Gradle or Maven

## Simple examples

<tabs id="simple-example" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code>com/example/myproject/handler/RegistrationHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class RegistrationHandler : BotHandler({
                command("/register", next = "get_contact") {
                    // send message with contact button
                    sendMessage("Please, share your phone number", replyMarkup = contactKeyboard("Share contact"))
                }
                // if user share contact, then store number
                step("get_contact", type = CONTACT) {
                    sendMessage("Hello, ${contact.firstName}! Stored number: ${contact.phoneNumber}.", replyMarkup = removeKeyboard())
                }
                // if user typed phone number, then we need ask one more question
                step("get_contact", type = TEXT, next = "get_firstname") {
                    sendMessage("What is your name?", replyMarkup = removeKeyboard())
                    transfer(text) // transfer the phone number to next step
                }
                // step will executed only if user typed phone number as string
                step("get_firstname") {
                    // we can receive phone number from previous step (because transferred it)
                    val phone = transferred&lt;String&gt;()
                    sendMessage("Hello, $text! Stored number: $phone.")
                }
            })
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code>com/example/myproject/handler/RegistrationHandler.kt</code>
        <code-block lang="kotlin">
            @Factory
            class RegistrationHandler : BotHandler({
                command("/register", next = "get_contact") {
                    // send message with contact button
                    sendMessage("Please, share your phone number", replyMarkup = contactKeyboard("Share contact"))
                }
                // if user share contact, then store number
                step("get_contact", type = CONTACT) {
                    sendMessage("Hello, ${contact.firstName}! Stored number: ${contact.phoneNumber}.", replyMarkup = removeKeyboard())
                }
                // if user typed phone number, then we need ask one more question
                step("get_contact", type = TEXT, next = "get_firstname") {
                    sendMessage("What is your name?", replyMarkup = removeKeyboard())
                    transfer(text) // transfer the phone number to next step
                }
                // step will executed only if user typed phone number as string
                step("get_firstname") {
                    // we can receive phone number from previous step (because transferred it)
                    val phone = transferred&lt;String&gt;()
                    sendMessage("Hello, $text! Stored number: $phone.")
                }
            })
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code>com/example/myproject/handler/RegistrationHandler.kt</code>
        <code-block lang="kotlin">
            fun BotHandling.registrationHandler() {
                command("/register", next = "get_contact") {
                    // send message with contact button
                    sendMessage("Please, share your phone number", replyMarkup = contactKeyboard("Share contact"))
                }
                // if user share contact, then store number
                step("get_contact", type = CONTACT) {
                    sendMessage("Hello, ${contact.firstName}! Stored number: ${contact.phoneNumber}.", replyMarkup = removeKeyboard())
                }
                // if user typed phone number, then we need ask one more question
                step("get_contact", type = TEXT, next = "get_firstname") {
                    sendMessage("What is your name?", replyMarkup = removeKeyboard())
                    transfer(text) // transfer the phone number to next step
                }
                // step will executed only if user typed phone number as string
                step("get_firstname") {
                    // we can receive phone number from previous step (because transferred it)
                    val phone = transferred&lt;String&gt;()
                    sendMessage("Hello, $text! Stored number: $phone.")
                }
            }
        </code-block>
    </tab>
</tabs>

<note>Everything that works in the core version also works in the frameworks.
Such as the extension method from BotHandling. 
Most of the examples will be shown on the core version only.</note>

## More complex examples

<tabs id="complex-example" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code>com/example/myproject/holder/MessageTemplateHolder.kt</code>
        <code-block lang="kotlin">
        @ConfigurationProperties("telegram-bot.template")
        class MessageTemplateHolder {
            // from application.yml telegram-bot.template.chain = "Let's play a game. Write any positive integer."
            lateinit var chain: String
            // from application.yml telegram-bot.template.chain-start-sum = "I will now add the numbers entered until the sum of them is greater than or equal to ${target}."
            lateinit var chainStartSum: String
            // from application.yml telegram-bot.template.chain-one-more = "The current amount is ${sum}. Target: ${target}."
            lateinit var chainOneMore: String
            // from application.yml telegram-bot.template.chain-end = "Let's play a game. Write any positive integer."
            lateinit var chainEnd: String
            // from application.yml telegram-bot.template.chain-integer-is-expected = "Integer is expected."
            lateinit var chainIntegerIsExpected: String
            // from application.yml telegram-bot.template.chain-positive-is-expected = "Positive integer is expected."
            lateinit var chainPositiveIsExpected: String
        }
        </code-block>
        <code>com/example/myproject/handler/GameChainHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class GameChainHandler(
                private val template: MessageTemplateHolder,
            ) : BotHandler({
                val startSum = 0
                command("/chain", next = "get_target") {
                    sendMessage(template.chain)
                }
                step("get_target", next = "sum_numbers") {
                    val target = text.toIntOrNull() ?: throw ChatException(template.chainIntegerIsExpected)
                    if (target &lt; 1) throw ChatException(template.chainPositiveIsExpected)
                    sendMessage(template.chainStartSum with ("target" to target))
                    transfer(target to startSum)
                }
                step("sum_numbers") {
                    val value = text.toIntOrNull() ?: throw ChatException(template.chainIntegerIsExpected)
                    var (target, sum) = transferred&lt;Pair&lt;Int, Int&gt;&gt;()
                    sum += value
                    if (sum &lt; target) {
                        sendMessage(template.chainOneMore with mapOf("sum" to sum, "target" to target))
                        next("sum_numbers", target to sum)
                        return@step
                    }
                    sendMessage(template.chainEnd with mapOf("sum" to sum, "target" to target))
                }
            })
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code>com/example/myproject/handler/PurchaseHandler.kt</code>
        <code-block lang="kotlin">
            @Factory
            class PurchaseHandler(
                private val itemService: ItemService,
                private val feedbackService: FeedbackService,
            ) : BotHandler({
                command("/buy") {
                    sendMessage("Make your choice", replyMarkup = inlineKeyboard(
                        callbackButton("Buy slippers", next = "buy", content = "some id 1"),
                        callbackButton("Buy hats", next = "buy", content = "some id 2"),
                        callbackButton("Give feedback", next = "get_feedback_intro")))
                }
                callback("buy") {
                    val itemId = transferred&lt;String&gt;()
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
                    sendMessage("Please write us what you think about our company")
                }
                step("get_feedback") {
                    feedbackService.save(chatId, text)
                    sendMessage("Thanks for the feedback!")
                    sendSticker(...)
                }
            })
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code>com/example/myproject/handler/RegistrationHandler.kt</code>
        <code-block lang="kotlin">
            fun BotHandling.registrationHandler() {
                val phonePattern = &lt;phone regex&gt;
                command("/register", next = "get_contact") {
                    sendMessage("Enter a number or share your contact to register", 
                        replyMarkup = contactKeyboard("Share contact"))
                }
                step("get_contact", type = CONTACT) {
                    sendMessage("${contact.firstName}, You have successfully registered with number ${contact.phoneNumber}!", 
                        replyMarkup = removeKeyboard())
                }
                step("get_contact", type = TEXT, next = "get_firstname") {
                    phonePattern.find(text) ?: throw ChatException("Incorrect phone number format")
                    val phone: String = text
                    sendMessage("What is your name?", replyMarkup = removeKeyboard())
                    transfer(phone)
                }
                step("get_firstname") {
                    val phone = transferred&lt;String&gt;()
                    sendMessage("${text}, You have successfully registered with number ${phone}!")
                }
            }
        </code-block>
    </tab>
</tabs>