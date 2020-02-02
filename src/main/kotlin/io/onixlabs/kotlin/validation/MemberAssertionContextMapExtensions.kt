package io.onixlabs.kotlin.validation

import io.onixlabs.kotlin.core.toUpperSnakeCase

/**
 * Asserts that the subject must contain the specified key.
 *
 * @param key The key in the map.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <K, V, T : Map<K, V>> MemberAssertionContext<out T?>.mustContainKey(
    key: K,
    message: String = "must contain the specified key: $key.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val key = key

        override fun isValid(): Boolean {
            return subject?.containsKey(this.key) ?: false
        }
    }

    validate(condition, message, type)
}


/**
 * Asserts that the subject must not contain the specified key.
 *
 * @param key The key in the map.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <K, V, T : Map<K, V>> MemberAssertionContext<out T?>.mustNotContainKey(
    key: K,
    message: String = "must not contain the specified key: $key.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val key = key

        override fun isValid(): Boolean {
            return !(subject?.containsKey(this.key) ?: true)
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain the specified value.
 *
 * @param value The value in the map.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <K, V, T : Map<K, V>> MemberAssertionContext<out T?>.mustContainValue(
    value: V,
    message: String = "must contain the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject?.containsValue(this.value) ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not contain the specified value.
 *
 * @param value The value in the map.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <K, V, T : Map<K, V>> MemberAssertionContext<out T?>.mustNotContainValue(
    value: V,
    message: String = "must not contain the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return !(subject?.containsValue(this.value) ?: true)
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain the specified entry.
 *
 * @param key The key of the entry in the map.
 * @param value The value of the entry in the map.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <K, V, T : Map<K, V>> MemberAssertionContext<out T?>.mustContainEntry(
    key: K,
    value: V,
    message: String = "must contain the specified entry: $key to $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val key = key
        val value = value

        override fun isValid(): Boolean {
            return subject?.any { it.key == this.key && it.value == this.value } ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not contain the specified entry.
 *
 * @param key The key of the entry in the map.
 * @param value The value of the entry in the map.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <K, V, T : Map<K, V>> MemberAssertionContext<out T?>.mustNotContainEntry(
    key: K,
    value: V,
    message: String = "must not contain the specified entry: $key to $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val key = key
        val value = value

        override fun isValid(): Boolean {
            return subject?.none { it.key == this.key && it.value == this.value } ?: false
        }
    }

    validate(condition, message, type)
}