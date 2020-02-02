package io.onixlabs.kotlin.validation

import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period
import java.util.*

data class ComparableExtensionTestSubject(val property: Int?)

data class GenericExtensionTestSubject(val property: Any?)

data class IterableExtensionTestSubject(val property: Iterable<Int>?)

data class StringExtensionTestSubject(val property: String?)

data class BooleanExtensionTestSubject(val property: Boolean?)

data class MapExtensionTestSubject(val property: Map<String, Any>?)

data class NameValuePair(val name: String, val value: Any?)

data class ComplexSubject(
    val title: String,
    val primaryReference: String?,
    val secondaryReference: String?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val arbitraryAmount: BigDecimal,
    val items: List<NameValuePair>?,
    val metadata: Map<String, UUID>,
    val defaultNameValuePair: NameValuePair
)

fun ComplexSubject.elapsed() = Period.between(startDate, endDate).days

fun ComplexSubject.getMetadataValue(key: String): UUID {
    return metadata[key] ?: throw IllegalArgumentException("Key not found.")
}

data class NullSubject(
    val a: String? = null,
    val b: Int? = null,
    val c: Boolean? = null,
    val d: List<Any>? = null
)

class NullSubjectValidator : Validator<NullSubject>() {
    override fun validate(builder: ValidationBuilder<NullSubject>) {
        builder.allProperties { mustNotBeNull() }
    }
}

class ComplexSubjectValidator : Validator<ComplexSubject>() {

    override fun validate(builder: ValidationBuilder<ComplexSubject>) {

        builder.property(ComplexSubject::title) {
            mustHaveMinimumLength(10)
            mustHaveMaximumLength(100)
        }

        builder.property(ComplexSubject::primaryReference, ComplexSubject::secondaryReference) {
            mustNotBeNullOrBlank()
            mustStartWith("REF")
        }

        builder.property(ComplexSubject::secondaryReference) {
            mustEndWith("_2ND")
        }

        builder.property(ComplexSubject::startDate) {
            mustBeLessThanOrEqualTo(LocalDate.of(2000, 1, 1))
        }

        builder.property(ComplexSubject::endDate) {
            mustBeGreaterThan(LocalDate.of(2000, 1, 1))
        }

        builder.property(ComplexSubject::arbitraryAmount) {
            mustBeGreaterThanOrEqualTo(BigDecimal.ZERO)
        }

        builder.property(ComplexSubject::items) {
            mustNotBeNull()
            mustHaveCount(10)
        }

        builder.collection(ComplexSubject::items) {
            validateWith(validatorFor {
                property(NameValuePair::name) {
                    mustNotStartWith("$")
                }

                property(NameValuePair::value) {
                    mustNotBeNull()
                    mustSatisfy({ it is Boolean })
                }
            })
        }

        builder.map(ComplexSubject::metadata) {
            mustSatisfy({ it.value == UUID.fromString("f1758a23-c14f-4397-957d-c63af8ca6273") })
        }

        builder.property(ComplexSubject::defaultNameValuePair) {
            validateWith(validatorFor {
                property(NameValuePair::name) {
                    mustNotStartWith("$")
                }

                property(NameValuePair::value) {
                    mustNotBeNull()
                    mustBeEqualTo(true)
                }
            })
        }

        builder.function(ComplexSubject::elapsed) {
            mustBeGreaterThan(2)
        }

        builder.function(ComplexSubject::getMetadataValue, "hello") {
            mustBeEqualTo(UUID.fromString("f1758a23-c14f-4397-957d-c63af8ca6273"))
        }
    }
}
