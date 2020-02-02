package io.onixlabs.kotlin.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MemberAssertionContextMapExtensionTests {

    private val member =
        "Property 'MapExtensionTestSubject.property' of type 'kotlin.collections.Map<kotlin.String, kotlin.Any>?'"

    @Test
    fun `MemberAssertionContext_mustContainKey should assert correctly on validation failure`() {

        // Arrange
        val subject = MapExtensionTestSubject(mapOf("hello" to 123))
        val validator = Validator.validatorFor<MapExtensionTestSubject> {
            property(MapExtensionTestSubject::property) {
                mustContainKey("goodbye")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain the specified key: goodbye.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotContainKey should assert correctly on validation failure`() {

        // Arrange
        val subject = MapExtensionTestSubject(mapOf("hello" to 123))
        val validator = Validator.validatorFor<MapExtensionTestSubject> {
            property(MapExtensionTestSubject::property) {
                mustNotContainKey("hello")
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not contain the specified key: hello.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainValue should assert correctly on validation failure`() {

        // Arrange
        val subject = MapExtensionTestSubject(mapOf("hello" to 123))
        val validator = Validator.validatorFor<MapExtensionTestSubject> {
            property(MapExtensionTestSubject::property) {
                mustContainValue(456)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain the specified value: 456.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotContainValue should assert correctly on validation failure`() {

        // Arrange
        val subject = MapExtensionTestSubject(mapOf("hello" to 123))
        val validator = Validator.validatorFor<MapExtensionTestSubject> {
            property(MapExtensionTestSubject::property) {
                mustNotContainValue(123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not contain the specified value: 123.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustContainEntry should assert correctly on validation failure`() {

        // Arrange
        val subject = MapExtensionTestSubject(mapOf("hello" to 123))
        val validator = Validator.validatorFor<MapExtensionTestSubject> {
            property(MapExtensionTestSubject::property) {
                mustContainEntry("goodbye", 456)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must contain the specified entry: goodbye to 456.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustNotContainEntry should assert correctly on validation failure`() {

        // Arrange
        val subject = MapExtensionTestSubject(mapOf("hello" to 123))
        val validator = Validator.validatorFor<MapExtensionTestSubject> {
            property(MapExtensionTestSubject::property) {
                mustNotContainEntry("hello", 123)
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must not contain the specified entry: hello to 123.", result.message)
    }
}