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

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import java.io.File


internal object ComplexSubjectValidationTestUtils {
    private val mapper = with(ObjectMapper()) {
        registerModule(JavaTimeModule())
        setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
        setSerializationInclusion(JsonInclude.Include.NON_DEFAULT)
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    fun loadExpectedGraph(): String {
        val classLoader = ClassLoader.getSystemClassLoader()
        val file = File(classLoader.getResource("complex-subject-validator.graph.min.json")!!.file)
        return file.readText()
    }

    fun serialize(subject: Any): String = mapper.writeValueAsString(subject)
}
