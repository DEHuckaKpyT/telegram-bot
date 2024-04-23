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
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache5.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.jackson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.kotlin.com.google.common.base.CaseFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.io.path.Path


val objectsPath = Path("./../../telegram-bot-core/src/main/kotlin")
val objectsPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram"
val todayFormattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))!!
val classDocumentation = "Created on $todayFormattedDate.\n\n%s\n\n@author KScript"
//val ignoreClassNames = listOf("InputFile")


val client = HttpClient(Apache5) {
    install(ContentNegotiation) {
        register(Json, JacksonConverter(jacksonObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)))
    }
}

runBlocking {
    val contract = client.get("https://ark0f.github.io/tg-bot-api/custom_v2.json").body<Contract>()
    println("Generating telegram bot api contracts...")
    println("Version: ${contract.version.major}.${contract.version.minor}.${contract.version.patch}")
    println("Recent changes: ${contract.recentChanges.year}-${contract.recentChanges.month}-${contract.recentChanges.day}")

    createObjects(contract.objects)
}

suspend fun createObjects(objects: List<Object>) = objects.forEach { currentObject ->
//    if (ignoreClassNames.contains(currentObject.name)) return@forEach

    if (currentObject is PropertiesObject) createPropertiesObject(currentObject)
    if (currentObject is UnknownObject) createUnknownObject(currentObject)
}

suspend fun createPropertiesObject(obj: PropertiesObject) {
    if (obj.properties.any { it.typeInfo is AnyOfType }) return
    val parameters = obj.properties.map { it.toParameter() }
    val properties = obj.properties.map { it.toProperty() }
    val constructor = FunSpec.constructorBuilder().apply {
        addParameters(parameters)
//        addModifiers(INTERNAL)
    }.build()

    val file = FileSpec.builder(objectsPackageName, obj.name)
        .addType(
            TypeSpec.classBuilder(obj.name)
                .addKdoc(classDocumentation.format("@see [${obj.name}](${obj.documentationLink})"))
                .primaryConstructor(constructor)
                .addProperties(properties)
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(objectsPath)
    }
}

fun Property.toParameter(): ParameterSpec = ParameterSpec.builder(
    name = nameCamelCase,
    type = typeInfo.toTypeName().copy(nullable = required.not())
).build()

fun Property.toProperty(): PropertySpec = PropertySpec.builder(
    name = nameCamelCase,
    type = typeInfo.toTypeName().copy(nullable = required.not())
).initializer(name.toCamelCase()).build()

fun Type.toTypeName(): TypeName = when (this) {
    is IntegerType -> ClassName("", "Int")
    is StringType -> ClassName("", "String")
    is BooleanType -> ClassName("", "Boolean")
    is FloatType -> ClassName("", "Double")
    is AnyOfType -> throw RuntimeException("Unexpected type $this")
    is ReferenceType -> ClassName(objectsPackageName, reference)
    is ArrayType -> List::class.asClassName().parameterizedBy(array.toTypeName())
    else -> throw RuntimeException("Unexpected type $this")
}


suspend fun createUnknownObject(obj: UnknownObject) {
    val file = FileSpec.builder(objectsPackageName, obj.name)
        .addType(
            TypeSpec.classBuilder(obj.name)
                .addKdoc(classDocumentation.format("@see [${obj.name}](${obj.documentationLink})"))
                .build()
        )
        .build()

    withContext(Dispatchers.IO) {
        file.writeTo(objectsPath)
    }
}

fun String.toCamelCase(): String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this);

//region model
data class Contract(
    val version: Version,
    @param:JsonProperty("recent_changes") val recentChanges: RecentChanges,
    val methods: List<Method>,
    val objects: List<Object>,
)

data class Version(val major: Int, val minor: Int, val patch: Int)
data class RecentChanges(val year: Int, val month: Int, val day: Int)
data class Method(
    val name: String,
    val description: String,
    val arguments: List<Argument>,
    @param:JsonProperty("maybe_multipart") val maybeMultipart: Boolean,
    @param:JsonProperty("return_type") val returnType: Type,
    @param:JsonProperty("documentation_link") val documentationLink: String,
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
    abstract val description: String
}

data class Argument(
    val name: String,
    val description: String,
    val required: Boolean,
    @param:JsonProperty("type_info") val typeInfo: Type,
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
    val default: String?,
    @param:JsonProperty("min_len") val minLen: Int?,
    @param:JsonProperty("max_len") val maxLen: Int?,
    val enumeration: List<String>,
) : Type()

@JsonTypeName("bool")
data class BooleanType(
    override val type: String,
    val default: Boolean?,
) : Type()

@JsonTypeName("float")
data class FloatType(
    override val type: String,
) : Type()

@JsonTypeName("any_of")
data class AnyOfType(
    override val type: String,
    @param:JsonProperty("any_of") val anyOf: List<Type>,
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
    override val description: String,
    val properties: List<Property>,
    @param:JsonProperty("documentation_link") val documentationLink: String,
) : Object()

@JsonTypeName("any_of")
data class AnyOfObject(
    override val type: String,
    override val name: String,
    override val description: String,
    @param:JsonProperty("any_of") val anyOf: List<Type>,
    @param:JsonProperty("documentation_link") val documentationLink: String,
) : Object()

@JsonTypeName("unknown")
data class UnknownObject(
    override val type: String,
    override val name: String,
    override val description: String,
    @param:JsonProperty("documentation_link") val documentationLink: String,
) : Object()

data class Property(
    val name: String,
    val description: String,
    val required: Boolean,
    @param:JsonProperty("type_info") val typeInfo: Type,
) {
    val nameCamelCase = name.toCamelCase()
}
//endregion model
