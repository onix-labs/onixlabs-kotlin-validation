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
 * Asserts that the subject must be null or blank.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<String?>.mustBeNullOrBlank(
    message: String = "must be null or blank.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject.isNullOrBlank()
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be null or blank.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<String?>.mustNotBeNullOrBlank(
    message: String = "must not be null or blank.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return !subject.isNullOrBlank()
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be null or empty.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<String?>.mustBeNullOrEmpty(
    message: String = "must be null or empty.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject.isNullOrEmpty()
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be null or empty.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<String?>.mustNotBeNullOrEmpty(
    message: String = "must not be null or empty.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return !subject.isNullOrEmpty()
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be blank.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustBeBlank(
    message: String = "must be blank.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.isBlank() ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be blank.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustNotBeBlank(
    message: String = "must not be blank.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.isNotBlank() ?: true
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
fun MemberAssertionContext<out String?>.mustBeEmpty(
    message: String = "must be empty.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.isEmpty() ?: false
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
fun MemberAssertionContext<out String?>.mustNotBeEmpty(
    message: String = "must not be empty.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject?.isNotEmpty() ?: true
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must contain the specified value.
 *
 * @param value The value that the subject must contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustContain(
    value: String,
    message: String = "must contain the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject?.contains(this.value) ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not contain the specified value.
 *
 * @param value The value that the subject must not contain.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustNotContain(
    value: String,
    message: String = "must not contain the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return !(subject?.contains(this.value) ?: false)
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
fun MemberAssertionContext<out String?>.mustContainAll(
    values: Iterable<String>,
    message: String = "must contain all of the specified values: ${values.joinToString()}.",
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
fun MemberAssertionContext<out String?>.mustContainAny(
    values: Iterable<String>,
    message: String = "must contain any of the specified values: ${values.joinToString()}.",
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
 * Asserts that the subject must end with the specified value.
 *
 * @param value The value that the subject must end with.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustEndWith(
    value: String,
    message: String = "must end with the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject?.endsWith(this.value) ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not end with the specified value.
 *
 * @param value The value that the subject must not end with.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustNotEndWith(
    value: String,
    message: String = "must not end with the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return !(subject?.endsWith(this.value) ?: false)
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must start with the specified value.
 *
 * @param value The value that the subject must start with.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustStartWith(
    value: String,
    message: String = "must start with the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject?.startsWith(this.value) ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not start with the specified value.
 *
 * @param value The value that the subject must not start with.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustNotStartWith(
    value: String,
    message: String = "must not start with the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return !(subject?.startsWith(this.value) ?: false)
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must have the specified length.
 *
 * @param length The desired length of the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustHaveLength(
    length: Int,
    message: String = "must have the specified length: $length.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val length = length

        override fun isValid(): Boolean {
            return subject?.length == this.length
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be between the specified minimum and maximum length.
 *
 * @param range The desired minimum and maximum length of the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustHaveLength(
    range: IntRange,
    message: String = "must be between the specified minimum and maximum length: $range",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val range = range

        override fun isValid(): Boolean {
            return subject?.length in this.range
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must have the specified maximum length.
 *
 * @param maximumLength The desired maximum length of the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustHaveMaximumLength(
    maximumLength: Int,
    message: String = "must have the specified maximum length: $maximumLength.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val maximumLength = maximumLength

        override fun isValid(): Boolean {
            return subject != null && subject.length <= this.maximumLength
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must have the specified minimum length.
 *
 * @param minimumLength The desired minimum length of the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustHaveMinimumLength(
    minimumLength: Int,
    message: String = "must have the specified minimum length: $minimumLength.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val minimumLength = minimumLength

        override fun isValid(): Boolean {
            return subject != null && subject.length >= this.minimumLength
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be a valid Luhn (Mod10) checksum.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustBeValidLuhnChecksum(
    message: String = "must be a valid Luhn (Mod10) checksum.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        /**
         * Determines whether the specified string is a valid Luhn (Mod10) checksum.
         *
         * @return Returns true if the specified string is a valid Luhn (Mod10) checksum; otherwise, false.
         */
        fun String.isValidLuhnChecksum(): Boolean {
            fun isValidInput(value: String): Boolean {
                return value.all { it.isDigit() } && value.length > 1
            }

            fun computeChecksumDigit(value: String, index: Int, digit: Int): Int {
                return when {
                    (value.length - index + 1) % 2 == 0 -> digit
                    digit >= 5 -> digit * 2 - 9
                    else -> digit * 2
                }
            }

            fun isValidChecksum(value: String): Boolean {
                return value
                    .map(Character::getNumericValue)
                    .mapIndexed { index, digit -> computeChecksumDigit(value, index, digit) }
                    .sum() % 10 == 0
            }

            return isValidInput(this) && isValidChecksum(this)
        }

        override fun isValid(): Boolean {
            return subject?.isValidLuhnChecksum() ?: false
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must match the specified regular expression.
 *
 * @param regex The regular expression that the subject must match.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<out String?>.mustMatchRegex(
    regex: Regex,
    message: String = "must match the specified regular expression: ${regex.pattern}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val regex = regex

        override fun isValid(): Boolean {
            return subject?.matches(this.regex) ?: false
        }
    }

    validate(condition, message, type)
}
