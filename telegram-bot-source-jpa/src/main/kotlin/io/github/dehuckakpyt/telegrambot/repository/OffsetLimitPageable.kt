package io.github.dehuckakpyt.telegrambot.repository

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort


/**
 * Created on 03.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
class OffsetLimitPageable(private val offset: Long, limit: Int) : PageRequest(offset.toInt(), limit, Sort.unsorted()) {
    override fun getOffset(): Long = offset
}