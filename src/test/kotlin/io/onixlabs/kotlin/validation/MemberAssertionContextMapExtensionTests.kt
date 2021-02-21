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
