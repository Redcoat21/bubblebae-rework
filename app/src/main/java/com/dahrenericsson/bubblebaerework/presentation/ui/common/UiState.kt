package com.dahrenericsson.bubblebaerework.presentation.ui.common

sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    data class Success<T>(val data: T) : UiState()
    data class Error(val message: String) : UiState()
}