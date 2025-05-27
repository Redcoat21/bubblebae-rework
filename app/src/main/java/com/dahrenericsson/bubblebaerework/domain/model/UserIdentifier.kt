package com.dahrenericsson.bubblebaerework.domain.model

/**
 * Represents a user identifier in the application.
 * This class can be extended in the future to include more properties or methods related to user identification.
 */
sealed class UserIdentifier {
    data class Id(val id: String) : UserIdentifier()
    data class Email(val email: String) : UserIdentifier()
    data class Username(val username: String) : UserIdentifier()

    fun isValid(): Boolean {
        return when (this) {
            is Id -> id.isNotBlank()
            is Email -> email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            is Username -> username.isNotBlank()
        }
    }
}