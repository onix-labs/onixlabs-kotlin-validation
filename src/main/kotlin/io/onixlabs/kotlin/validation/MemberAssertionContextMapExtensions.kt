/**
 * Copyright 2020 Matthew Layton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
