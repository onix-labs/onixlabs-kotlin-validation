package io.onixlabs.kotlin.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MemberAssertionContextIterableExtensionTests {

    private val member =
        "Property 'IterableExtensionTestSubject.property' of type 'kotlin.collections.Iterable<kotlin.Int>?'"

    @Test
    fun `MemberAssertionContext_mustContain should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustContain(789)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain the specified element value: 789.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainAll should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustContainAll(listOf(123, 456, 789))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain all of the specified element values: 123, 456, 789.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainAny should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustContainAny(listOf(1, 2, 3))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain any of the specified element values: 1, 2, 3.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainExactly should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustContainExactly(listOf(123, 456, 789))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain exactly the specified element values: 123, 456, 789.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainOrderedExactly should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustContainOrderedExactly(listOf(456, 123))
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain exactly the specified element values in order: 456, 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustHaveCount should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustHaveCount(3)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain exactly the specified number of elements: 3.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeEmpty should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
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
        val subject = IterableExtensionTestSubject(emptyList())
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
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
    fun `MemberAssertionContext_mustBeSingle should assert correctly on validation failure`() {

        // Arrange
        val subject = IterableExtensionTestSubject(listOf(123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustBeSingle()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain a single element.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeDistinct should assert correctly on validation failure`() {

        // Arrange
        val subject =
            IterableExtensionTestSubject(listOf(123, 123, 456))
        val validator = Validator.validatorFor<IterableExtensionTestSubject> {
            property(IterableExtensionTestSubject::property) {
                mustBeDistinct()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain distinct elements.", result.message)
    }
}