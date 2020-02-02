package io.onixlabs.kotlin.validation

/**
 * Represents an exception that will be thrown when validation mode is set to STOP_ON_ERROR.
 *
 * @param message A message detailing the validation exception. Typically this should be the assertion message.
 * @param cause An underlying cause of the validation exception.
 */
class ValidationException(message: String, cause: Throwable? = null) : Exception(message, cause)