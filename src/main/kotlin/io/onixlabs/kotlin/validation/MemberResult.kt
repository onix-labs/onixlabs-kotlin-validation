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

/**
 * Represents the validation result of a member in the object graph.
 *
 * @param name The name of the member.
 * @param type The type of the member.
 * @param declaration The declaration of the member.
 * @param nullable Determines whether the member's return type is nullable.
 * @param parent The parent of this member result.
 */
class MemberResult(
    name: String,
    val type: String,
    val declaration: String,
    val nullable: Boolean,
    parent: ValidationResult
) : ValidationResult(name, parent) {

    private val _assertions = mutableListOf<MemberAssertion>()

    /**
     * Gets a list of failed validation assertions made against this member.
     */
    val assertions: List<MemberAssertion> get() = _assertions

    /**
     * Adds an assertion to the member.
     *
     * @param assertion The assertion to add.
     */
    internal fun addAssertion(assertion: MemberAssertion) = _assertions.add(assertion)

    /**
     * Calculates the graph path back to the root of the object graph.
     *
     * @return Returns a string representation of the object graph from the root to the current leaf node.
     */
    override fun calculateGraphPath(): String {
        return if (parent == null) name else when (declaration) {
            "Property" -> "${parent.calculateGraphPath()}.$name"
            "Function" -> "${parent.calculateGraphPath()}.$name()"
            "Element", "Entry" -> "${parent.calculateGraphPath()}[$name]"
            else -> throw IllegalArgumentException("Unknown declaration type.")
        }
    }

    /**
     * Creates a relative assertion prefix.
     *
     * @return Returns a formatted relative assertion prefix.
     */
    internal fun createRelativeAssertionPrefix(): String = "$declaration '${calculateGraphPath()}' of type '$type'"

    /**
     * Gets all failed validation assertions from this and all nested members as a flat string list.
     *
     * @return Returns all failed validation assertions from this and all nested members as a flat string list.
     */
    override fun toList(): List<String> {
        return super.toList() + assertions.map { it.toString(createRelativeAssertionPrefix()) }
    }
}
