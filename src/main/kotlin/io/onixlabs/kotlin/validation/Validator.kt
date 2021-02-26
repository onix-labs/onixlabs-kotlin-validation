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

import io.onixlabs.kotlin.core.reflection.kotlinClass
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmErasure

/**
 * Represents the base class for validator implementations.
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
        inline fun <reified T : Any> validatorFor(crossinline action: ValidationBuilder<T>.() -> Unit): Validator<T> {
            return object : Validator<T>() {
                override val genericKotlinClass: KClass<*> get() = T::class
                override fun validate(builder: ValidationBuilder<T>) = action(builder)
            }
        }

        /**
         * Builds and validates a subject on-the-fly.
         *
         * @param T The underlying subject class of the validator.
         * @param subject The subject to be validated.
         * @param action he action that will be executed by the validation builder.
         * @throws ValidationGraphException if the validator fails.
         */
        inline fun <reified T : Any> validate(subject: T, crossinline action: ValidationBuilder<T>.() -> Unit) {
            val validator = validatorFor(action)
            val result = validator.validate(subject)
            if (result.members.isNotEmpty()) {
                val message = "Validation of the specified object type failed: ${result.name}."
                throw ValidationGraphException(message, result.toList())
            }
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
