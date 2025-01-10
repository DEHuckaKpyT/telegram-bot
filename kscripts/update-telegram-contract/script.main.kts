@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
@file:DependsOn("io.ktor:ktor-client-core:2.3.10")
@file:DependsOn("io.ktor:ktor-client-apache5-jvm:2.3.10")
@file:DependsOn("io.ktor:ktor-client-content-negotiation-jvm:2.3.10")
@file:DependsOn("io.ktor:ktor-serialization-jackson-jvm:2.3.10")
@file:DependsOn("com.fasterxml.jackson.core:jackson-databind:2.16.0")
@file:DependsOn("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")
@file:DependsOn("com.squareup:kotlinpoet-jvm:1.16.0")

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.kotlin.com.google.common.base.CaseFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.io.path.Path

val getDefaultValueFromDescriptionRegex = Regex("^(?:Always (.+)\\..+)|(?:.+, always “([a-z0-9_]+)”)$")
val getMustBeValueFromDescriptionRegex = Regex("^.+, must be \\*?([a-z0-9_]+)\\*?$")
val coreModulePath = Path("./../../telegram-bot-core/src/main/kotlin")
val testModulePath = Path("./../../telegram-bot-test/src/main/kotlin")
val objectsPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram"
val objectsInternalPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram.internal"
val telegramBotClassPackageName = "io.github.dehuckakpyt.telegrambot"
val inputClassPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram.input"
val apiPackageName = "io.github.dehuckakpyt.telegrambot.api"
val todayFormattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))!!
val client = HttpClient(Apache5) {
    install(ContentNegotiation) {
        register(Json, JacksonConverter(jacksonObjectMapper()))
    }
}

runBlocking {
    val contract = client.get("https://ark0f.github.io/tg-bot-api/custom_v2.json").bodyAsText()
//    val contract = Path("./custom_v2.json").readText()
        .replace("\"reference\": \"Telegram\"", "\"reference\": \"StarTransactions\"")
        .replace("\\\\_", "_")
        .replace("\\'", "'")
        .replace("\\\\-", "-")
        .replace("»", "")
        .replace(Regex("\n$"), "")
        .let { jacksonObjectMapper().readValue<Contract>(it) }
    contract.replaceObjectTypes()
    contract.replaceMethodTypes()
    contract.methodsWithOverloadsByName = contract.splitMethodsToOverloads()

    println("Generating telegram bot api contracts...")
    println("Version: ${contract.version.major}.${contract.version.minor}.${contract.version.patch}")
    println("Recent changes: ${contract.recentChanges.year}-${contract.recentChanges.month}-${contract.recentChanges.day}")
    createObjects(contract.objects)
    createMethods(contract.methodsWithOverloadsByName, contract.objects)
    createExtensionApiMethods(contract.methodsWithOverloadsByName)
    createApiMethods(contract.methodsWithOverloadsByName)
    createTelegramBotApiHandling(contract.methodsWithOverloadsByName)
    createTelegramBotApiExtHandling(contract.methodsWithOverloadsByName)
    createMockTelegramBot(contract.methodsWithOverloadsByName)
}

suspend fun createMockTelegramBot(methods: List<List<Method>>) {
    fun TypeSpec.Builder.addFunctions(): TypeSpec.Builder {
        methods.forEach { allMethods ->
            val mainMethod = allMethods.first()
            addFunction(FunSpec.builder(mainMethod.name)
                .addModifiers(SUSPEND, OVERRIDE)
                .apply { mainMethod.arguments.forEach { addParameter(it.toParameter(specifyNull = false)) } }
                .addStatement("return mockk()")
                .returns(mainMethod.returnType.toClassTypeName())
                .build())
        }

        return this
    }

    val file = FileSpec.builder("io.github.dehuckakpyt.telegrambot.test.mock", "MockTelegramBot")
        .indent("    ")
        .addImport("io.mockk", "mockk")
        .addType(
            TypeSpec.classBuilder("MockTelegramBot")
                .addKdoc("@author KScript")
                .addModifiers(INTERNAL)
                .addSuperinterface(ClassName(telegramBotClassPackageName, "TelegramBot"))
                .addProperty(PropertySpec.builder("username", String::class)
                    .initializer("\"mock_bot\"")
                    .addModifiers(OVERRIDE)
                    .build())
                .addProperty(PropertySpec.builder("client", ClassName("io.github.dehuckakpyt.telegrambot.api.client", "TelegramApiClient"))
                    .initializer("mockk()")
                    .addModifiers(OVERRIDE)
                    .build())
                .addFunctions()
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(testModulePath)
    }
}

suspend fun createTelegramBotApiHandling(methods: List<List<Method>>) {
    fun convertArgumentStatement(argName: String, from: Type, to: Type): String {
        if (from is LongType && to is StringType) return "$argName.toString()"
        if (from is StringType && to is ReferenceType && to.reference == "Input") return "StringInput($argName)"
        throw RuntimeException("Unexpected types to convert")
    }

    fun TypeSpec.Builder.addFunctions(): TypeSpec.Builder {
        methods.forEach { allMethods ->
            val mainMethod = allMethods.first()
            var statement = "return bot.${mainMethod.name}("
            for (argument in mainMethod.arguments) {
                statement += if (argument.nameCamelCase == "chatId")
                    "\n    ${argument.nameCamelCase} = chat.id,"
                else
                    "\n    ${argument.nameCamelCase} = ${argument.nameCamelCase},"
            }
            statement += "\n)"

            addFunction(FunSpec.builder(mainMethod.name)
                .addModifiers(SUSPEND)
                .receiver(ClassName("io.github.dehuckakpyt.telegrambot.container", "Container"))
                .addKdoc(mainMethod.description)
                .apply { mainMethod.arguments.filter { it.nameCamelCase != "chatId" }.forEach { addParameter(it.toParameter(withDoc = true)) } }
                .addStatement(statement)
                .returns(mainMethod.returnType.toClassTypeName())
                .build())
        }

        return this
    }

    val file = FileSpec.builder(apiPackageName, "TelegramBotApiHandling")
        .indent("    ")
        .addType(
            TypeSpec.classBuilder("TelegramBotApiHandling")
                .addKdoc("@author KScript")
                .addModifiers(ABSTRACT)
                .addProperty("bot", ClassName(telegramBotClassPackageName, "TelegramBot"), ABSTRACT, PROTECTED)
                .addFunctions()
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createTelegramBotApiExtHandling(methods: List<List<Method>>) {
    fun convertArgumentStatement(argName: String, from: Type, to: Type): String {
        if (from is LongType && to is StringType) return "$argName.toString()"
        if (from is StringType && to is ReferenceType && to.reference == "Input") return "StringInput($argName)"
        throw RuntimeException("Unexpected types to convert")
    }

    fun TypeSpec.Builder.addFunctions(): TypeSpec.Builder {
        methods.forEach { allMethods ->
            val mainMethod = allMethods.first()
            allMethods.drop(1).forEach { method ->
                var statement = "return ${method.name}("
                for ((i, argument) in method.arguments.withIndex()) {
                    statement += if (argument.typeInfo == mainMethod.arguments[i].typeInfo) {
                        if (argument.nameCamelCase == "chatId")
                            ""
                        else
                            "\n    ${argument.nameCamelCase} = ${argument.nameCamelCase},"
                    } else {
                        if (argument.required) {
                            "\n    ${argument.nameCamelCase} = ${convertArgumentStatement(argument.nameCamelCase, argument.typeInfo, mainMethod.arguments[i].typeInfo)},"
                        } else {
                            "\n    ${argument.nameCamelCase} = ${argument.nameCamelCase}?.let { ${convertArgumentStatement(argument.nameCamelCase, argument.typeInfo, mainMethod.arguments[i].typeInfo)} },"
                        }
                    }
                }
                statement += "\n)"

                if ((method.arguments.any { it.nameCamelCase == "chatId" && it.typeInfo !is StringType }).not())
                    addFunction(FunSpec.builder(method.name)
                        .addModifiers(SUSPEND)
                        .receiver(ClassName("io.github.dehuckakpyt.telegrambot.container", "Container"))
                        .addKdoc(method.description)
                        .apply { method.arguments.filter { it.nameCamelCase != "chatId" }.forEach { addParameter(it.toParameter(withDoc = true)) } }
                        .addStatement(statement)
                        .returns(method.returnType.toClassTypeName())
                        .build())
            }
        }

        return this
    }

    val file = FileSpec.builder(apiPackageName, "TelegramBotApiExtHandling")
        .indent("    ")
        .addImport(inputClassPackageName, "StringInput")
        .addType(
            TypeSpec.classBuilder("TelegramBotApiExtHandling")
                .addKdoc("@author KScript")
                .superclass(ClassName(apiPackageName, "TelegramBotApiHandling"))
                .addModifiers(ABSTRACT)
                .addFunctions()
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createApiMethods(methods: List<List<Method>>) {
    val file = FileSpec.builder(apiPackageName, "TelegramBotApi")
        .indent("    ")
        .addType(
            TypeSpec.interfaceBuilder("TelegramBotApi")
                .addKdoc("@author KScript")
                .addTelegramBotMethods(methods)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createExtensionApiMethods(methods: List<List<Method>>) {
    fun convertArgumentStatement(argName: String, from: Type, to: Type): String {
        if (from is LongType && to is StringType) return "$argName.toString()"
        if (from is StringType && to is ReferenceType && to.reference == "Input") return "StringInput($argName)"
        throw RuntimeException("Unexpected types to convert")
    }

    fun TypeSpec.Builder.addFunctions(): TypeSpec.Builder {
        methods.forEach { allMethods ->
            val mainMethod = allMethods.first()
            allMethods.drop(1).forEach { method ->
                var statement = "return ${method.name}("
                for ((i, argument) in method.arguments.withIndex()) {
                    if (argument.typeInfo == mainMethod.arguments[i].typeInfo) {
                        statement += "\n    ${argument.nameCamelCase} = ${argument.nameCamelCase},"
                    } else {
                        if (argument.required) {
                            statement += "\n    ${argument.nameCamelCase} = ${convertArgumentStatement(argument.nameCamelCase, argument.typeInfo, mainMethod.arguments[i].typeInfo)},"
                        } else {
                            statement += "\n    ${argument.nameCamelCase} = ${argument.nameCamelCase}?.let { ${convertArgumentStatement(argument.nameCamelCase, argument.typeInfo, mainMethod.arguments[i].typeInfo)} },"
                        }
                    }
                }
                statement += "\n)"

                addFunction(FunSpec.builder(method.name)
                    .addModifiers(SUSPEND)
                    .addKdoc(method.description)
                    .apply { method.arguments.forEach { addParameter(it.toParameter(withDoc = true)) } }
                    .addStatement(statement)
                    .returns(method.returnType.toClassTypeName())
                    .build())
            }
        }

        return this
    }

    val file = FileSpec.builder(apiPackageName, "TelegramBotApiExt")
        .indent("    ")
        .addImport(inputClassPackageName, "StringInput")
        .addType(
            TypeSpec.interfaceBuilder("TelegramBotApiExt")
                .addSuperinterface(ClassName(apiPackageName, "TelegramBotApi"))
                .addKdoc("@author KScript")
                .addFunctions()
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createMethods(methods: List<List<Method>>, objects: List<Object>) {
    val objectsByName = objects.associateBy { it.name }
    val file1 = FileSpec.builder(telegramBotClassPackageName, "TelegramBot")
        .indent("    ")
        .addType(
            TypeSpec.interfaceBuilder("TelegramBot")
                .addKdoc("Created on $todayFormattedDate.\n\n@author KScript")
                .addSuperinterface(ClassName(apiPackageName, "TelegramBotApiExt"))
                .addProperty("username", String::class)
                .addProperty("client", ClassName("io.github.dehuckakpyt.telegrambot.api.client", "TelegramApiClient"))
                .build()
        )
        .build()

    val file2 = FileSpec.builder(telegramBotClassPackageName, "TelegramBotImpl")
        .indent("    ")
        .addInternalImports(methods)
        .addImport("io.github.dehuckakpyt.telegrambot.ext", "appendContent", "appendContentIfNotNull", "appendIfNotNull")
        .addImport("io.github.dehuckakpyt.telegrambot.ext", "toJson")
        .addType(
            TypeSpec.classBuilder("TelegramBotImpl")
                .addKdoc("Created on $todayFormattedDate.\n\n@author KScript")
                .addSuperinterface(ClassName(telegramBotClassPackageName, "TelegramBot"))
                .primaryConstructor(FunSpec.constructorBuilder()
                    .addParameter("token", String::class)
                    .addParameter("username", String::class)
                    .addParameter("eventManager", ClassName("io.github.dehuckakpyt.telegrambot.event.managing", "TelegramBotEventManager"))
                    .build())
                .addProperty(PropertySpec.builder("username", String::class, OVERRIDE)
                    .initializer("username")
                    .build())
                .addProperty(PropertySpec.builder("eventManager", ClassName("io.github.dehuckakpyt.telegrambot.event.managing", "TelegramBotEventManager"), PRIVATE)
                    .initializer("eventManager")
                    .build())
                .addProperty(PropertySpec.builder("client", ClassName("io.github.dehuckakpyt.telegrambot.api.client", "TelegramApiClient"), OVERRIDE)
                    .initializer("TelegramApiClient(token)")
                    .build())
                .addTelegramBotImplMethods(methods, objectsByName)
                .addFunction(FunSpec.builder("afterMethod")
                    .addModifiers(PRIVATE, SUSPEND, INLINE)
                    .addTypeVariable(TypeVariableName("T"))
                    .receiver(TypeVariableName("T"))
                    .returns(TypeVariableName("T"))
                    .addParameter("methodName", String::class)
                    .addParameter(ParameterSpec.builder("collectArguments", LambdaTypeName.get(receiver = ClassName("kotlin.collections", "MutableMap").parameterizedBy(String::class.asTypeName(), Any::class.asTypeName().copy(nullable = true)), returnType = Unit::class.asTypeName()), CROSSINLINE).defaultValue("{}").build())
                    .addCode("""
                        |eventManager.sendAfterMethodEvent(methodName, this, collectArguments)
                        |
                        |return this
                    """.trimMargin())
                    .build())
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file1.writeTo(coreModulePath)
        file2.writeTo(coreModulePath)
    }
}

fun FileSpec.Builder.addInternalImports(groupedMethods: List<List<Method>>) = apply {
    groupedMethods.forEach { methods ->
        val mainMethod = methods.first()
        if (mainMethod.arguments.size == 0) return@forEach

        if (mainMethod.maybeMultipart.not()) {
            addImport(objectsInternalPackageName, CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, mainMethod.name) + (mainMethod.postJsonClassNamePostfix ?: ""))
        }
    }
}

fun TypeSpec.Builder.addTelegramBotMethods(groupedMethods: List<List<Method>>) = apply {
    groupedMethods.forEach { methods ->
        val mainMethod = methods.first()
        addFunction(
            FunSpec.builder(mainMethod.name)
                .addKdoc(mainMethod.description)
                .addModifiers(ABSTRACT, SUSPEND)
                .addParameters(mainMethod.arguments, withDoc = true)
                .returns(mainMethod.returnType.toClassTypeName())
                .build()
        )
    }
}

suspend fun TypeSpec.Builder.addTelegramBotImplMethods(groupedMethods: List<List<Method>>, objectsByName: Map<String, Object>) = apply {
    groupedMethods.forEach { methods ->
        val method = methods.first()

        addFunction(
            FunSpec.builder(method.name)
                .addModifiers(OVERRIDE, SUSPEND)
                .addParameters(method.arguments, specifyNull = false)
                .returns(method.returnType.toClassTypeName())
                .addGetMethodIfNecessary(method)
                .addPostMethodJsonIfNecessary(method)
                .addPostMethodMultipartIfNecessary(method, objectsByName)
                .build()
        )
    }
}

fun FunSpec.Builder.addGetMethodIfNecessary(method: Method): FunSpec.Builder {
    if (method.arguments.size != 0) return this

    val returnType = method.returnType.toClassTypeName()
    val returnClassName = when (returnType) {
        is ClassName -> returnType.simpleName
        is ParameterizedTypeName -> "${returnType.rawType.simpleName}<${(returnType.typeArguments.first() as ClassName).simpleName}>"
        else -> returnType.toString()
    }

    addStatement("return client.get<$returnClassName>(\"${method.name}\")\n    .afterMethod(\"${method.name}\")")

    return this
}

suspend fun FunSpec.Builder.addPostMethodJsonIfNecessary(method: Method): FunSpec.Builder {
    if (method.arguments.size == 0 || method.maybeMultipart) return this

    val obj = method.toPropertiesObject()
    createInternalPropertiesObject(obj)

    val returnType = method.returnType.toClassTypeName()
    val returnClassName = when (returnType) {
        is ClassName -> returnType.simpleName
        is ParameterizedTypeName -> "${returnType.rawType.simpleName}<${(returnType.typeArguments.first() as ClassName).simpleName}>"
        else -> returnType.toString()
    }

    addStatement("return client.postJson<$returnClassName>(\"${method.name}\"," +
            "\n    ${obj.name}(" +
            obj.properties.joinToString(",") { "\n        ${it.nameCamelCase} = ${it.nameCamelCase}" } +
            "\n    )\n)" +
            ".afterMethod(\"${method.name}\") {" +
            obj.properties.joinToString("") { "\n    put(\"${it.nameCamelCase}\", ${it.nameCamelCase})" } +
            "\n}")

    return this
}

fun FunSpec.Builder.addPostMethodMultipartIfNecessary(method: Method, objectsByName: Map<String, Object>): FunSpec.Builder {
    if (method.arguments.size == 0 || method.maybeMultipart.not()) return this

    val returnType = method.returnType.toClassTypeName()
    val returnClassName = when (returnType) {
        is ClassName -> returnType.simpleName
        is ParameterizedTypeName -> "${returnType.rawType.simpleName}<${(returnType.typeArguments.first() as ClassName).simpleName}>"
        else -> returnType.toString()
    }

    val statement = buildString {
        append("return client.postMultiPart<$returnClassName>(\"${method.name}\") {")
        method.arguments.forEach { arg -> append("\n    ").append(arg.toAppendStatement()) }
        method.arguments.forEach { arg -> arg.toAppendContentInsideObjectsStatements(objectsByName)?.let { statements -> statements.forEach { statement -> append("\n    ").append(statement) } } }
        append("\n}.afterMethod(\"${method.name}\") {")
        append(method.arguments.joinToString(separator = "") { "\n    put(\"${it.nameCamelCase}\", ${it.nameCamelCase})" })
        append("\n}")
    }

    addStatement(statement)

    return this
}

fun Argument.toAppendContentInsideObjectsStatements(objectsByName: Map<String, Object>): List<String>? {
    if (typeInfo !is ReferenceType && typeInfo !is ArrayType) return null

    if (typeInfo is ReferenceType) {
        val reference = (typeInfo as ReferenceType).reference
        if (reference in setOf("Input", "InputFile", "ContentInput", "ReplyMarkup")) return null

        val paths = mutableListOf<String>()
        evaluateInputPathsInside(nameCamelCase, reference, objectsByName, paths)

        return paths
    }

    if (typeInfo is ArrayType) {
        val array = (typeInfo as ArrayType).array
        if (array !is ReferenceType) return null
        val reference = array.reference
        if (reference in setOf("Input", "InputFile", "ContentInput", "ReplyMarkup")) return null

        val paths = mutableListOf<String>()
        evaluateInputPathsInside("$nameCamelCase.forEach { $nameCamelCase -> $nameCamelCase", reference, objectsByName, paths)

        return paths
    }
    return null
}

fun evaluateInputPathsInside(path: String, currentObjectName: String, objectsByName: Map<String, Object>, paths: MutableList<String>): Unit {
    val currentObject = objectsByName[currentObjectName]!!
    if (currentObject is PropertiesObject) {
        for (property in currentObject.properties) {
            if (property.typeInfo.isCommon) continue
            if (property.typeInfo is ReferenceType) {
                val currentPath = "$path.${property.nameCamelCase}"
                if (property.typeInfo.reference in setOf("Input", "InputFile", "ContentInput")) {
                    paths.add("${property.required.appendContentMethodName}($currentPath)")
                } else {
                    evaluateInputPathsInside(currentPath, property.typeInfo.reference, objectsByName, paths)
                }
            }
            if (property.typeInfo is AnyOfType && property.typeInfo.anyOf.size == 2 && property.typeInfo.anyOf.any { it is StringType } && property.typeInfo.anyOf.any { it is ReferenceType && it.reference in setOf("Input", "InputFile", "ContentInput") }) {
                val nullable = property.required.not() || path.contains('?')
                val appendContentMethodName = if (nullable) "appendContentIfNotNull" else "appendContent"
                if (!path.contains("forEach")) {
                    val currentPath = "$path.${property.nameCamelCase}"
                    paths.add("\n    $appendContentMethodName($currentPath)")
                } else {
                    val (firstPart, secondPart) = path.split(" -> ")
                    paths.add("\n    $firstPart ->\n        $appendContentMethodName($secondPart.${property.nameCamelCase})\n    }")
                }
            }
//        if (property.typeInfo is ArrayType && property.typeInfo.array is ReferenceType) {
//            val dot = if (property.required.not() || path.contains('?')) "?." else "."
//            val currentPath = "$path${dot}forEach { ${property.nameCamelCase} -> ${property.nameCamelCase}"
//            if (property.typeInfo.array.reference in setOf("Input", "InputFile", "ContentInput")) {
//                throw RuntimeException("Unexpected property type")
//            } else {
//                evaluateInputPathsInside(currentPath, property.typeInfo.array.reference, objectsByName, paths)
//            }
//        }
//        if (property.typeInfo is ArrayType && property.typeInfo.array is AnyOfType) {
//            val type = property.typeInfo.array.anyOf.first()
//            if (type !is ReferenceType) throw RuntimeException("Unexpected property type")
//            val nextObject = objectsByName[type.reference]!!
//            val dot = if (property.required.not() || path.contains('?')) "?." else "."
//            val currentPath = "$path${dot}forEach { ${property.nameCamelCase} -> ${property.nameCamelCase}"
//            evaluateInputPathsInside(currentPath, nextObject.parentName!!, objectsByName, paths)
//        }
        }
    }
    if (currentObject is AnyOfObject) {
        for (property in currentObject.properties!!) {
            if (property.typeInfo.isCommon) continue
            if (property.typeInfo is ReferenceType) {
                val currentPath = "$path.${property.nameCamelCase}"
                if (property.typeInfo.reference in setOf("Input", "InputFile", "ContentInput")) {
                    paths.add("${property.required.appendContentMethodName}($currentPath)")
                } else {
                    evaluateInputPathsInside(currentPath, property.typeInfo.reference, objectsByName, paths)
                }
            }
            if (property.typeInfo is AnyOfType && property.typeInfo.anyOf.size == 2 && property.typeInfo.anyOf.any { it is StringType } && property.typeInfo.anyOf.any { it is ReferenceType && it.reference in setOf("Input", "InputFile", "ContentInput") }) {
                val nullable = property.required.not() || path.contains('?')
                val appendContentMethodName = if (nullable) "appendContentIfNotNull" else "appendContent"
                if (!path.contains("forEach")) {
                    val currentPath = "$path.${property.nameCamelCase}"
                    paths.add("\n    $appendContentMethodName($currentPath)")
                } else {
                    val (firstPart, secondPart) = path.split(" -> ")
                    paths.add("\n    $firstPart ->\n        $appendContentMethodName($secondPart.${property.nameCamelCase})\n    }")
                }
            }
//        if (property.typeInfo is ArrayType && property.typeInfo.array is ReferenceType) {
//            val dot = if (property.required.not() || path.contains('?')) "?." else "."
//            val currentPath = "$path${dot}forEach { ${property.nameCamelCase} -> ${property.nameCamelCase}"
//            if (property.typeInfo.array.reference in setOf("Input", "InputFile", "ContentInput")) {
//                throw RuntimeException("Unexpected property type")
//            } else {
//                evaluateInputPathsInside(currentPath, property.typeInfo.array.reference, objectsByName, paths)
//            }
//        }
//        if (property.typeInfo is ArrayType && property.typeInfo.array is AnyOfType) {
//            val type = property.typeInfo.array.anyOf.first()
//            if (type !is ReferenceType) throw RuntimeException("Unexpected property type")
//            val nextObject = objectsByName[type.reference]!!
//            val dot = if (property.required.not() || path.contains('?')) "?." else "."
//            val currentPath = "$path${dot}forEach { ${property.nameCamelCase} -> ${property.nameCamelCase}"
//            evaluateInputPathsInside(currentPath, nextObject.parentName!!, objectsByName, paths)
//        }
        }
    }
}

val Boolean.appendMethodName get(): String = if (this) "append" else "appendIfNotNull"
val Boolean.appendContentMethodName get(): String = if (this) "appendContent" else "appendContentIfNotNull"
val Type.isCommon: Boolean get() = type in setOf("integer", "string", "bool", "float", "long")

fun Argument.toAppendStatement(): String = when {
    (typeInfo.isCommon) -> {
        "${required.appendMethodName}(\"$name\", $nameCamelCase)"
    }

    (typeInfo is ReferenceType && (typeInfo as ReferenceType).reference in setOf("InputFile", "Input", "ContentInput")) -> {
        when ((typeInfo as ReferenceType).reference) {
            "InputFile", "Input" -> "${required.appendContentMethodName}(\"$name\", $nameCamelCase)"
            "ContentInput" -> "${required.appendContentMethodName}(\"$name\", $nameCamelCase)"
            else -> throw RuntimeException("Failed to convert argument to appendContent statement")
        }
    }

    (typeInfo.type in setOf("reference", "array")) -> {
        "${required.appendMethodName}(\"$name\", client.toJson($nameCamelCase))"
    }

    else -> throw RuntimeException("Failed to convert argument to append statement")
}

suspend fun createInternalPropertiesObject(obj: PropertiesObject) {
    val parameters = obj.properties.map { it.toInternalParameterSpec() }
    val properties = obj.properties.map { it.toInternalPropertySpec() }
    val constructor = FunSpec.constructorBuilder().apply {
        addParameters(parameters)
    }.build()
    val file = FileSpec.builder(objectsInternalPackageName, obj.name)
        .indent("    ")
        .addType(
            TypeSpec.classBuilder(obj.name)
                .addKdoc("@author KScript")
                .addModifiers(DATA, INTERNAL)
                .primaryConstructor(constructor)
                .addProperties(properties)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

fun Method.toPropertiesObject(): PropertiesObject = PropertiesObject(
    name = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, this.name) + (postJsonClassNamePostfix ?: ""),
    properties = arguments.mapTo(mutableListOf()) { it.toProperty() },
    type = "property",
    documentationLink = documentationLink
)

fun Argument.toProperty(): Property = Property(
    name = name,
    description = description,
    required = required,
    typeInfo = typeInfo
)

inline fun <T> T.alsoIf(predicate: (T) -> Boolean, block: (T) -> Unit): T {
    if (predicate(this).not()) return this

    return this.also(block)
}

inline fun <T> T.alsoIf(condition: Boolean, block: (T) -> Unit): T {
    if (condition.not()) return this

    return this.also(block)
}

fun FunSpec.Builder.addParameters(arguments: List<Argument>, specifyNull: Boolean = true, withDoc: Boolean = false) = apply {
    arguments.forEach { argument ->
        addParameter(argument.toParameter(specifyNull, withDoc))
    }
}

fun Argument.toParameter(specifyNull: Boolean = true, withDoc: Boolean = false): ParameterSpec = ParameterSpec.builder(nameCamelCase, typeInfo.toMethodTypeName().copy(nullable = required.not()))
    .also { if (specifyNull && required.not()) it.defaultValue("null") }
    .alsoIf(withDoc) { it.addKdoc(description) }
    .build()

fun Type.toMethodTypeName(): TypeName = when (this) {
    is IntegerType -> Int::class.asClassName()
    is LongType -> Long::class.asClassName()
    is StringType -> String::class.asClassName()
    is BooleanType -> Boolean::class.asClassName()
    is FloatType -> Double::class.asClassName()
    is AnyOfType -> throw RuntimeException("Unexpected type $this")
    is ReferenceType -> when (reference) {
        "InputFile", "Input" -> ClassName(inputClassPackageName, "Input")
        "ContentInput" -> ClassName(inputClassPackageName, "ContentInput")
        else -> ClassName(objectsPackageName, reference)
    }

    is ArrayType -> Iterable::class.asClassName().parameterizedBy(array.toMethodTypeName())
    else -> throw RuntimeException("Unexpected type $this")
}

suspend fun createObjects(objects: List<Object>) {
    val objectsByName = objects.associateBy { it.name }
    objects.forEach { currentObject ->
        if (currentObject is AnyOfObject) createAnyOfObject(currentObject, objectsByName)
    }
    objects.forEach { currentObject ->
        if (currentObject.name == "InputFile") return@forEach

        if (currentObject is PropertiesObject) createPropertiesObject(currentObject)
        if (currentObject is UnknownObject) createUnknownObject(currentObject)
    }
}

suspend fun createPropertiesObject(obj: PropertiesObject) {
    if (obj.properties.any { it.typeInfo is AnyOfType }) {
        createMultiTypePropertiesObject(obj)
    } else {
        createSimplePropertiesObject(obj)
    }
}

suspend fun createMultiTypePropertiesObject(obj: PropertiesObject) {
    if (obj.properties.any { it.typeInfo.isMultiPropertyIntLongAndString }) {
        createMultiTypeIntLongAndStringPropertiesObject(obj)
    } else {
        createMultiTypeInputFileAndStringPropertiesObject(obj)
    }
}

suspend fun createAnyOfObject(obj: AnyOfObject, objectsByName: Map<String, Object>) {
    var referenceObjects = obj.anyOf.map { objectsByName[(it as ReferenceType).reference]!! as PropertiesObject }
    val typePropertyName = referenceObjects.firstOrNull { it.typePropertyName != null }?.typePropertyName
    if (typePropertyName == null) {
        val file = FileSpec.builder(objectsPackageName, obj.name)
            .indent("    ")
            .addType(
                TypeSpec.defaultInterfaceBuilder(obj)
                    .alsoIf(obj.properties != null) { typeSpec ->
                        obj.properties!!.forEach { property ->
                            typeSpec.addProperty(PropertySpec.builder(property.nameCamelCase, property.typeInfo.toClassTypeName().copy(nullable = property.required.not()), ABSTRACT).build())
                        }
                    }
                    .build()
            )
            .build()

        withContext(Dispatchers.IO) {
            file.writeTo(coreModulePath)
        }
        return
    }
    val defaultImplName = referenceObjects.singleOrNull { it.typePropertyValue == null }?.name
    if (defaultImplName != null) referenceObjects = referenceObjects.filter { it.name != defaultImplName }
    val jsonSubTypes = referenceObjects.map { refObj ->
        AnnotationSpec.builder(JsonSubTypes.Type::class)
            .addMember("value = ${refObj.name}::class")
            .addMember("name = \"${refObj.typePropertyValue}\"")
            .build()
    }.let { subTypes ->
        AnnotationSpec.builder(JsonSubTypes::class)
            .addMember("\n    %L,".repeat(subTypes.size) + "\n", *subTypes.toTypedArray())
            .build()
    }
    val jsonTypeInfo = AnnotationSpec.builder(JsonTypeInfo::class)
        .addMember("use = JsonTypeInfo.Id.NAME")
        .addMember("include = JsonTypeInfo.As.PROPERTY")
        .addMember("property = \"$typePropertyName\"")
        .addMember("visible = true")
        .also { if (defaultImplName != null) it.addMember("defaultImpl = $defaultImplName::class") }
        .build()
    val file = FileSpec.builder(objectsPackageName, obj.name)
        .indent("    ")
        .addType(
            TypeSpec.defaultInterfaceBuilder(obj)
                .alsoIf(obj.properties != null) { typeSpec ->
                    obj.properties!!.forEach { property ->
                        typeSpec.addProperty(PropertySpec.builder(property.nameCamelCase, property.typeInfo.toClassTypeName().copy(nullable = property.required.not()), ABSTRACT).build())
                    }
                }
                .addAnnotation(jsonTypeInfo)
                .addAnnotation(jsonSubTypes)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createMultiTypeIntLongAndStringPropertiesObject(obj: PropertiesObject) {
    val primaryConstructorBuilder = FunSpec.constructorBuilder()
    val secondaryConstructorBuilder = FunSpec.constructorBuilder()
    val properties = mutableListOf<PropertySpec>()
    val callThisConstructorArgs = mutableListOf<String>()

    obj.properties.forEach { property ->
        if (property.typeInfo !is AnyOfType) {
            if (property.typeInfo.constValue == null && property.alwaysNull != true) {
                primaryConstructorBuilder.addParameter(property.toParameterSpec())
                secondaryConstructorBuilder.addParameter(property.toParameterSpec())
                callThisConstructorArgs.add(property.nameCamelCase)
            }
            properties.add(property.toPropertySpec())
        } else {
            if (property.alwaysNull != true) {
                primaryConstructorBuilder.addParameter(property.toParameterSpec(String::class.asClassName()))
                secondaryConstructorBuilder.addParameter(property.toParameterSpec(property.typeInfo.anyOf.first { it.type != "string" }.toClassTypeName()))
                callThisConstructorArgs.add(property.nameCamelCase + ".toString()")
            }
            properties.add(property.toPropertySpec(String::class.asClassName()))
        }
    }
    val file = FileSpec.builder(objectsPackageName, obj.name)
        .indent("    ")
        .addType(
            TypeSpec.defaultClassBuilder(obj)
                .primaryConstructor(primaryConstructorBuilder.build())
                .addFunction(secondaryConstructorBuilder.callThisConstructor(*callThisConstructorArgs.toTypedArray()).build())
                .addProperties(properties)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createMultiTypeInputFileAndStringPropertiesObject(obj: PropertiesObject) {
    val primaryConstructorBuilder = FunSpec.constructorBuilder()
    val secondaryConstructorBuilder = FunSpec.constructorBuilder()
    val properties = mutableListOf<PropertySpec>()
    val callThisConstructorArgs = mutableListOf<String>()

    obj.properties.forEach { property ->
        if (property.typeInfo !is AnyOfType) {
            if (property.typeInfo.constValue == null && property.alwaysNull != true) {
                primaryConstructorBuilder.addParameter(property.toParameterSpec())
                secondaryConstructorBuilder.addParameter(property.toParameterSpec())
                callThisConstructorArgs.add(property.nameCamelCase)
            }
            properties.add(property.toPropertySpec())
        } else {
            if (property.alwaysNull != true) {
                primaryConstructorBuilder.addParameter(property.toParameterSpec(ClassName(inputClassPackageName, "Input")))
                secondaryConstructorBuilder.addParameter(property.toParameterSpec(String::class.asClassName()))
                callThisConstructorArgs.add("StringInput(${property.nameCamelCase})")
            }
            properties.add(property.toPropertySpec(ClassName(inputClassPackageName, "Input")))
        }
    }
    val file = FileSpec.builder(objectsPackageName, obj.name)
        .indent("    ")
        .addImport(inputClassPackageName, "StringInput")
        .addType(
            TypeSpec.defaultClassBuilder(obj)
                .primaryConstructor(primaryConstructorBuilder.build())
                .addFunction(secondaryConstructorBuilder.callThisConstructor(*callThisConstructorArgs.toTypedArray()).build())
                .addProperties(properties)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createSimplePropertiesObject(obj: PropertiesObject) {
    val parameters = obj.properties.filter { it.typeInfo.constValue == null && it.alwaysNull != true }.map { it.toParameterSpec() }
    val properties = obj.properties.map { it.toPropertySpec() }
    val constructor = FunSpec.constructorBuilder().apply {
        addParameters(parameters)
    }.build()
    val file = FileSpec.builder(objectsPackageName, obj.name)
        .indent("    ")
        .addType(
            TypeSpec.defaultClassBuilder(obj)
                .primaryConstructor(constructor)
                .addProperties(properties)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

suspend fun createUnknownObject(obj: UnknownObject) {
    val file = FileSpec.builder(objectsPackageName, obj.name)
        .indent("    ")
        .addType(
            TypeSpec.defaultClassBuilder(obj)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(coreModulePath)
    }
}

fun Property.toInternalParameterSpec(type: TypeName? = null, name: String = nameCamelCase, nullable: Boolean = required.not()): ParameterSpec = ParameterSpec.builder(
    name = name,
    type = (type ?: typeInfo.toInternalClassTypeName()).copy(nullable = nullable)
).alsoIf(nullable) {
    it.defaultValue("null")
}.build()

fun Property.toParameterSpec(type: TypeName? = null, name: String = nameCamelCase, nullable: Boolean = required.not()): ParameterSpec = ParameterSpec.builder(
    name = name,
    type = (type ?: typeInfo.toClassTypeName()).copy(nullable = nullable)
).also {
    if (nullable) {
        it.defaultValue("null")
    }
}.addKdoc("%L", description).build()

fun Property.toPropertySpec(type: TypeName? = null, name: String = nameCamelCase, nullable: Boolean = required.not(), modifiers: Iterable<KModifier> = emptySet()): PropertySpec = PropertySpec.builder(
    name = name,
    type = (type ?: typeInfo.toClassTypeName()).copy(nullable = nullable),
    modifiers = modifiers.let { mods -> return@let if (needBeOverridden != null && needBeOverridden!!) mods.toMutableSet().also { modsSet -> modsSet.add(OVERRIDE) } else mods }
).initializer(
    when {
        typeInfo.constValue != null -> "\"${typeInfo.constValue}\""
        alwaysNull == true -> "null"
        else -> name
    }
//    if (typeInfo.constValue != null) "\"${typeInfo.constValue}\"" else name
).alsoIf(alwaysNull != true) {
    it.addAnnotation(
        AnnotationSpec.builder(JsonProperty::class)
            .addMember("\"${this.name}\"")
            .useSiteTarget(AnnotationSpec.UseSiteTarget.GET)
            .build()
    )
    if (this.typeInfo.constValue == null) {
        it.addAnnotation(
            AnnotationSpec.builder(JsonProperty::class)
                .addMember("\"${this.name}\"")
                .useSiteTarget(AnnotationSpec.UseSiteTarget.PARAM)
                .build()
        )
    }
}.alsoIf(alwaysNull == true) {
    it.addAnnotation(
        AnnotationSpec.builder(JsonIgnore::class)
            .useSiteTarget(AnnotationSpec.UseSiteTarget.GET)
            .build()
    )
}.build()

fun Property.toInternalPropertySpec(type: TypeName? = null, name: String = nameCamelCase, nullable: Boolean = required.not()): PropertySpec = PropertySpec.builder(
    name = name,
    type = (type ?: typeInfo.toInternalClassTypeName()).copy(nullable = nullable),
).initializer(name).addAnnotation(
    AnnotationSpec.builder(JsonProperty::class)
        .addMember("\"${this.name}\"")
        .useSiteTarget(AnnotationSpec.UseSiteTarget.GET)
        .build()
).build()

fun TypeSpec.Companion.defaultClassBuilder(obj: Object): TypeSpec.Builder = classBuilder(obj.name).apply {
    addKdoc(
        CodeBlock.builder()
            .also { if (obj.description != null) it.add("%L", obj.description!!) }
            .add("\n\n@see [%L] (%L)", obj.name, obj.documentationLink)
            .add("\n\n@author KScript")
            .build()
    )
    if (obj.parentName != null) addSuperinterface(ClassName(objectsPackageName, obj.parentName!!))
    if (obj is PropertiesObject) {
        var size = obj.properties.size
        size -= obj.properties.count { it.typeInfo.constValue != null }
        if (size > 0) addModifiers(DATA)
    }
}

fun TypeSpec.Companion.defaultInterfaceBuilder(obj: Object): TypeSpec.Builder = interfaceBuilder(obj.name).apply {
    addKdoc(
        CodeBlock.builder()
            .also { if (obj.description != null) it.add("%L", obj.description!!) }
            .add("\n\n@see [%L] (%L)", obj.name, obj.documentationLink)
            .add("\n\n@author KScript")
            .build()
    )
    addModifiers(SEALED)
}

fun Type.toClassTypeName(): TypeName = when (this) {
    is IntegerType -> Int::class.asClassName()
    is LongType -> Long::class.asClassName()
    is StringType -> String::class.asClassName()
    is BooleanType -> Boolean::class.asClassName()
    is FloatType -> Double::class.asClassName()
    is AnyOfType -> tryToClassTypeNameFromAnyOfType()
    is ReferenceType -> when (reference) {
        "InputFile", "Input" -> ClassName(inputClassPackageName, "Input")
        "ContentInput" -> ClassName(inputClassPackageName, "ContentInput")
        else -> ClassName(objectsPackageName, reference)
    }

    is ArrayType -> List::class.asClassName().parameterizedBy(array.toClassTypeName())
    else -> throw RuntimeException("Unexpected type $this")
}

fun AnyOfType.tryToClassTypeNameFromAnyOfType(): TypeName {
    if (anyOf.size == 2 && anyOf.any { it is StringType } && anyOf.any { it is ReferenceType && it.reference in setOf("InputFile", "Input") }) {
        return ClassName(inputClassPackageName, "Input")
    }

    throw RuntimeException("Unexpected type $this")
}

fun Type.toInternalClassTypeName(): TypeName =
    if (this is ArrayType) Iterable::class.asClassName().parameterizedBy(array.toClassTypeName())
    else toClassTypeName()


val Type.isMultiPropertyIntLongAndString: Boolean
    get() {
        if (this !is AnyOfType) return false
        if (anyOf.size != 2) return false
        if (!anyOf.any { it.type == "string" }) return false
        if (!anyOf.any { it.type == "integer" || it.type == "long" }) return false
        return true
    }
val Property.nameCamelCase get() = name.toCamelCase()
val Argument.nameCamelCase get() = name.toCamelCase()

fun String.toCamelCase(): String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this)

suspend fun Contract.replaceMethodTypes() {
    val objectsByName = objects.associateBy(Object::name)

    methods.forEach { method ->
        val arguments = method.arguments
        for ((i, argument) in arguments.withIndex()) {
            // this param must be long
            if (method.name == "getUpdates" && argument.name == "offset") {
                if (argument.typeInfo is IntegerType) {
                    val typeInfo = argument.typeInfo
                    arguments[i] = argument.copy(typeInfo = (typeInfo as IntegerType).toLongType())
                }
            }
            // means that you can download file by this field
            if (argument.description.contains("exists on the Telegram servers")) {
                arguments[i] = argument.copy(typeInfo = AnyOfType("any_of", mutableListOf(StringType("string"), ReferenceType("reference", "Input"))))
            }
            // thumbnails you can upload only
            if (argument.name == "thumbnail" && argument.description.contains("Thumbnails can't be reused and can be only uploaded as a new file")) {
                arguments[i] = argument.copy(typeInfo = ReferenceType("reference", "ContentInput"))
            }
            // certificates you can upload only
            if (argument.name == "certificate" && argument.typeInfo is ReferenceType && (argument.typeInfo as ReferenceType).reference == "InputFile") {
                arguments[i] = argument.copy(typeInfo = ReferenceType("reference", "ContentInput"))
            }
            // all ids can be greater than Int.MAX
            if (argument.name == "id" || argument.name.endsWith("_id") || argument.name.endsWith("date") || argument.description.contains("may have more than 32 significant bits") || argument.description.contains("can be bigger than 2^31")) {
                if (argument.typeInfo is IntegerType) {
                    val typeInfo = argument.typeInfo
                    arguments[i] = argument.copy(typeInfo = (typeInfo as IntegerType).toLongType())
                }
                if (argument.typeInfo is AnyOfType) {
                    for ((index, typeAnyOf) in (argument.typeInfo as AnyOfType).anyOf.withIndex()) {
                        if (typeAnyOf !is IntegerType) continue
                        (argument.typeInfo as AnyOfType).anyOf[index] = typeAnyOf.toLongType()
                    }
                }
            }
            if (argument.name.endsWith("_ids") || argument.description.contains("may have more than 32 significant bits") || argument.description.contains("can be bigger than 2^31")) {
                if (argument.typeInfo is ArrayType && (argument.typeInfo as ArrayType).array.type == "integer") {
                    arguments[i] = argument.copy(typeInfo = ArrayType(argument.typeInfo.type, ((argument.typeInfo as ArrayType).array as IntegerType).toLongType()))
                }
            }

            if (argument.typeInfo is AnyOfType && (argument.typeInfo as AnyOfType).anyOf.all { it is ReferenceType }) {
                val interfaceName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, argument.name)
                if ((argument.typeInfo as AnyOfType).anyOf.any { objectsByName[(it as ReferenceType).reference]!!.parentName == null }) {
                    (argument.typeInfo as AnyOfType).anyOf.forEach { objectsByName[(it as ReferenceType).reference]!!.parentName = interfaceName }
                    val file = FileSpec.builder(objectsPackageName, interfaceName)
                        .indent("    ")
                        .addType(TypeSpec.interfaceBuilder(interfaceName)
                            .addKdoc("@author KScript")
                            .build())
                        .build()

                    withContext(Dispatchers.IO) {
                        file.writeTo(coreModulePath)
                    }
                }
                argument.typeInfo = ReferenceType("reference", interfaceName)
            }

            if ((argument.typeInfo is ArrayType) && ((argument.typeInfo as ArrayType).array is AnyOfType) && (((argument.typeInfo as ArrayType).array as AnyOfType).anyOf.first() is ReferenceType)) {
                val objectWithParent = objectsByName[(((argument.typeInfo as ArrayType).array as AnyOfType).anyOf.first() as ReferenceType).reference]
                argument.typeInfo = ArrayType("array", array = ReferenceType("reference", objectWithParent!!.parentName!!))
            }
        }
    }

    methods.filter { it.returnType is AnyOfType }.forEach { method ->
        // split methods, which returns Message or Boolean type like https://core.telegram.org/bots/api#editmessagetext
        if (method.description.contains("[Message](https://core.telegram.org/bots/api/#message) is returned, otherwise *True* is returned.") && method.description.contains("not an inline message")) {
            method.arguments.forEach { if (it.name == "chat_id" || it.name == "message_id" || it.name == "inline_message_id") it.required = true }
            val otherwiseMethod = method.copy(arguments = method.arguments.toMutableList())
            method.returnType = ReferenceType("reference", "Message")
            method.arguments = method.arguments.filter { it.name != "inline_message_id" }.toMutableList()
            otherwiseMethod.returnType = BooleanType("bool")
            otherwiseMethod.arguments = otherwiseMethod.arguments.filter { it.name != "chat_id" && it.name != "message_id" }.toMutableList()
            methods.add(otherwiseMethod)

            method.postJsonClassNamePostfix = "ByChatIdAndMessageId"
            otherwiseMethod.postJsonClassNamePostfix = "ByInlineMessageId"
        }
    }
    methods.forEach { method ->
        method.arguments = method.arguments.sortedWith(compareBy(Argument::required).reversed()).toMutableList()
    }
}

fun Contract.splitMethodsToOverloads(): List<List<Method>> = buildList {
    for (method in methods) {
        val groupedMethods = mutableListOf(method)
        var point = groupedMethods.nextMultipleType
        while (point != null) {
            val (mIndex, aIndex) = point
            val arguments = groupedMethods[mIndex].arguments
            val argument = arguments[aIndex]
            val (mainType, secondaryType) = argument.typeInfo.destructedTypes
            arguments[aIndex] = argument.copy(typeInfo = mainType)
            groupedMethods.add(groupedMethods[mIndex].copy(arguments = arguments.toMutableList().also { it[aIndex] = argument.copy(typeInfo = secondaryType) }))

            point = groupedMethods.nextMultipleType
        }

        add(groupedMethods)
    }
}

val List<Method>.nextMultipleType: Pair<Int, Int>?
    get() {
        withIndex().forEach { (mIndex, method) ->
            method.arguments.withIndex().forEach { (aIndex, arg) ->
                if (arg.typeInfo is AnyOfType) return mIndex to aIndex
            }
        }

        return null
    }
val Type.destructedTypes: Pair<Type, Type>
    get() {
        if (this !is AnyOfType) throw IllegalArgumentException("Nothing to destruct")
        val (first, second) = this.anyOf
        if (first is StringType && second is LongType) return first to second
        if (first is LongType && second is StringType) return second to first
        if (first is StringType && second is ReferenceType) return second to first
        if (first is ReferenceType && second is StringType) return first to second

        throw RuntimeException("Cant to destruct types of $anyOf")
    }

fun Contract.replaceObjectTypes() {
    val objectsByName = objects.associateBy(Object::name)
    objects.forEach { obj ->
        if (obj !is PropertiesObject) return@forEach
        val properties = obj.properties
        for ((i, property) in properties.withIndex()) {
            var _property = property
            // means that you can download file by this field
            if (_property.description.contains("exists on the Telegram servers")) {
                properties[i] = _property.copy(typeInfo = AnyOfType("any_of", mutableListOf(StringType("string"), ReferenceType("reference", "InputFile"))))
                _property = properties[i]
            }
            // thumbnail's you can upload only
            if (_property.name == "thumbnail" && _property.description.contains("Thumbnails can't be reused and can be only uploaded as a new file")) {
                properties[i] = _property.copy(typeInfo = ReferenceType("reference", "InputFile"))
                _property = properties[i]
            }
            // all ids can be greater than Int.MAX
            if (_property.name == "id" || _property.name.endsWith("_id") || _property.name.endsWith("date") || _property.description.contains("may have more than 32 significant bits") || _property.description.contains("can be bigger than 2^31")) {
                if (_property.typeInfo is IntegerType) {
                    val typeInfo = _property.typeInfo
                    properties[i] = _property.copy(typeInfo = (typeInfo as IntegerType).toLongType())
                    _property = properties[i]
                }
                if (_property.typeInfo is AnyOfType) {
                    for ((index, typeAnyOf) in (_property.typeInfo as AnyOfType).anyOf.withIndex()) {
                        if (typeAnyOf !is IntegerType) continue
                        (_property.typeInfo as AnyOfType).anyOf[index] = typeAnyOf.toLongType()
                    }
                }
            }
            if (_property.name.endsWith("_ids") || _property.description.contains("may have more than 32 significant bits") || _property.description.contains("can be bigger than 2^31")) {
                if (_property.typeInfo is ArrayType && (_property.typeInfo as ArrayType).array.type == "integer") {
                    properties[i] = _property.copy(typeInfo = ArrayType(_property.typeInfo.type, ((_property.typeInfo as ArrayType).array as IntegerType).toLongType()))
                    _property = properties[i]
                }
            }
            val defaultValueGroupValues = getDefaultValueFromDescriptionRegex.find(_property.description)?.groupValues
            if (defaultValueGroupValues != null && defaultValueGroupValues.any { it.isNotBlank() }) {
                val defaultValue = defaultValueGroupValues[1].ifBlank { defaultValueGroupValues[2] }
                if (_property.typeInfo is LongType) {
                    properties[i] = _property.copy(typeInfo = (_property.typeInfo as LongType).copy(default = defaultValue.toLong()))
                    _property = properties[i]
                }
                if (_property.typeInfo is IntegerType) {
                    properties[i] = _property.copy(typeInfo = (_property.typeInfo as IntegerType).copy(default = defaultValue.toInt()))
                    _property = properties[i]
                }
                if (_property.typeInfo is StringType) {
                    properties[i] = _property.copy(typeInfo = (_property.typeInfo as StringType).copy(default = defaultValue))
                    _property = properties[i]
                }
                if (obj.typePropertyName == null) obj.typePropertyName = _property.name
                if (obj.typePropertyValue == null) obj.typePropertyValue = defaultValue
            }
            val mustBeGroupValues = getMustBeValueFromDescriptionRegex.find(_property.description)?.groupValues
            if (mustBeGroupValues != null && mustBeGroupValues[1].isNotBlank()) {
                _property.typeInfo.constValue = mustBeGroupValues[1]
                _property = properties[i]
            }
        }
    }

    objects.forEach { obj ->
        if (obj !is AnyOfObject) return@forEach
        val referenceObjects = obj.anyOf.map { objectsByName[(it as ReferenceType).reference]!! as PropertiesObject }
        referenceObjects.forEach { it.parentName = obj.name }

        val propertiesByName = referenceObjects.flatMap { it.properties }.groupBy { it.name }

        for ((propertyName, properties) in propertiesByName) {
            if (properties.size != referenceObjects.size) continue

            if (obj.properties == null) obj.properties = mutableListOf()
            val parentProperty = Property(
                name = propertyName,
                description = "",
                required = properties.all { it.required },
                typeInfo = properties.first().typeInfo
            )
            obj.properties!!.add(parentProperty)
            properties.forEach { it.needBeOverridden = true }
        }

        for ((propertyName, properties) in propertiesByName) {
            if (properties.size == referenceObjects.size) continue
            if (!properties.any { property ->
                    (property.typeInfo is ReferenceType && property.typeInfo.reference in setOf("InputFile", "Input"))
                            || (property.typeInfo is AnyOfType && property.typeInfo.anyOf.size == 2 && property.typeInfo.anyOf.any { type -> type is StringType } && property.typeInfo.anyOf.any { type -> type is ReferenceType && type.reference in setOf("InputFile", "Input") })
                }) continue

            if (obj.properties == null) obj.properties = mutableListOf()
            val parentProperty = Property(
                name = propertyName,
                description = "",
                required = false,
                typeInfo = AnyOfType("any_of", mutableListOf(StringType("string"), ReferenceType("reference", "Input")))
            )
            obj.properties!!.add(parentProperty)
            properties.forEach { it.needBeOverridden = true }

            for (referenceObject in referenceObjects) {
                if (referenceObject.properties.any { properties.contains(it) }) continue

                referenceObject.properties.add(Property(
                    name = propertyName,
                    description = "",
                    required = false,
                    typeInfo = ReferenceType("reference", "Input"),
                    alwaysNull = true,
                    needBeOverridden = true
                ))
            }
        }
    }
}

fun IntegerType.toLongType(): LongType = LongType(default?.toLong(), min?.toLong(), min?.toLong(), enumeration.map { it.toLong() })

//region model
data class Contract(
    val version: Version,
    @param:JsonProperty("recent_changes") val recentChanges: RecentChanges,
    val methods: MutableList<Method>,
    val objects: List<Object>,
    var methodsWithOverloadsByName: List<List<Method>> = listOf(),
)

data class Version(val major: Int, val minor: Int, val patch: Int)
data class RecentChanges(val year: Int, val month: Int, val day: Int)
data class Method(
    val name: String,
    val description: String,
    var arguments: MutableList<Argument>,
    @param:JsonProperty("maybe_multipart") val maybeMultipart: Boolean,
    @param:JsonProperty("return_type") var returnType: Type,
    @param:JsonProperty("documentation_link") val documentationLink: String,
    var postJsonClassNamePostfix: String? = null,
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = PropertiesObject::class, name = "properties"),
    JsonSubTypes.Type(value = AnyOfObject::class, name = "any_of"),
    JsonSubTypes.Type(value = UnknownObject::class, name = "unknown"),
)
abstract class Object {
    abstract val type: String
    abstract val name: String
    abstract val description: String?
    abstract val documentationLink: String
    var parentName: String? = null
}

data class Argument(
    val name: String,
    val description: String,
    var required: Boolean,
    @param:JsonProperty("type_info") var typeInfo: Type,
)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = IntegerType::class, name = "integer"),
    JsonSubTypes.Type(value = StringType::class, name = "string"),
    JsonSubTypes.Type(value = BooleanType::class, name = "bool"),
    JsonSubTypes.Type(value = FloatType::class, name = "float"),
    JsonSubTypes.Type(value = AnyOfType::class, name = "any_of"),
    JsonSubTypes.Type(value = ReferenceType::class, name = "reference"),
    JsonSubTypes.Type(value = ArrayType::class, name = "array"),
)
abstract class Type {
    abstract val type: String

    // for properties with constant values
    var constValue: String? = null
}

// custom type for internal replacing
data class LongType(
    val default: Long?,
    val min: Long?,
    val max: Long?,
    val enumeration: List<Long>,
) : Type() {
    override val type: String = "long"
}

@JsonTypeName("integer")
data class IntegerType(
    override val type: String,
    val default: Int?,
    val min: Int?,
    val max: Int?,
    val enumeration: List<Int>,
) : Type()

@JsonTypeName("string")
data class StringType(
    override val type: String,
    val default: String? = null,
    @param:JsonProperty("min_len") val minLen: Int? = null,
    @param:JsonProperty("max_len") val maxLen: Int? = null,
    val enumeration: List<String> = emptyList(),
) : Type()

@JsonTypeName("bool")
data class BooleanType(
    override val type: String,
    val default: Boolean? = null,
) : Type()

@JsonTypeName("float")
data class FloatType(
    override val type: String,
) : Type()

@JsonTypeName("any_of")
data class AnyOfType(
    override val type: String,
    @param:JsonProperty("any_of") val anyOf: MutableList<Type>,
) : Type()

@JsonTypeName("reference")
data class ReferenceType(
    override val type: String,
    val reference: String,
) : Type()

@JsonTypeName("array")
data class ArrayType(
    override val type: String,
    val array: Type,
) : Type()

@JsonTypeName("properties")
data class PropertiesObject(
    override val type: String,
    override val name: String,
    override val description: String? = null,
    val properties: MutableList<Property>,
    @param:JsonProperty("documentation_link") override val documentationLink: String,
    // will be store for creating @JsonTypeInfo
    var typePropertyName: String? = null,
    // will be store for creating @JsonSubTypes
    var typePropertyValue: String? = null,
) : Object()

@JsonTypeName("any_of")
data class AnyOfObject(
    override val type: String,
    override val name: String,
    override val description: String? = null,
    @param:JsonProperty("any_of") val anyOf: List<Type>,
    @param:JsonProperty("documentation_link") override val documentationLink: String,
    var properties: MutableList<Property>? = null,
) : Object()

@JsonTypeName("unknown")
data class UnknownObject(
    override val type: String,
    override val name: String,
    override val description: String? = null,
    @param:JsonProperty("documentation_link") override val documentationLink: String,
) : Object()

data class Property(
    val name: String,
    val description: String,
    val required: Boolean,
    @param:JsonProperty("type_info") val typeInfo: Type,
    var needBeOverridden: Boolean? = null,
    var alwaysNull: Boolean? = null,
)
//endregion model
