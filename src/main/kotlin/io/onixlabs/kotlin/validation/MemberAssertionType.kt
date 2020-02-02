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