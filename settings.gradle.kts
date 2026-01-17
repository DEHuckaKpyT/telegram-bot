rootProject.name = "telegram-bot"

include("telegram-bot-core")
include("telegram-bot-spring")
include("telegram-bot-ktor")
include("telegram-bot-test")
include("telegram-bot-source-jpa")
include("telegram-bot-source-exposed")
include("telegram-bot-templater-freemarker")
//include("telegram-bot-admin-api-spring")
//include("telegram-bot-admin-ui-spring")

include("example-core")
include("example-spring")
include("example-ktor")
include("kscripts")
include("kscripts:host")
include("kscripts:script-definition")


plugins {
    // See https://splitties.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.5"
}

refreshVersions {
    // Ignoring file versions.properties
    file("build/tmp/refreshVersions").mkdirs()
    versionsPropertiesFile = file("build/tmp/refreshVersions/versions.properties")
}
