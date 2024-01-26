package io.github.dehuckakpyt.telegrambot.model.type.supplement.content

import java.io.File


/**
 * Created on 26.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public object ContentFactory {
    public fun content(resource: String): Content = ResourceContent(resource)
    public fun content(file: File): Content = NamedFileContent(file)
    public fun content(byteArray: ByteArray): Content = ByteArrayContent(byteArray)
    public fun content(name: String, resource: String): NamedContent = NamedResourceContent(name, resource)
    public fun content(name: String, file: File): NamedContent = NamedFileContent(name, file)
    public fun content(name: String, byteArray: ByteArray): NamedContent = NamedByteArrayContent(name, byteArray)
}