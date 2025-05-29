package com.dahrenericsson.bubblebaerework.presentation.ui.common.validation

import android.util.Patterns
import io.konform.validation.Validation
import io.konform.validation.constraints.minLength
import io.konform.validation.constraints.pattern

object CommonValidationRules {
    val email = Validation<String> {
        pattern(Patterns.EMAIL_ADDRESS.pattern()) hint "Invalid email format"
    }

    val password = Validation<String> {
        minLength(8) hint "Password must be at least 8 characters long"
        pattern(".*[A-Z].*") hint "Password must contain at least one uppercase letter"
        pattern(".*[a-z].*") hint "Password must contain at least one lowercase letter"
        pattern(".*[0-9].*") hint "Password must contain at least one digit"
        pattern(".*[!@#\$%^&*(),.?\":{}|<>].*") hint "Password must contain at least one special character"
    }
}