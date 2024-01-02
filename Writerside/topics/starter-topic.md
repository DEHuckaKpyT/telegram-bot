# Kotlin Telegram Bot

Kotlin library for creating Telegram Bots. You can use implementation for [Spring](https://spring.io/), [Ktor](https://ktor.io/)+[Koin](https://insert-koin.io/) or create with you own implementation.
Now it have possibility to save state in database with [Exposed](https://github.com/JetBrains/Exposed) only.

Easy to handle dialogs with users.
Supporting message templates and other helpful features.
Working on coroutines.

Example of applications in [example-spring](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-spring), [example-ktor](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-ktor), [example-core](https://github.com/DEHuckaKpyT/telegram-bot/tree/master/example-core) directories. 

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
        <code>com/example/myproject/handler/Template.kt</code>
        <code-block lang="kotlin">
            // from application.yml telegram-bot.template.chain = "Let's play a game. Write any positive integer."
            val GameChainHandler.chain by property()
            // from application.yml telegram-bot.template.chain-start-sum = "I will now add the numbers entered until the sum of them is greater than or equal to ${target}."
            val GameChainHandler.chainStartSum by property()
            // from application.yml telegram-bot.template.chain-one-more = "The current amount is ${sum}. Target: ${target}."
            val GameChainHandler.chainOneMore by property() 
            // from application.yml telegram-bot.template.chain-end = "Let's play a game. Write any positive integer."
            val GameChainHandler.chainEnd by property() 
            // from application.yml telegram-bot.template.chain-integer-is-expected = "Integer is expected."
            val GameChainHandler.chainIntegerIsExpected by property() 
            // from application.yml telegram-bot.template.chain-positive-is-expected = "Positive integer is expected."
            val GameChainHandler.chainPositiveIsExpected by property() 
        </code-block>
        <code>com/example/myproject/handler/GameChainHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class GameChainHandler : BotHandler({
                val startSum = 0
                command("/chain", next = "get target") {
                    sendMessage(chain)
                }
                step("get target", next = "sum numbers") {
                    val target = text.toIntOrNull() ?: throw ChatException(chainIntegerIsExpected)
                    if (target &lt; 1) throw ChatException(chainPositiveIsExpected)
                    sendMessage(chainStartSum with ("target" to target))
                    transfer(target to startSum)
                }
                step("sum numbers") {
                    val value = text.toIntOrNull() ?: throw ChatException(chainIntegerIsExpected)
                    var (target, sum) = transferred&lt;Pair&lt;Int, Int&gt;&gt;()
                    sum += value
                    if (sum &lt; target) {
                        sendMessage(chainOneMore with mapOf("sum" to sum, "target" to target))
                        next("sum numbers", target to sum)
                        return@step
                    }
                    sendMessage(chainEnd with mapOf("sum" to sum, "target" to target))
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
                    sendMessage("Выберите действие", replyMarkup = inlineKeyboard(
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