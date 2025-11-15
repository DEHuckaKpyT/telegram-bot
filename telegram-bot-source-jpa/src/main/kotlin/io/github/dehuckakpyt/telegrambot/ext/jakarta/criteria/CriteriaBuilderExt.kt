package io.github.dehuckakpyt.telegrambot.ext.jakarta.criteria

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate


/**
 * @author Denis Matytsin
 */
/**
 * Create a predicate for testing whether the expression contains (ignore case) the given substring.
 *
 * @param expression  string expression
 * @param substring  substring
 *
 * @return containsIgnoreCase predicate
 */
public fun CriteriaBuilder.containsIgnoreCase(expression: Expression<String>, substring: String): Predicate =
    like(lower(expression), "%${substring.lowercase()}%")
