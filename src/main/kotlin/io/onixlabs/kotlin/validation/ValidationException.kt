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
 * Represents an exception that will be thrown when validation mode is set to STOP_ON_ERROR.
 *
 * @param message A message detailing the validation exception. Typically this should be the assertion message.
 * @param cause An underlying cause of the validation exception.
 */
class ValidationException(message: String, cause: Throwable? = null) : Exception(message, cause)
