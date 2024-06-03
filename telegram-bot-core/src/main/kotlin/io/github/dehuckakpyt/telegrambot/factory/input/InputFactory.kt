package io.github.dehuckakpyt.telegrambot.factory.input

import io.github.dehuckakpyt.telegrambot.model.telegram.input.*
import java.io.File


/**
 * Created on 26.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface InputFactory

public class InputFactoryImpl : InputFactory

public fun InputFactory.input(resource: String): ContentInput = ResourceContent(resource)
public fun InputFactory.input(file: File): ContentInput = NamedFileContent(file)
public fun InputFactory.input(byteArray: ByteArray): ContentInput = ByteArrayContent(byteArray)
public fun InputFactory.input(name: String, resource: String): NamedContentInput = NamedResourceContent(name, resource)
public fun InputFactory.input(name: String, file: File): NamedContentInput = NamedFileContent(name, file)
public fun InputFactory.input(name: String, byteArray: ByteArray): NamedContentInput = NamedByteArrayContent(name, byteArray)