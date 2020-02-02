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