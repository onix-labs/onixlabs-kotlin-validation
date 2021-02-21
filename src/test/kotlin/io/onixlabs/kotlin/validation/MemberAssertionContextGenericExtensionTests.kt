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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*


class MemberAssertionContextGenericExtensionTests {

    private val member =
        "Property 'GenericExtensionTestSubject.property' of type 'kotlin.Any?'"

    @Test
    fun `MemberAssertionContext_mustBeNull should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(Unit)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustBeNull()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be null.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeNull should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(null)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustNotBeNull()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be null.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeEqualTo should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(null)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustBeEqualTo(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be equal to the specified value: 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeEqualTo should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(123)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustNotBeEqualTo(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be equal to the specified value: 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeEqualByReferenceTo should assert correctly on validation failure`() {

        // Arrange
        val reference = UUID.fromString("00000000-0000-0000-0000-000000000000")
        val subject = GenericExtensionTestSubject(reference)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustBeEqualByReferenceTo(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals(
            "$member must be equal by reference to the specified value: 00000000-0000-0000-0000-000000000000.",
            result.message
        )
    }

    @Test
    fun `MemberAssertionContext_mustNotBeEqualByReferenceTo should assert correctly on validation failure`() {

        // Arrange
        val reference = UUID.fromString("00000000-0000-0000-0000-000000000000")
        val subject = GenericExtensionTestSubject(reference)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustNotBeEqualByReferenceTo(reference)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals(
            "$member must not be equal by reference to the specified value: 00000000-0000-0000-0000-000000000000.",
            result.message
        )
    }

    @Test
    fun `MemberAssertionContext_mustBeEqualToAny should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(123)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustBeEqualToAny(listOf(456, 789))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be equal to any of the specified values: 456, 789.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeEqualToAny should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(123)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustNotBeEqualToAny(listOf(123, 456))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be equal to any of the specified values: 123, 456.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustSatisfy should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(123)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustSatisfy({ value -> value as Int > 200 })
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must satisfy the specified predicate.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotSatisfy should assert correctly on validation failure`() {

        // Arrange
        val subject = GenericExtensionTestSubject(123)
        val validator = Validator.validatorFor<GenericExtensionTestSubject> {
            property(GenericExtensionTestSubject::property) {
                mustNotSatisfy({ value -> value as Int > 100 })
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not satisfy the specified predicate.", result.message)
    }
}
