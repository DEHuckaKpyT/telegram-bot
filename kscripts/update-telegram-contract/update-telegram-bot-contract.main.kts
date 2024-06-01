@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
@file:DependsOn("io.ktor:ktor-client-core:2.3.10")
@file:DependsOn("io.ktor:ktor-client-apache5-jvm:2.3.10")
@file:DependsOn("io.ktor:ktor-client-content-negotiation-jvm:2.3.10")
@file:DependsOn("io.ktor:ktor-serialization-jackson-jvm:2.3.10")
@file:DependsOn("com.fasterxml.jackson.core:jackson-databind:2.16.0")
@file:DependsOn("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")
@file:DependsOn("com.squareup:kotlinpoet-jvm:1.16.0")

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.KModifier.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.ktor.client.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.plugins.contentnegotiation.*
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
val modulePath = Path("./../../telegram-bot-core/src/main/kotlin")
val objectsPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram"
val objectsInternalPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram.internal"
val telegramBotClassPackageName = "io.github.dehuckakpyt.telegrambot.temp"
val inputClassPackageName = "io.github.dehuckakpyt.telegrambot.model.type.supplement.input"
val todayFormattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))!!
val client = HttpClient(Apache5) {
    install(ContentNegotiation) {
        register(Json, JacksonConverter(jacksonObjectMapper()))
    }
}

runBlocking {
//    val contract = client.get("https://ark0f.github.io/tg-bot-api/custom_v2.json").body<Contract>()
    val contract = Path("./custom_v2.json").let { jacksonObjectMapper().readValue<Contract>(it.toFile()) }
    contract.replaceObjectTypes()
    contract.replaceMethodTypes()
    contract.methodsWithOverloadsByName = contract.splitMethodsToOverloads()

    println("Generating telegram bot api contracts...")
    println("Version: ${contract.version.major}.${contract.version.minor}.${contract.version.patch}")
    println("Recent changes: ${contract.recentChanges.year}-${contract.recentChanges.month}-${contract.recentChanges.day}")
    createObjects(contract.objects)
    createMethods(contract.methodsWithOverloadsByName)
}

suspend fun createMethods(methods: List<List<Method>>) {
    val file1 = FileSpec.builder(telegramBotClassPackageName, "TelegramBot")
        .indent("    ")
        .addType(
            TypeSpec.interfaceBuilder("TelegramBot")
                .addKdoc("Created on $todayFormattedDate.\n\n@author KScript")
                .addProperty("username", String::class)
                .addTelegramBotMethods(methods)
                .build()
        )
        .build()

    val file2 = FileSpec.builder(telegramBotClassPackageName, "TelegramBotImpl")
        .indent("    ")
        .addInternalImports(methods)
        .addImport("io.github.dehuckakpyt.telegrambot.ext", "appendContent", "appendContentIfNotNull", "appendIfNotNull")
        .addImport(inputClassPackageName, "StringInput")
        .addImport("io.github.dehuckakpyt.telegrambot.ext", "toJson")
        .addType(
            TypeSpec.classBuilder("TelegramBotImpl")
                .addKdoc("Created on $todayFormattedDate.\n\n@author KScript")
                .addSuperinterface(ClassName(telegramBotClassPackageName, "TelegramBot"))
                .primaryConstructor(FunSpec.constructorBuilder()
                    .addParameter("token", String::class)
                    .addParameter("username", String::class)
                    .addParameter("messageSource", ClassName("io.github.dehuckakpyt.telegrambot.source.message", "MessageSource"))
                    .build())
                .addProperty(PropertySpec.builder("token", String::class, PRIVATE)
                    .initializer("token")
                    .build())
                .addProperty(PropertySpec.builder("username", String::class, OVERRIDE)
                    .initializer("username")
                    .build())
                .addProperty(PropertySpec.builder("messageSource", ClassName("io.github.dehuckakpyt.telegrambot.source.message", "MessageSource"), PRIVATE)
                    .initializer("messageSource")
                    .build())
                .addProperty(PropertySpec.builder("client", ClassName("io.github.dehuckakpyt.telegrambot.client", "TelegramApiClient"), PRIVATE)
                    .initializer("TelegramApiClient(token)")
                    .build())
                .addTelegramBotImplMethods(methods)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file1.writeTo(modulePath)
        file2.writeTo(modulePath)
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
                .addModifiers(ABSTRACT, SUSPEND)
                .addParameters(mainMethod.arguments)
                .returns(mainMethod.returnType.toClassTypeName())
                .build()
        )
    }
}

suspend fun TypeSpec.Builder.addTelegramBotImplMethods(groupedMethods: List<List<Method>>) = apply {
    groupedMethods.forEach { methods ->
        val method = methods.first()

        addFunction(
            FunSpec.builder(method.name)
                .addModifiers(OVERRIDE, SUSPEND)
                .addParameters(method.arguments, specifyNull = false)
                .returns(method.returnType.toClassTypeName())
                .addGetMethodIfNecessary(method)
                .addPostMethodJsonIfNecessary(method)
                .addPostMethodMultipartIfNecessary(method)
                .build()
        )
    }
}

fun FunSpec.Builder.addGetMethodIfNecessary(method: Method): FunSpec.Builder {
    if (method.arguments.size != 0) return this

    addStatement("return client.get(\"${method.name}\")")

    return this
}

suspend fun FunSpec.Builder.addPostMethodJsonIfNecessary(method: Method): FunSpec.Builder {
    if (method.arguments.size == 0 || method.maybeMultipart) return this

    val obj = method.toPropertiesObject()
    createInternalPropertiesObject(obj)

    addStatement("return client.postJson(\"${method.name}\", ${obj.name}(" + obj.properties.joinToString { "\n        ${it.nameCamelCase} = ${it.nameCamelCase}" } + "\n    )\n)")

    return this
}

fun FunSpec.Builder.addPostMethodMultipartIfNecessary(method: Method): FunSpec.Builder {
    if (method.arguments.size == 0 || method.maybeMultipart.not()) return this

    val statement = StringBuilder("return client.postMultiPart(\"${method.name}\") {").also { builder ->
        method.arguments.forEach { arg -> builder.append("\n    ").append(arg.toAppendStatement()) }
    }.also { builder ->
        builder.append("\n}")
    }.toString()

    addStatement(statement)

    return this
}

val Boolean.appendMethodName get(): String = if (this) "append" else "appendIfNotNull"
val Boolean.appendContentMethodName get(): String = if (this) "appendContent" else "appendContentIfNotNull"

fun Argument.toAppendStatement(): String = when {
    (typeInfo.type in setOf("integer", "string", "bool", "float", "long")) -> {
        "${required.appendMethodName}(\"$name\", $nameCamelCase)"
    }

    (typeInfo is ReferenceType && (typeInfo as ReferenceType).reference in setOf("InputFile", "Input", "ContentInput")) -> {
        when ((typeInfo as ReferenceType).reference) {
            "InputFile", "Input" -> "if ($nameCamelCase is ContentInput)\n        ${required.appendContentMethodName}(\"$name\", $nameCamelCase)\n    else\n        ${required.appendMethodName}(\"$name\", ($nameCamelCase as StringInput).fileId)"
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
                .addKdoc("Created on $todayFormattedDate.\n\n@author KScript")
                .addModifiers(DATA, INTERNAL)
                .primaryConstructor(constructor)
                .addProperties(properties)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(modulePath)
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

fun FunSpec.Builder.addParameters(arguments: List<Argument>, specifyNull: Boolean = true) = apply {
    arguments.forEach { argument ->
        addParameter(argument.toParameter(specifyNull))
    }
}

fun Argument.toParameter(specifyNull: Boolean = true): ParameterSpec = ParameterSpec.builder(nameCamelCase, typeInfo.toMethodTypeName().copy(nullable = required.not()))
    .also { if (specifyNull && required.not()) it.defaultValue("null") }
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
                    .build()
            )
            .build()

        withContext(Dispatchers.IO) {
            file.writeTo(modulePath)
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
                .addAnnotation(jsonTypeInfo)
                .addAnnotation(jsonSubTypes)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(modulePath)
    }
}

suspend fun createMultiTypeIntLongAndStringPropertiesObject(obj: PropertiesObject) {
    val primaryConstructorBuilder = FunSpec.constructorBuilder()
    val secondaryConstructorBuilder = FunSpec.constructorBuilder()
    val properties = mutableListOf<PropertySpec>()
    val callThisConstructorArgs = mutableListOf<String>()

    obj.properties.forEach { property ->
        if (property.typeInfo !is AnyOfType) {
            if (property.typeInfo.constValue == null) {
                primaryConstructorBuilder.addParameter(property.toParameterSpec())
                secondaryConstructorBuilder.addParameter(property.toParameterSpec())
                callThisConstructorArgs.add(property.nameCamelCase)
            }
            properties.add(property.toPropertySpec())
        } else {
            primaryConstructorBuilder.addParameter(property.toParameterSpec(String::class.asClassName()))
            secondaryConstructorBuilder.addParameter(property.toParameterSpec(property.typeInfo.anyOf.first { it.type != "string" }.toClassTypeName()))
            properties.add(property.toPropertySpec(String::class.asClassName()))
            callThisConstructorArgs.add(property.nameCamelCase + ".toString()")
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
        file.writeTo(modulePath)
    }
}

suspend fun createMultiTypeInputFileAndStringPropertiesObject(obj: PropertiesObject) {
    val primaryConstructorBuilder = FunSpec.constructorBuilder()
    val secondaryConstructorBuilder = FunSpec.constructorBuilder()
    val properties = mutableListOf<PropertySpec>()
    val callThisConstructorArgs = mutableListOf<String>()

    obj.properties.forEach { property ->
        if (property.typeInfo !is AnyOfType) {
            if (property.typeInfo.constValue == null) {
                primaryConstructorBuilder.addParameter(property.toParameterSpec())
                secondaryConstructorBuilder.addParameter(property.toParameterSpec())
                callThisConstructorArgs.add(property.nameCamelCase)
            }
            properties.add(property.toPropertySpec())
        } else {
            primaryConstructorBuilder.addParameter(property.toParameterSpec(ClassName(inputClassPackageName, "Input")))
            secondaryConstructorBuilder.addParameter(property.toParameterSpec(String::class.asClassName()))
            properties.add(property.toPropertySpec(ClassName(inputClassPackageName, "Input")))
            callThisConstructorArgs.add("StringInput(${property.nameCamelCase})")
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
        file.writeTo(modulePath)
    }
}

suspend fun createSimplePropertiesObject(obj: PropertiesObject) {
    val parameters = obj.properties.filter { it.typeInfo.constValue == null }.map { it.toParameterSpec() }
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
        file.writeTo(modulePath)
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
        file.writeTo(modulePath)
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

fun Property.toPropertySpec(type: TypeName? = null, name: String = nameCamelCase, nullable: Boolean = required.not()): PropertySpec = PropertySpec.builder(
    name = name,
    type = (type ?: typeInfo.toClassTypeName()).copy(nullable = nullable),
).initializer(
    if (typeInfo.constValue != null) "\"${typeInfo.constValue}\"" else name
).addAnnotation(
    AnnotationSpec.builder(JsonProperty::class)
        .addMember("\"${this.name}\"")
        .useSiteTarget(AnnotationSpec.UseSiteTarget.GET)
        .build()
).also {
    if (this.typeInfo.constValue == null) {
        it.addAnnotation(
            AnnotationSpec.builder(JsonProperty::class)
                .addMember("\"${this.name}\"")
                .useSiteTarget(AnnotationSpec.UseSiteTarget.PARAM)
                .build()
        )
    }
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
            .add("Created on $todayFormattedDate.")
            .also { if (obj.description != null) it.add("\n\n%L", obj.description!!) }
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
            .add("Created on $todayFormattedDate.")
            .also { if (obj.description != null) it.add("\n\n%L", obj.description!!) }
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
    is AnyOfType -> throw RuntimeException("Unexpected type $this")
    is ReferenceType -> when (reference) {
        "InputFile", "Input" -> ClassName(inputClassPackageName, "Input")
        "ContentInput" -> ClassName(inputClassPackageName, "ContentInput")
        else -> ClassName(objectsPackageName, reference)
    }

    is ArrayType -> List::class.asClassName().parameterizedBy(array.toClassTypeName())
    else -> throw RuntimeException("Unexpected type $this")
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
                            .addKdoc("Created on $todayFormattedDate.\n\n@author KScript")
                            .build())
                        .build()

                    withContext(Dispatchers.IO) {
                        file.writeTo(modulePath)
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
)
//endregion model
