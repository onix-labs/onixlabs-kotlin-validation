package io.onixlabs.kotlin.validation

import io.onixlabs.kotlin.core.toUpperSnakeCase

/**
 * Asserts that the subject must be true.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<Boolean?>.mustBeTrue(
    message: String = "must be true.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject == true
        }
    }

    validate(condition, message, type)
}

/**
 * Asserts that the subject must be false.
 *
 * @param message The assertion message detailing why the assertion failed.
 * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
 */
fun MemberAssertionContext<Boolean?>.mustBeFalse(
    message: String = "must be false.",
    type: MemberAssertionType = MemberAssertionType.RELATIVE
) {
    val id = object : Any() {}.javaClass.enclosingMethod.name
    val condition = object : ValidationCondition() {
        override val id: String = id.toUpperSnakeCase()

        override fun isValid(): Boolean {
            return subject == false
        }
    }

    validate(condition, message, type)
}