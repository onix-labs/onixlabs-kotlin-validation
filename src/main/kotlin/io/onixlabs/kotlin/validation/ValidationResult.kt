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

import io.onixlabs.kotlin.core.reflection.formattedQualifiedName
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

/**
 * Represents the root of a validated object graph.
 * This class is extended by [MemberResult] to allow tree structure object graphs.
 *
 * @param name The name of the subject being validated.
 * @param parent The parent of this validation result.
 * Validation results are implemented as a forked, singly linked list
 * A null parent indicates the root of the validation object graph.
 */
open class ValidationResult internal constructor(val name: String, protected val parent: ValidationResult? = null) {

    private val _members = mutableListOf<ValidationResult>()

    /**
     * Gets all validated members within the validation object graph.
     */
    @Suppress
    val members: List<ValidationResult>
        get() = _members

    /**
     * Adds a new validation result member to the validation object graph.
     *
     * @param member The member to add to the graph.
     */
    internal fun addMember(member: ValidationResult) = _members.add(member)

    /**
     * Creates a validation result with this validation result as its parent.
     *
     * @param kotlinClass The kotlin class of the nested object.
     * @return Returns a validation result with this result as its parent.
     */
    internal fun createMember(kotlinClass: KClass<*>): ValidationResult {
        return ValidationResult(
            name = kotlinClass.simpleName!!,
            parent = this
        )
    }

    /**
     * Creates a member result with this validation result as its parent.
     *
     * @param callable The callable property or function of the member to validate.
     * @return Returns a member result with this result as its parent.
     */
    internal fun createMember(callable: KCallable<*>): MemberResult {
        return MemberResult(
            name = callable.name,
            type = callable.returnType.formattedQualifiedName,
            declaration = when (callable) {
                is KProperty -> "Property"
                is KFunction -> "Function"
                else -> throw IllegalArgumentException("Unknown callable type.")
            },
            nullable = callable.returnType.isMarkedNullable,
            parent = this
        )
    }

    /**
     * Creates a member result with this validation result as its parent.
     *
     * @param callable The callable property or function of the member to validate.
     * @param index The index of the element in a collection.
     * @return Returns a member result with this result as its parent.
     */
    internal fun createMember(callable: KCallable<*>, index: Int): MemberResult {
        val elementType = callable.returnType.arguments[0].type
        return MemberResult(
            name = index.toString(),
            type = elementType?.jvmErasure?.qualifiedName!!,
            declaration = "Element",
            nullable = elementType.isMarkedNullable,
            parent = this
        )
    }

    /**
     * Creates a member result with this validation result as its parent.
     *
     * @param callable The callable property or function of the member to validate.
     * @param key The key of the entry in a map.
     * @return Returns a member result with this result as its parent.
     */
    internal fun createMember(callable: KCallable<*>, key: String): MemberResult {
        val elementType = callable.returnType.arguments[0].type
        return MemberResult(
            name = key,
            type = elementType?.jvmErasure?.qualifiedName!!,
            declaration = "Entry",
            nullable = elementType.isMarkedNullable,
            parent = this
        )
    }

    /**
     * Calculates the graph path back to the root of the object graph.
     *
     * @return Returns a string representation of the object graph from the root to the current leaf node.
     */
    internal open fun calculateGraphPath(): String = parent?.calculateGraphPath() ?: name

    /**
     * Gets all failed validation assertions from this and all nested members as a flat string list.
     *
     * @return Returns all failed validation assertions from this and all nested members as a flat string list.
     */
    open fun toList(): List<String> = members.flatMap { it.toList() }

    /**
     * Gets a string representation of the current object.
     * This collates and flattens all assertion results into a newline separated string.
     *
     * @return Returns a flattened, newline separated string of all assertion results.
     */
    override fun toString(): String = toList().joinToString("\n")
}
