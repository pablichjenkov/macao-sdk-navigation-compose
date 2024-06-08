package com.macaosoftware.component.core

sealed class DestinationResult<out T> {
    class Success<T>(val value: T) : DestinationResult<T>()
    class Error(val error: DestinationError) : DestinationResult<Nothing>()
}

sealed interface DestinationError
data object Cancel : DestinationError