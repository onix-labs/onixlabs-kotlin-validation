package io.onixlabs.kotlin.validation

/**
 * Represents an assertion made against a member in the object graph.
 *
 * @param condition The condition which will be used to determine validity of the assertion.
 * @param type The type of the assertion.
 * @param message The message representing the assertion.
 */
class MemberAssertion internal constructor(
    val condition: ValidationCondition,
    val message: String,
    val type: MemberAssertionType = MemberAssertionType.RELATIVE
) {

    /**
     * Gets a formatted assertion message string.
     *
     * @param relativePrefix The relative prefix for an assertion message.
     * @return Returns a formatted assertion message string.
     */
    internal fun toString(relativePrefix: String): String {
        return if (type == MemberAssertionType.RELATIVE) "$relativePrefix $message" else message
    }
}