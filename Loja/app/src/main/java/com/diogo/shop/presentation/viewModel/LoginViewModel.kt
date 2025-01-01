package com.diogo.shop.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.diogo.shop.data.remote.api.AuthApi
import com.diogo.shop.data.remote.api.Firebase
import com.diogo.shop.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
    sealed class LoginState {
        object Idle : LoginState() // No ongoing login process
        object Loading : LoginState() // Login in progress
        object Success : LoginState() // Login successful
        data class Error(val message: String) : LoginState() // Login failed with error message
    }

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _currentUser = MutableStateFlow(Firebase.auth.currentUser)

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    private val authApi: AuthApi = AuthRepositoryImpl(Firebase.auth)

    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val currentUser: StateFlow<FirebaseUser?> = _currentUser
    val loginState: StateFlow<LoginState> = _loginState

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        _loginState.value = LoginState.Loading
        authApi.login(_email.value, _password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _currentUser.value = Firebase.auth.currentUser
                    _loginState.value = LoginState.Success
                } else {
                    val exception = task.exception
                    _loginState.value = LoginState.Error("Email ou Password errados")
                }
            }
    }

}