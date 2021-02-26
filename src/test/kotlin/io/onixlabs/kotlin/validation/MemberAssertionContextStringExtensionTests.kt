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


class MemberAssertionContextStringExtensionTests {

    private val member = "Property 'StringExtensionTestSubject.property' of type 'kotlin.String?'"

    @Test
    fun `MemberAssertionContext_mustBeNullOrBlank should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustBeNullOrBlank()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be null or blank.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeNullOrBlank should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject(null)
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotBeNullOrBlank()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be null or blank.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeNullOrEmpty should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustBeNullOrEmpty()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be null or empty.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeNullOrEmpty should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject(null)
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotBeNullOrEmpty()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be null or empty.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeBlank should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustBeBlank()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be blank.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeBlank should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotBeBlank()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be blank.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeEmpty should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject(" ")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustBeEmpty()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be empty.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeEmpty should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotBeEmpty()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be empty.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContain should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustContain("Awesome")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain the specified value: Awesome.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotContain should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotContain("World")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not contain the specified value: World.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainAll should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Cat Dog")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustContainAll(listOf("Cat", "Dog", "Fox"))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain all of the specified values: Cat, Dog, Fox.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainAny should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Cat")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustContainAny(listOf("Dog", "Fox"))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain any of the specified values: Dog, Fox.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustEndWith should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello Mars")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustEndWith("World")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must end with the specified value: World.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotEndWith should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotEndWith("World")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not end with the specified value: World.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustStartWith should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustStartWith("Goodbye")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must start with the specified value: Goodbye.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotStartWith should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustNotStartWith("Hello")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not start with the specified value: Hello.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustHaveLength should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustHaveLength(100)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must have the specified length: 100.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustHaveLength should assert correctly on validation failure (range check, min)`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustHaveLength(10..20)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be between the specified minimum and maximum length: 10..20", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustHaveLength should assert correctly on validation failure (range check, max)`() {

        // Arrange
        val subject =
            StringExtensionTestSubject("The quick brown fox jumps over the lazy dog")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustHaveLength(10..20)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be between the specified minimum and maximum length: 10..20", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustHaveMaximumLength should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustHaveMaximumLength(5)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must have the specified maximum length: 5.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustHaveMinimumLength should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustHaveMinimumLength(100)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must have the specified minimum length: 100.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeValidLuhnChecksum should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustBeValidLuhnChecksum()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be a valid Luhn (Mod10) checksum.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustMatchRegex should assert correctly on validation failure`() {

        // Arrange
        val subject = StringExtensionTestSubject("Hello World")
        val validator = Validator.validatorFor<StringExtensionTestSubject> {
            property(StringExtensionTestSubject::property) {
                mustMatchRegex(Regex("[A-Z]"))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must match the specified regular expression: [A-Z].", result.message)
    }
}
