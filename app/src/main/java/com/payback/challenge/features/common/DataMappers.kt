package com.payback.challenge.features.common

class DomainMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

class UiMapperException(message: String, throwable: Throwable?) : Exception(message, throwable)
class DatabaseMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

abstract class ApiToDatabaseMapper<INPUT : Any, OUTPUT : Any> {

    fun toDatabase(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DatabaseMapperException(
            "Could not map ${input::class.simpleName} to Database",
            throwable
        )
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class DatabaseToDomainMapper<INPUT : Any, OUTPUT : Any> {

    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException(
            "Could not map ${input::class.simpleName} to Domain",
            throwable
        )
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class ApiToDomainMapper<INPUT : Any, OUTPUT : Any> {
    fun toDomain(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException("Could not map ${input::class.simpleName} to Domain", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class DomainToPresentationMapper<INPUT : Any, OUTPUT : Any> {

    fun toPresentation(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw DomainMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class PresentationToUiMapper<INPUT : Any, OUTPUT : Any> {

    fun toUi(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw UiMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}
