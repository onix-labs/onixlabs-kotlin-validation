package io.onixlabs.kotlin.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MemberAssertionContextBooleanExtensionTests {

    private val member =
        "Property 'BooleanExtensionTestSubject.property' of type 'kotlin.Boolean?'"

    @Test
    fun `MemberAssertionContext_mustBeTrue should assert correctly on validation failure`() {

        // Arrange
        val subject = BooleanExtensionTestSubject(false)
        val validator = Validator.validatorFor<BooleanExtensionTestSubject> {
            property(BooleanExtensionTestSubject::property) {
                mustBeTrue()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be true.", result.message)
    }

    @Test
    fun `MemberAssertionContext_mustBeFalse should assert correctly on validation failure`() {

        // Arrange
        val subject = BooleanExtensionTestSubject(true)
        val validator = Validator.validatorFor<BooleanExtensionTestSubject> {
            property(BooleanExtensionTestSubject::property) {
                mustBeFalse()
            }
        }

        // Act
        val result = assertThrows<ValidationException> {
            validator.validate(subject, ValidationMode.STOP_ON_ERROR)
        }

        // Assert
        assertEquals("$member must be false.", result.message)
    }
}