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
