package io.onixlabs.kotlin.validation

/**
 * Specifies the validation mode of the object graph validator.
 */
enum class ValidationMode {

    /**
     * Validates the entire object graph.
     * All failed validation assertions are collected and returned upon completion.
     */
    DEFAULT,

    /**
     * Validates the object graph until an error is identified.
     * The first validation assertion to fail will cause an exception to be thrown immediately.
     */
    STOP_ON_ERROR,

    /**
     * Validates the object graph in emulation mode.
     * The entire object graph is assumed to be invalid, thus collecting all validation assertions.
     */
    EMULATION
}