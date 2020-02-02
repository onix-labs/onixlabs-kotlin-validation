package io.onixlabs.kotlin.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MemberAssertionContextComparableExtensionTests {

    private val member =
        "Property 'ComparableExtensionTestSubject.property' of type 'kotlin.Int?'"

    @Test
    fun `MemberAssertionContext_mustBeWithinRange should assert correctly on validation failure`() {

        // Arrange
        val subject = ComparableExtensionTestSubject(100)
        val validator = Validator.validatorFor<ComparableExtensionTestSubject> {
            property(ComparableExtensionTestSubject::property) {
                mustBeWithinRange(123, 456)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be within the specified range: 123 to 456.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotBeWithinRange should assert correctly on validation failure`() {

        // Arrange
        val subject = ComparableExtensionTestSubject(200)
        val validator = Validator.validatorFor<ComparableExtensionTestSubject> {
            property(ComparableExtensionTestSubject::property) {
                mustNotBeWithinRange(123, 456)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not be within the specified range: 123 to 456.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeGreaterThan should assert correctly on validation failure`() {

        // Arrange
        val subject = ComparableExtensionTestSubject(100)
        val validator = Validator.validatorFor<ComparableExtensionTestSubject> {
            property(ComparableExtensionTestSubject::property) {
                mustBeGreaterThan(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be greater than the specified value: 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeGreaterThanOrEqualTo should assert correctly on validation failure`() {

        // Arrange
        val subject = ComparableExtensionTestSubject(100)
        val validator = Validator.validatorFor<ComparableExtensionTestSubject> {
            property(ComparableExtensionTestSubject::property) {
                mustBeGreaterThanOrEqualTo(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be greater than or equal to the specified value: 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeLessThan should assert correctly on validation failure`() {

        // Arrange
        val subject = ComparableExtensionTestSubject(200)
        val validator = Validator.validatorFor<ComparableExtensionTestSubject> {
            property(ComparableExtensionTestSubject::property) {
                mustBeLessThan(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be less than the specified value: 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeLessThanOrEqualTo should assert correctly on validation failure`() {

        // Arrange
        val subject = ComparableExtensionTestSubject(200)
        val validator = Validator.validatorFor<ComparableExtensionTestSubject> {
            property(ComparableExtensionTestSubject::property) {
                mustBeLessThanOrEqualTo(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be less than or equal to the specified value: 123.", result.message)
    }
}