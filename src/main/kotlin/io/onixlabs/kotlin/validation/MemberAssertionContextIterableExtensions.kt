package io.onixlabs.kotlin.validation

import io.onixlabs.kotlin.core.ONE
import io.onixlabs.kotlin.core.collections.orderedContentEquals
import io.onixlabs.kotlin.core.collections.unorderedContentEquals
import io.onixlabs.kotlin.core.toUpperSnakeCase

/**
 * Asserts that the subject must contain the specified value.
 *
 * @param value The value that the subject must contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustContain(
    value: T,
    message: String = "must contain the specified element value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject != null && this.value in subject
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain all of the specified values.
 *
 * @param values The values that the subject must contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustContainAll(
    values: Iterable<T>,
    message: String = "must contain all of the specified element values: ${values.joinToString()}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val values = values

        override fun isValid(): Boolean {
            return this.values.all { subject?.contains(it) ?: false }
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain any of the specified values.
 *
 * @param values The values that the subject may contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustContainAny(
    values: Iterable<T>,
    message: String = "must contain any of the specified element values: ${values.joinToString()}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val values = values

        override fun isValid(): Boolean {
            return this.values.any { subject?.contains(it) ?: false }
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain all of the specified values exactly.
 *
 * @param values The values that the subject must contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustContainExactly(
    values: Iterable<T>,
    message: String = "must contain exactly the specified element values: ${values.joinToString()}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val values = values

        override fun isValid(): Boolean {
            return subject?.unorderedContentEquals(this.values) ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain all of the specified values exactly, and in the same order.
 *
 * @param values The values that the subject must contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustContainOrderedExactly(
    values: Iterable<T>,
    message: String = "must contain exactly the specified element values in order: ${values.joinToString()}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val values = values

        override fun isValid(): Boolean {
            return subject?.orderedContentEquals(this.values) ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must have the specified element count.
 *
 * @param count The count of elements that the subject must contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustHaveCount(
    count: Int,
    message: String = "must contain exactly the specified number of elements: $count.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val count = count

        override fun isValid(): Boolean {
            return subject?.count() == this.count
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be empty.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustBeEmpty(
    message: String = "must be empty.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.toList()?.isEmpty() ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be empty.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustNotBeEmpty(
    message: String = "must not be empty.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.toList()?.isNotEmpty() ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain a single element.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustBeSingle(
    message: String = "must contain a single element.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.count() == Int.ONE
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain distinct elements.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<out Iterable<T>?>.mustBeDistinct(
    message: String = "must contain distinct elements.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.count() == subject?.distinct()?.count() ?: false
        }
    }

    validate(condition, message, type)
}