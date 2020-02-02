package io.onixlabs.kotlin.validation

import io.onixlabs.kotlin.core.reflection.getProperties
import kotlin.reflect.*

/**
 * Represents a builder to construct validation assertions.
 *
 * @param result The complete result of validation against an object graph.
 * @param mode Specifies how the subject will be validated.
 * @param subject The subject under validation.
 */
class ValidationBuilder<T> internal constructor(
    private val result: ValidationResult,
    private val mode: ValidationMode,
    private val subjectType: KClass<*>,
    val subject: T?
) {

    /**
     * Performs assertions against elements in a collection.
     *
     * @param property The collection property to make assertions against.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNCHECKED_CAST")
    fun <R> collection(
        property: KProperty1<T, Iterable<R>?>,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(property)
        val collection = subject?.let { property.get(it) } ?: listOf(null)

        collection.forEachIndexed { index, element ->

            val elementResult = memberResult.createMember(property, index)
            val context =
                MemberAssertionContext(elementResult, mode, element)

            action(context as MemberAssertionContext<R>)

            if (elementResult.toList().isNotEmpty()) {
                memberResult.addMember(elementResult)
                result.addMember(memberResult)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <K, V> map(
        property: KProperty1<T, Map<K, V>?>,
        action: MemberAssertionContext<Map.Entry<K, V>>.() -> Unit
    ) {
        val memberResult = result.createMember(property)
        val entries = (subject?.let { property.get(it) } ?: mapOf(null as K to null as V)).entries

        entries.forEach {
            val key = if (it.key is String) "\"${it.key.toString()}\"" else "key"
            val entryResult = memberResult.createMember(property, key)
            val context = MemberAssertionContext(entryResult, mode, it)

            action(context)

            if (entryResult.toList().isNotEmpty()) {
                memberResult.addMember(entryResult)
                result.addMember(memberResult)
            }
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNCHECKED_CAST")
    fun <R> function(
        function: KFunction1<T, R>,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNCHECKED_CAST")
    fun <R, A1> function(
        function: KFunction2<T, A1, R>, arg1: A1,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2> function(
        function: KFunction3<T, A1, A2, R>,
        arg1: A1,
        arg2: A2,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param arg3 The third argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2, A3> function(
        function: KFunction4<T, A1, A2, A3, R>,
        arg1: A1,
        arg2: A2,
        arg3: A3,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2, arg3)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param arg3 The third argument of the function.
     * @param arg4 The fourth argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2, A3, A4> function(
        function: KFunction5<T, A1, A2, A3, A4, R>,
        arg1: A1,
        arg2: A2,
        arg3: A3,
        arg4: A4,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2, arg3, arg4)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param arg3 The third argument of the function.
     * @param arg4 The fourth argument of the function.
     * @param arg5 The fifth argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2, A3, A4, A5> function(
        function: KFunction6<T, A1, A2, A3, A4, A5, R>,
        arg1: A1,
        arg2: A2,
        arg3: A3,
        arg4: A4,
        arg5: A5,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2, arg3, arg4, arg5)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param arg3 The third argument of the function.
     * @param arg4 The fourth argument of the function.
     * @param arg5 The fifth argument of the function.
     * @param arg6 The sixth argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2, A3, A4, A5, A6> function(
        function: KFunction7<T, A1, A2, A3, A4, A5, A6, R>,
        arg1: A1,
        arg2: A2,
        arg3: A3,
        arg4: A4,
        arg5: A5,
        arg6: A6,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2, arg3, arg4, arg5, arg6)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param arg3 The third argument of the function.
     * @param arg4 The fourth argument of the function.
     * @param arg5 The fifth argument of the function.
     * @param arg6 The sixth argument of the function.
     * @param arg7 The seventh argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2, A3, A4, A5, A6, A7> function(
        function: KFunction8<T, A1, A2, A3, A4, A5, A6, A7, R>,
        arg1: A1,
        arg2: A2,
        arg3: A3,
        arg4: A4,
        arg5: A5,
        arg6: A6,
        arg7: A7,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the result of a function call.
     *
     * @param function The function to make assertions against.
     * @param arg1 The first argument of the function.
     * @param arg2 The second argument of the function.
     * @param arg3 The third argument of the function.
     * @param arg4 The fourth argument of the function.
     * @param arg5 The fifth argument of the function.
     * @param arg6 The sixth argument of the function.
     * @param arg7 The seventh argument of the function.
     * @param arg8 The eighth argument of the function.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNUSED", "UNCHECKED_CAST")
    fun <R, A1, A2, A3, A4, A5, A6, A7, A8> function(
        function: KFunction9<T, A1, A2, A3, A4, A5, A6, A7, A8, R>,
        arg1: A1,
        arg2: A2,
        arg3: A3,
        arg4: A4,
        arg5: A5,
        arg6: A6,
        arg7: A7,
        arg8: A8,
        action: MemberAssertionContext<R>.() -> Unit
    ) {
        val memberResult = result.createMember(function)
        val context =
            MemberAssertionContext(memberResult, mode, subject?.let {
                function.call(it, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8)
            })

        action(context as MemberAssertionContext<R>)

        if (memberResult.toList().isNotEmpty()) {
            result.addMember(memberResult)
        }
    }

    /**
     * Performs assertions against the value of a property.
     * Multiple properties can be specified allowing all properties to be validated with the same assertions.
     *
     * @param properties The properties to make assertions against.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNCHECKED_CAST")
    fun <R> property(properties: Iterable<KProperty1<T, R>>, action: MemberAssertionContext<R>.() -> Unit) {
        properties.forEach { property ->
            val memberResult = result.createMember(property)
            val context = MemberAssertionContext(
                memberResult,
                mode,
                subject?.let {
                    property.get(it)
                })

            action(context as MemberAssertionContext<R>)

            if (memberResult.toList().isNotEmpty()) {
                result.addMember(memberResult)
            }
        }
    }

    /**
     * Performs assertions against the value of a property.
     * Multiple properties can be specified allowing all properties to be validated with the same assertions.
     *
     * @param properties The properties to make assertions against.
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNCHECKED_CAST")
    fun <R> property(vararg properties: KProperty1<T, R>, action: MemberAssertionContext<R>.() -> Unit) {
        property(properties.toList(), action)
    }

    /**
     * Performs assertions against the values of all properties in the subject.
     * All properties are restricted to assertions of type [Any] since this is the lowest common denominator.
     *
     * @param action The action block where assertions may be configured.
     */
    @Suppress("UNCHECKED_CAST")
    fun allProperties(action: MemberAssertionContext<Any?>.() -> Unit) {
        property(subjectType.getProperties() as List<KProperty1<T, Any?>>, action)
    }
}