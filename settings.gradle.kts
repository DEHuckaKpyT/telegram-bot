rootProject.name = "telegram-bot"

include("telegram-bot-core")
include("telegram-bot-spring")
include("telegram-bot-ktor")
include("telegram-bot-test")
include("telegram-bot-source-jpa")
include("telegram-bot-source-spring2-jpa")
include("telegram-bot-source-exposed")
include("telegram-bot-templater-freemarker")

include("example-core")
include("example-spring")
include("example-ktor")
include("kscripts")
include("kscripts:host")
include("kscripts:script-definition")
