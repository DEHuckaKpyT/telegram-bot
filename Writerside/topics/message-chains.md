# Message chains

The most valuable thing in the library is the convenient construction of a dialog with the user (let name it the "chain").

Most dialogs with bot begin with the command. You can also start a chain with a callback, reaction etc.

<tabs id="message-chain-start" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code>com/example/myproject/handler/StartHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class StartHandler : BotHandler({
                command("/start") {
                    // here are the actions when the user enters the /start command
                }
            })
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code>com/example/myproject/handler/StartHandler.kt</code>
        <code-block lang="kotlin">
            @Factory
            class StartHandler : BotHandler({
                command("/start") {
                    // here are the actions when the user enters the /start command
                }
            })
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code>com/example/myproject/handler/StartHandler.kt</code>
        <code-block lang="kotlin">
            fun BotHandling.startHandler() {
                command("/start") {
                    // here are the actions when the user enters the /start command
                }
            }
        </code-block>
    </tab>
</tabs>
<note>Everything that works in the core version also works in the frameworks.
Such as the extension method from BotHandling. Most of the examples will be shown on the core version only.</note>