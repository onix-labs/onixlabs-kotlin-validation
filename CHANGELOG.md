![ONIX Labs](https://raw.githubusercontent.com/onix-labs/onix-labs.github.io/master/content/logo/master_full_md.png)

# Change Log

This document serves as the change log for the ONIXLabs Kotlin Validation API.

## Version 2.0.0

#### ValidationGraphException (class)

Represents an exception that will be thrown when validation mode is set to DEFAULT.

#### Validator.validate (function)

Builds and validates a subject on-the-fly.

## Version 1.0.0

#### MemberAssertion (class)

Represents an assertion made against a member in the object graph.

#### MemberAssertionContext (class)

Represents an assertion context over a member in the object graph.

#### MemberAssersionType (enum)

Specifies the type of assertion made against a member.

-   **ABSOLUTE** - The assertion message is absolute and should be returned as-is, rather than computed.
-   **RELATIVE** - The assertion message is relative to its position in the object graph and can be computed.

#### MemberResult (class)

Represents the validation result of a member in the object graph.

#### ValidationBuilder (class)

Represents a builder to construct validation assertions.

#### ValidationCondition (abstract class)

Represents the base class for implementing validation conditions.

#### ValidationException (class)

Represents an exception that will be thrown when validation mode is set to `STOP_ON_ERROR`.

#### ValidationMode (enum)

Specifies the validation mode of the object graph validator.

-   **DEFAULT** - Validates the entire object graph.
-   **STOP_ON_ERROR** - Validates the object graph until an error is identified.
-   **EMULATION** - Validates the object graph in emulation mode.

#### ValidationResult (open class)

Represents the root of a validated object graph. This class is extended by `MemberResult` to allow tree structure object graphs.

#### Validator (abstract class)

Represents the base class for validator implementations.

### Extensions

#### MemberAssertionContext Boolean Extensions

-   Asserts that the subject must be true.
-   Asserts that the subject must be false.

#### MemberAssertionContext Comparable Extensions

-   Asserts that the subject must be within the specified range.
-   Asserts that the subject must not be within the specified range.
-   Asserts that the subject must be greater than the specified value.
-   Asserts that the subject must be greater than or equal to the specified value.
-   Asserts that the subject must be less than the specified value.
-   Asserts that the subject must be less than or equal to the specified value.

#### MemberAssertionContext Generic Extensions

-   Asserts that the subject must be null.
-   Asserts that the subject must not be null.
-   Asserts that the subject must be equal to the specified value.
-   Asserts that the subject must not be equal to the specified value.
-   Asserts that the subject must be equal by reference to the specified value.
-   Asserts that the subject must not be equal by reference to the specified value.
-   Asserts that the subject must be equal to any of the specified values.
-   Asserts that the subject must not be equal to any of the specified values.
-   Asserts that the subject must satisfy the specified predicate.
-   Asserts that the subject must not satisfy the specified predicate.

#### MemberAssertionContext Iterable Extensions

-   Asserts that the subject must contain the specified value.
-   Asserts that the subject must contain all of the specified values.
-   Asserts that the subject must contain any of the specified values.
-   Asserts that the subject must contain all of the specified values exactly.
-   Asserts that the subject must contain all of the specified values exactly, and in the same order.
-   Asserts that the subject must have the specified element count.
-   Asserts that the subject must be empty.
-   Asserts that the subject must not be empty.
-   Asserts that the subject must contain a single element.
-   Asserts that the subject must contain distinct elements.

#### MemberAssertionContext Map Extensions

-   Asserts that the subject must contain the specified key.
-   Asserts that the subject must not contain the specified key.
-   Asserts that the subject must contain the specified value.
-   Asserts that the subject must not contain the specified value.
-   Asserts that the subject must contain the specified entry.
-   Asserts that the subject must not contain the specified entry.

#### MemberAssertionContext String Extensions

-   Asserts that the subject must be null or blank.
-   Asserts that the subject must not be null or blank.
-   Asserts that the subject must be null or empty.
-   Asserts that the subject must not be null or empty.
-   Asserts that the subject must be blank.
-   Asserts that the subject must not be blank.
-   Asserts that the subject must be empty.
-   Asserts that the subject must not be empty.
-   Asserts that the subject must contain the specified value.
-   Asserts that the subject must not contain the specified value.
-   Asserts that the subject must contain all of the specified values.
-   Asserts that the subject must contain any of the specified values.
-   Asserts that the subject must end with the specified value.
-   Asserts that the subject must not end with the specified value.
-   Asserts that the subject must start with the specified value.
-   Asserts that the subject must not start with the specified value.
-   Asserts that the subject must have the specified length.
-   Asserts that the subject must be between the specified minimum and maximum length.
-   Asserts that the subject must have the specified maximum length.
-   Asserts that the subject must have the specified minimum length.
-   Asserts that the subject must be a valid Luhn (Mod10) checksum.
-   Asserts that the subject must match the specified regular expression.