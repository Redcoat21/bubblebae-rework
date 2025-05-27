package com.dahrenericsson.bubblebaerework.domain.common

enum class ErrorType {
    NETWORK_ERROR, // Is used for network-related issues, such as no internet connection or timeout.
    DATABASE_ERROR, // Is used for database-related issues, such as SQL errors
    UNKNOWN_ERROR,
    NOT_FOUND_ERROR, // Is used when a requested resource is not found, such as a user or item.
    VALIDATION_ERROR, // Is used for validation issues, such as invalid input or missing required fields.
}