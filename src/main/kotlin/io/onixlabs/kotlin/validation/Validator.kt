package io.onixlabs.kotlin.validation

import io.onixlabs.kotlin.core.reflection.kotlinClass
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmErasure

/**
 * Represents the base class for validator implementations
 *
 * @param T The underlying subject type of the validator.
 */
abstract class Validator<T> {

    companion object {
        private const val EX_ILLEGAL_VALIDATION_MODE =
            "Validation of a null subject can only occur when the validation mode is set to EMULATION."

        /**
         * Builds a validator on-the-fly for the specified type.
         *
         * @param T The underlying subject class type of the validator.
         * @param action The action that will be executed by the validation builder.
         * @return Returns a custom validator implementation.
         */
        inline fun <reified T : Any> validatorFor(
            crossinline action: ValidationBuilder<T>.() -> Unit
        ) = object : Validator<T>() {
            override val genericKotlinClass: KClass<*> get() = T::class
            override fun validate(builder: ValidationBuilder<T>) = action(builder)
        }
    }

    /**
     * Gets the underlying class type of the generic type parameter [T].
     * This allows the type to identified even for anonymous validation implementations through type reification.
     */
    internal open val genericKotlinClass: KClass<*>
        get() = kotlinClass.supertypes[0].arguments[0].type?.jvmErasure!!


    /**
     * Validates the specified subject using the specified validation mode.
     *
     * @param subject The subject to validate. Null subjects can only be validated in EMULATION mode.
     * @param mode The validation mode to use when validating the specified subject.
     * @return Returns an object graph validation result containing all failed validation assertions.
     */
    fun validate(subject: T? = null, mode: ValidationMode = ValidationMode.EMULATION): ValidationResult {
        if (subject == null && mode != ValidationMode.EMULATION) {
            throw IllegalArgumentException(EX_ILLEGAL_VALIDATION_MODE)
        }

        val result = ValidationResult(genericKotlinClass.simpleName!!)
        validate(
            ValidationBuilder(
                result,
                mode,
                genericKotlinClass,
                subject
            )
        )
        return result
    }

    /**
     * Validates the specified subject using the DEFAULT validation mode.
     *
     * @param subject The subject to validate.
     * @return Returns an object graph validation result containing all failed validation assertions.
     */
    fun validate(subject: T): ValidationResult = validate(
        subject,
        ValidationMode.DEFAULT
    )

    /**
     * Validates the specified subject using the specified validation mode.
     * This is called internally when validating nested objects in the object graph.
     *
     * @param subject The subject to validate. Null subjects can only be validated in EMULATION mode.
     * @param mode The validation mode to use when validating the specified subject.
     * @param parent The parent of the object to be validated.
     * @return Returns an object graph validation result containing all failed validation assertions.
     */
    internal fun validate(subject: T?, mode: ValidationMode, parent: ValidationResult): ValidationResult {
        if (subject == null && mode != ValidationMode.EMULATION) {
            throw IllegalArgumentException(EX_ILLEGAL_VALIDATION_MODE)
        }

        val result = parent.createMember(genericKotlinClass)
        validate(
            ValidationBuilder(
                result,
                mode,
                genericKotlinClass,
                subject
            )
        )
        return result
    }

    /**
     * Defines the validation assertions built by the validation builder.
     *
     * @param builder The validation builder which will be used to build assertions.
     */
    protected abstract fun validate(builder: ValidationBuilder<T>)
}