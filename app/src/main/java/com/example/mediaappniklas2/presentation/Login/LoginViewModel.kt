package com.example.mediaappniklas2.presentation.Login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun performLogin(credentials: Credentials) {
        if (credentials.isNotEmpty() && credentials.login == "admin") {
            _loginState.value = LoginState(success = true)
        } else {
            _loginState.value = LoginState(error = "Wrong Credentials")
        }
    }
}


data class LoginState(val success: Boolean = false, val error: String? = null)