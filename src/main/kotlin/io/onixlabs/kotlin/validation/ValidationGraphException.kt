package io.onixlabs.kotlin.validation

/**
 * Represents an exception that will be thrown when validation mode is set to DEFAULT.
 *
 * @param message A message detailing the validation exception. Typically this should be the assertion message.
 * @param failures The failures that occurred during validation.
 * @param cause An underlying cause of the validation exception.
 */
class ValidationGraphException(
    message: String,
    val failures: List<String>,
    override val cause: Throwable? = null
) : ValidationException(message, cause)
