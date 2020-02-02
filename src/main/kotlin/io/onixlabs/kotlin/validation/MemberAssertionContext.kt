package io.onixlabs.kotlin.validation

/**
 * Represents an assertion context over a member in the object graph.
 *
 * @param T The underlying type of the subject.
 * @param result The result object in which assertions against the member will be collected.
 * @param subject The subject to be validated.
 */
class MemberAssertionContext<T> internal constructor(
    private val result: MemberResult,
    private val mode: ValidationMode,
    val subject: T
) {

    /**
     * Performs an assertion and stores the resulting assertion details if the assertion condition is invalid.
     *
     * @param condition The assertion condition which will be used to check subject validity.
     * @param message The assertion message detailing why the assertion failed.
     * @param type The type of the assertion, which is either absolute, or relative to its position in the object graph.
     * @throws ValidationException if the assertion condition fails and the validation mode is STOP_ON_ERROR.
     */
    fun validate(condition: ValidationCondition, message: String, type: MemberAssertionType) {
        val assertion = MemberAssertion(condition, message, type)
        if (mode == ValidationMode.EMULATION || !assertion.condition.isValid()) {
            if (mode == ValidationMode.STOP_ON_ERROR) {
                throw ValidationException(assertion.toString(result.createRelativeAssertionPrefix()))
            } else {
                result.addAssertion(assertion)
            }
        }
    }

    /**
     * Provides a mechanism for child objects to be validated as part of the parent object graph.
     *
     * @param validator The validator to validate the child object.
     */
    fun validateWith(validator: Validator<T>) {
        val result = validator.validate(subject, mode, result)
        if (result.toList().isNotEmpty()) {
            this.result.addMember(result)
        }
    }
}