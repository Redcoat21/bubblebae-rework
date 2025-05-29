package com.dahrenericsson.bubblebaerework.presentation.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dahrenericsson.bubblebaerework.domain.common.processResult
import com.dahrenericsson.bubblebaerework.domain.usecase.LoginUseCase
import com.dahrenericsson.bubblebaerework.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        loginUseCase(username, password).processResult(
            onError = { message, _, exception ->
                _uiState.value = UiState.Error(
                    message = message ?: exception?.message ?: "An unknown error occurred"
                )
            },
            onSuccess = { data ->
                _uiState.value = UiState.Success(data)
            }
        )
    }
}

