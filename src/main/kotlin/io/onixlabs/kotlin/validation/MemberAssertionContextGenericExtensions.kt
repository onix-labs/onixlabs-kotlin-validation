package io.onixlabs.kotlin.validation

import io.onixlabs.kotlin.core.toUpperSnakeCase

/**
 * Asserts that the subject must be null.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T?>.mustBeNull(
    message: String = "must be null.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject == null
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be null.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T?>.mustNotBeNull(
    message: String = "must not be null.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject != null
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be equal to the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustBeEqualTo(
    value: T,
    message: String = "must be equal to the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject == this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be equal to the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustNotBeEqualTo(
    value: T,
    message: String = "must not be equal to the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject != this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be equal by reference to the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustBeEqualByReferenceTo(
    value: T,
    message: String = "must be equal by reference to the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject === this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be equal by reference to the specified value.
 *
 * @param value The value to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustNotBeEqualByReferenceTo(
    value: T,
    message: String = "must not be equal by reference to the specified value: $value.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val value = value

        override fun isValid(): Boolean {
            return subject !== this.value
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be equal to any of the specified values.
 *
 * @param values The values to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustBeEqualToAny(
    values: Iterable<T>,
    message: String = "must be equal to any of the specified values: ${values.joinToString()}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val values = values

        override fun isValid(): Boolean {
            return subject in this.values
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not be equal to any of the specified values.
 *
 * @param values The values to compare with the subject.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustNotBeEqualToAny(
    values: Iterable<T>,
    message: String = "must not be equal to any of the specified values: ${values.joinToString()}.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()
        val values = values

        override fun isValid(): Boolean {
            return subject !in this.values
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must satisfy the specified predicate.
 *
 * @param predicate The predicate that the subject must satisfy.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustSatisfy(
    predicate: (T) -> Boolean,
    message: String = "must satisfy the specified predicate.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return predicate(subject)
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must not satisfy the specified predicate.
 *
 * @param predicate The predicate that the subject must not satisfy.
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun <T> MemberAssertionContext<T>.mustNotSatisfy(
    predicate: (T) -> Boolean,
    message: String = "must not satisfy the specified predicate.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return !predicate(subject)
        }
    }

    validate(condition, message, type)
}