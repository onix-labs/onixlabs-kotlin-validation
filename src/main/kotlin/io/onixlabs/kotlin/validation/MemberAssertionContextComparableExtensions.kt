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
 * Asserts that the subject must be within the specified range.
 *
 * @param minimum The lower bound of the range.
 * @param maximum The upper bound of the range.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T : Comparable<T>> MemberAssertionContext<out T?>.mustBeWithinRange(
    minimum: T,
    maximum: T,
    message: String = "must be within the specified range: $minimum to $maximum.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val minimum = minimum
        val maximum = maximum

        override fun isValid(): Boolean {
            return subject != null && subject in this.minimum..this.maximum
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be within the specified range.
 *
 * @param minimum The lower bound of the range.
 * @param maximum The upper bound of the range.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T : Comparable<T>> MemberAssertionContext<out T?>.mustNotBeWithinRange(
    minimum: T,
    maximum: T,
    message: String = "must not be within the specified range: $minimum to $maximum.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val minimum = minimum
        val maximum = maximum

        override fun isValid(): Boolean {
            return subject != null && subject !in this.minimum..this.maximum
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be greater than the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T : Comparable<T>> MemberAssertionContext<out T?>.mustBeGreaterThan(
    value: T,
    message: String = "must be greater than the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject != null && subject > this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be greater than or equal to the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T : Comparable<T>> MemberAssertionContext<out T?>.mustBeGreaterThanOrEqualTo(
    value: T,
    message: String = "must be greater than or equal to the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject != null && subject >= this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be less than the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T : Comparable<T>> MemberAssertionContext<out T?>.mustBeLessThan(
    value: T,
    message: String = "must be less than the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject != null && subject < this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be less than or equal to the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T : Comparable<T>> MemberAssertionContext<out T?>.mustBeLessThanOrEqualTo(
    value: T,
    message: String = "must be less than or equal to the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject != null && subject <= this.value
        }
    }

    validate(condition, message, type)
}
