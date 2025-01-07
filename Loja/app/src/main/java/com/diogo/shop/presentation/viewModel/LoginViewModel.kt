package com.diogo.shop.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.diogo.shop.data.remote.api.AuthApi
import com.diogo.shop.data.remote.api.Firebase
import com.diogo.shop.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _currentUser = MutableStateFlow(Firebase.auth.currentUser)

    private val authApi: AuthApi = AuthRepositoryImpl(Firebase.auth)

    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        authApi.login(_email.value, _password.value)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _currentUser.value = Firebase.auth.currentUser
                }
            }
    }
}