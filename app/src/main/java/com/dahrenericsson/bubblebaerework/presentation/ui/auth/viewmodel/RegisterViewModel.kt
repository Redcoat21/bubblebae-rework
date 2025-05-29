package com.dahrenericsson.bubblebaerework.presentation.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.dahrenericsson.bubblebaerework.domain.usecase.RegisterUseCase
import com.dahrenericsson.bubblebaerework.presentation.ui.common.viewmodel.BaseViewModel
import com.dahrenericsson.bubblebaerework.domain.common.processResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    BaseViewModel() {
    fun register(username: String, password: String, email: String) {
        setLoading()
        viewModelScope.launch {
            registerUseCase(
                username = username,
                password = password,
                email = email
            ).processResult(onError = { message, _, exception ->
                val message = message ?: exception?.message ?: "An unknown error occurred"
                setError(message)
            }, onSuccess = { data ->
                setSuccess(data)
            })
        }
    }
}

