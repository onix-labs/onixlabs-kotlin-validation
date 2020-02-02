package io.onixlabs.kotlin.validation

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NullSubjectValidatorTests {

    @Test
    fun `ComplexSubjectValidator_validate should produce the expected validation object graph`() {

        // Arrange
        val validator = NullSubjectValidator()
        val expected =
            SubjectValidationTestUtils.loadExpectedNullSubjectGraph()

        // Act
        val result = validator.validate()
        val graph = SubjectValidationTestUtils.serialize(result)

        // Assert
        Assertions.assertEquals(expected, graph)
    }

    @Test
    fun `NullSubjectValidator_validate should produce the expected validation output`() {

        // Arrange
        val validator = NullSubjectValidator()
        val expected = listOf(
            "Property 'NullSubject.a' of type 'kotlin.String?' must not be null.",
            "Property 'NullSubject.b' of type 'kotlin.Int?' must not be null.",
            "Property 'NullSubject.c' of type 'kotlin.Boolean?' must not be null.",
            "Property 'NullSubject.d' of type 'kotlin.collections.List<kotlin.Any>?' must not be null."
        )

        // Act
        val result = validator.validate().toList()

        // Assert
        Assertions.assertEquals(expected, result)
    }
}