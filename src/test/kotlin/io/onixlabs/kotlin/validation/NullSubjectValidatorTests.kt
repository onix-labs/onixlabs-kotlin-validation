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
