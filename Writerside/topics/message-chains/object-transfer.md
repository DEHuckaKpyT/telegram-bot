# Object transfer

Using the `next()` and `transfer()` methods, you can pass any object to the next step:
```kotlin
    step("get_name", next = "get_age") {
        sendMessage("Hello, $text!")
        transfer(text)
    }
```

And using the `transferred()` and `transferredOrNull()` methods, you can get this object in the next step:
```kotlin
    step("get_age") {
        val age = text.toIntOrNull() ?: throw ChatException("An integer is expected")
        val name = transferred<String>()
        // save person with name and age

        sendMessage("Your name is $name, and you are $age years old - recorded")
    }
```