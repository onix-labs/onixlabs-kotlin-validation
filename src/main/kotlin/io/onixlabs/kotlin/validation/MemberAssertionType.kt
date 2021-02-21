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

import javax.xml.bind.annotation.XmlEnumValue

/**
 * Specifies the type of assertion made against a member.
 */
enum class MemberAssertionType(private val alternativeName: String) {

    /**
     * The assertion message is absolute and should be returned as-is, rather than computed.
     */
    @Suppress("UNUSED")
    @XmlEnumValue("Absolute")
    ABSOLUTE("Absolute"),

    /**
     * The assertion message is relative to its position in the object graph and can be computed.
     */
    @XmlEnumValue("Relative")
    RELATIVE("Relative");

    /**
     * Returns a string that represents the current object.
     *
     * @return Returns a string that represents the current object.
     */
    override fun toString(): String = alternativeName
}
