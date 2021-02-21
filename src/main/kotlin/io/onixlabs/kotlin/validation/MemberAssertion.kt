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
 * Represents an assertion made against a member in the object graph.
 *
 * @param condition The condition which will be used to determine validity of the assertion.
 * @param type The type of the assertion.
 * @param message The message representing the assertion.
 */
class MemberAssertion internal constructor(
    val condition: ValidationCondition,
    val message: String,
    val type: MemberAssertionType = MemberAssertionType.RELATIVE
) {

    /**
     * Gets a formatted assertion message string.
     *
     * @param relativePrefix The relative prefix for an assertion message.
     * @return Returns a formatted assertion message string.
     */
    internal fun toString(relativePrefix: String): String {
        return if (type == MemberAssertionType.RELATIVE) "$relativePrefix $message" else message
    }
}
