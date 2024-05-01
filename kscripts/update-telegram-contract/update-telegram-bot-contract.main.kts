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


val objectsPath = Path("./../../telegram-bot-core/src/main/kotlin")
val objectsPackageName = "io.github.dehuckakpyt.telegrambot.model.telegram"
val contentClassPackageName = "io.github.dehuckakpyt.telegrambot.model.type.supplement.content"
val todayFormattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))!!
val classDocumentation = "Created on $todayFormattedDate.\n\n%s\n\n@author KScript"
//val ignoreClassNames = listOf("InputFile")


val client = HttpClient(Apache5) {
    install(ContentNegotiation) {
        register(Json, JacksonConverter(jacksonObjectMapper()))
    }
}

runBlocking {
//    val contract = client.get("https://ark0f.github.io/tg-bot-api/custom_v2.json").body<Contract>()
    val contract = Path("./custom_v2.json").let { jacksonObjectMapper().readValue<Contract>(it.toFile()) }
    contract.replaceTypes()

//    val flux = FunSpec.constructorBuilder()
//        .addParameter("asd", Int::class)
//        .addParameter("zxc", String::class)
//        .callThisConstructor("asd.toString()")
//        .callThisConstructor("zxc")
//        .build()
//    val constructor = FunSpec.constructorBuilder().apply {
//        addParameter("greeting", String::class)
//        addParameter("hello", String::class)
//    }.build()
//
//    val file = FileSpec.builder(objectsPackageName, "HelloWorld")
//        .addType(TypeSpec.classBuilder("HelloWorld")
//            .primaryConstructor(constructor)
//            .addProperty("greeting", String::class, KModifier.PRIVATE)
//            .addProperty("hello", String::class, KModifier.PRIVATE)
//            .addFunction(flux)
//            .build())
//        .build()
//
//    withContext(Dispatchers.IO) {
//        file.writeTo(objectsPath)
//    }
    println("Generating telegram bot api contracts...")
    println("Version: ${contract.version.major}.${contract.version.minor}.${contract.version.patch}")
    println("Recent changes: ${contract.recentChanges.year}-${contract.recentChanges.month}-${contract.recentChanges.day}")

    createObjects(contract.objects)
}

suspend fun createObjects(objects: List<Object>) = objects.forEach { currentObject ->
    if (currentObject.name == "InputFile") return@forEach

    if (currentObject is PropertiesObject) createPropertiesObject(currentObject)
    if (currentObject is UnknownObject) createUnknownObject(currentObject)
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
    } else println("WARN $obj")
}

suspend fun createMultiTypeIntLongAndStringPropertiesObject(obj: PropertiesObject) {
    val primaryConstructorBuilder = FunSpec.constructorBuilder()
    val secondaryConstructorBuilder = FunSpec.constructorBuilder()
    val properties = mutableListOf<PropertySpec>()
    val callThisConstructorArgs = mutableListOf<String>()

    obj.properties.forEach { property ->
        if (property.typeInfo !is AnyOfType) {
            primaryConstructorBuilder.addParameter(property.toParameterSpec())
            secondaryConstructorBuilder.addParameter(property.toParameterSpec())
            properties.add(property.toPropertySpec())
            callThisConstructorArgs.add(property.nameCamelCase)
        } else {
            primaryConstructorBuilder.addParameter(property.toParameterSpec(String::class.asClassName()))
            secondaryConstructorBuilder.addParameter(property.toParameterSpec(property.typeInfo.anyOf.first { it.type != "string" }.toTypeName()))
            properties.add(property.toPropertySpec(String::class.asClassName()))
            callThisConstructorArgs.add(property.nameCamelCase + ".toString()")
        }
    }

    val file = FileSpec.builder(objectsPackageName, obj.name)
        .addType(
            TypeSpec.classBuilder(obj.name)
                .addKdoc(classDocumentation.format("@see [${obj.name}](${obj.documentationLink})"))
                .primaryConstructor(primaryConstructorBuilder.build())
                .addFunction(secondaryConstructorBuilder.callThisConstructor(*callThisConstructorArgs.toTypedArray()).build())
                .addProperties(properties)
                .build()
        )
        .build()


    withContext(Dispatchers.IO) {
        file.writeTo(objectsPath)
    }
}

suspend fun createSimplePropertiesObject(obj: PropertiesObject) {
    val parameters = obj.properties.map { it.toParameterSpec() }
    val properties = obj.properties.map { it.toPropertySpec() }
    val constructor = FunSpec.constructorBuilder().apply {
        addParameters(parameters)
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

fun Property.toParameterSpec(type: TypeName? = null): ParameterSpec = ParameterSpec.builder(
    name = nameCamelCase,
    type = (type ?: typeInfo.toTypeName()).copy(nullable = required.not())
).build()

fun Property.toPropertySpec(type: TypeName? = null): PropertySpec = PropertySpec.builder(
    name = nameCamelCase,
    type = (type ?: typeInfo.toTypeName()).copy(nullable = required.not())
).initializer(nameCamelCase).build()

fun Type.toTypeName(): TypeName = when (this) {
    is IntegerType -> Int::class.asClassName()
    is LongType -> Long::class.asClassName()
    is StringType -> String::class.asClassName()
    is BooleanType -> Boolean::class.asClassName()
    is FloatType -> Double::class.asClassName()
    is AnyOfType -> throw RuntimeException("Unexpected type $this")
    is ReferenceType -> if (reference == "InputFile") ClassName(contentClassPackageName, "Content") else ClassName(objectsPackageName, reference)
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

val Type.isMultiPropertyIntLongAndString: Boolean
    get() {
        if (this !is AnyOfType) return false
        if (anyOf.size != 2) return false
        if (!anyOf.any { it.type == "string" }) return false
        if (!anyOf.any { it.type == "integer" || it.type == "long" }) return false
        return true
    }

val Property.nameCamelCase get() = name.toCamelCase()

fun String.toCamelCase(): String = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, this)


fun Contract.replaceTypes() {
    objects.forEach { obj ->
        if (obj !is PropertiesObject) return@forEach

        val properties = obj.properties
        for ((i, property) in properties.withIndex()) {
            var _property = property
            // thumbnail's you can upload only
            if (_property.name == "thumbnail" && _property.description.contains("Thumbnails can't be reused and can be only uploaded as a new file")) {
                properties[i] = _property.copy(typeInfo = ReferenceType("reference", "InputFile"))
                _property = properties[i]
            }
            if (_property.name == "id" || _property.name.endsWith("_id") || _property.description.contains("may have more than 32 significant bits")) {
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
            if (_property.name.endsWith("_ids") || _property.description.contains("may have more than 32 significant bits")) {
                if (_property.typeInfo is ArrayType && _property.typeInfo.type == "integer") {
                    properties[i] = _property.copy(typeInfo = ArrayType(_property.typeInfo.type, ((_property.typeInfo as ArrayType).array as IntegerType).toLongType()))
                    _property = properties[i]
                }
            }
//            if (_property.typeInfo is ReferenceType && (_property.typeInfo as ReferenceType).reference == "InputFile") {
//                properties[i] = _property.copy(typeInfo = ReferenceType("reference", "Content"))
//                _property = properties[i]
//            }
        }
    }
}

fun IntegerType.toLongType(): LongType = LongType(default?.toLong(), min?.toLong(), min?.toLong(), enumeration.map { it.toLong() })


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
    override val description: String,
    val properties: MutableList<Property>,
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
)

//endregion model
