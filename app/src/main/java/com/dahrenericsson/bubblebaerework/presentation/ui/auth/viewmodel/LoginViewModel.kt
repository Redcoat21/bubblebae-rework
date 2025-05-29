package com.dahrenericsson.bubblebaerework.presentation.ui.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.dahrenericsson.bubblebaerework.domain.common.processResult
import com.dahrenericsson.bubblebaerework.domain.usecase.LoginUseCase
import com.dahrenericsson.bubblebaerework.presentation.ui.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {
    fun login(username: String, password: String) = viewModelScope.launch {
        setLoading()
        loginUseCase(username, password).processResult(
            onError = { message, _, exception ->
                val message = message ?: exception?.message ?: "An unknown error occurred"
                setError(message)
            },
            onSuccess = { data ->
                setSuccess(data)
            }
        )
    }
}

