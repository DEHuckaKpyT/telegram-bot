package io.github.dehuckakpyt.telegrambot.model.type.supplement.input

import java.io.File


/**
 * Created on 26.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface ContentFactory

public fun ContentFactory.content(resource: String): ContentInput = ResourceContent(resource)
public fun ContentFactory.content(file: File): ContentInput = NamedFileContent(file)
public fun ContentFactory.content(byteArray: ByteArray): ContentInput = ByteArrayContent(byteArray)
public fun ContentFactory.content(name: String, resource: String): NamedContentInput = NamedResourceContent(name, resource)
public fun ContentFactory.content(name: String, file: File): NamedContentInput = NamedFileContent(name, file)
public fun ContentFactory.content(name: String, byteArray: ByteArray): NamedContentInput = NamedByteArrayContent(name, byteArray)