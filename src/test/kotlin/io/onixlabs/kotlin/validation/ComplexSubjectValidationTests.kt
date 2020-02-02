package io.onixlabs.kotlin.validation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexSubjectValidationTests {

    @Test
    fun `ComplexSubjectValidator_validate should produce the expected validation object graph`() {

        // Arrange
        val validator = ComplexSubjectValidator()
        val expected =
            SubjectValidationTestUtils.loadExpectedComplexSubjectGraph()

        // Act
        val result = validator.validate()
        val graph = SubjectValidationTestUtils.serialize(result)

        // Assert
        assertEquals(expected, graph)
    }

    @Test
    fun `ComplexSubjectValidator_validate should produce the expected validation output`() {

        // Arrange
        val validator = ComplexSubjectValidator()
        val expected = listOf(
            "Property 'ComplexSubject.title' of type 'kotlin.String' must have the specified minimum length: 10.",
            "Property 'ComplexSubject.title' of type 'kotlin.String' must have the specified maximum length: 100.",
            "Property 'ComplexSubject.primaryReference' of type 'kotlin.String?' must not be null or blank.",
            "Property 'ComplexSubject.primaryReference' of type 'kotlin.String?' must start with the specified value: REF.",
            "Property 'ComplexSubject.secondaryReference' of type 'kotlin.String?' must not be null or blank.",
            "Property 'ComplexSubject.secondaryReference' of type 'kotlin.String?' must start with the specified value: REF.",
            "Property 'ComplexSubject.secondaryReference' of type 'kotlin.String?' must end with the specified value: _2ND.",
            "Property 'ComplexSubject.startDate' of type 'java.time.LocalDate' must be less than or equal to the specified value: 2000-01-01.",
            "Property 'ComplexSubject.endDate' of type 'java.time.LocalDate' must be greater than the specified value: 2000-01-01.",
            "Property 'ComplexSubject.arbitraryAmount' of type 'java.math.BigDecimal' must be greater than or equal to the specified value: 0.",
            "Property 'ComplexSubject.items' of type 'kotlin.collections.List<io.onixlabs.kotlin.validation.NameValuePair>?' must not be null.",
            "Property 'ComplexSubject.items' of type 'kotlin.collections.List<io.onixlabs.kotlin.validation.NameValuePair>?' must contain exactly the specified number of elements: 10.",
            "Property 'ComplexSubject.items[0].name' of type 'kotlin.String' must not start with the specified value: $.",
            "Property 'ComplexSubject.items[0].value' of type 'kotlin.Any?' must not be null.",
            "Property 'ComplexSubject.items[0].value' of type 'kotlin.Any?' must satisfy the specified predicate.",
            "Entry 'ComplexSubject.metadata[key]' of type 'kotlin.String' must satisfy the specified predicate.",
            "Property 'ComplexSubject.defaultNameValuePair.name' of type 'kotlin.String' must not start with the specified value: $.",
            "Property 'ComplexSubject.defaultNameValuePair.value' of type 'kotlin.Any?' must not be null.",
            "Property 'ComplexSubject.defaultNameValuePair.value' of type 'kotlin.Any?' must be equal to the specified value: true.",
            "Function 'ComplexSubject.elapsed()' of type 'kotlin.Int' must be greater than the specified value: 2.",
            "Function 'ComplexSubject.getMetadataValue()' of type 'java.util.UUID' must be equal to the specified value: f1758a23-c14f-4397-957d-c63af8ca6273."
        )

        // Act
        val result = validator.validate().toList()

        // Assert
        assertEquals(expected, result)
    }
}