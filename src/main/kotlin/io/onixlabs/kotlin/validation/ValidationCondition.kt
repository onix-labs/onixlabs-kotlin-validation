package io.onixlabs.kotlin.validation

/**
 * Represents the base class for implementing validation conditions.
 *
 * @property id The identity of the condition. This should be used to identify the validation action taken.
 */
abstract class ValidationCondition {
    abstract val id: String

    /**
     * Determines whether the condition is valid.
     *
     * @return Returns true if the validation condition is valid; otherwise, false.
     */
    abstract fun isValid(): Boolean
}