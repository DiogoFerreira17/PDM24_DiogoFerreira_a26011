package com.diogo.shop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diogo.shop.data.remote.api.AuthApi
import com.diogo.shop.data.remote.api.Firebase
import com.diogo.shop.data.repository.AuthRepositoryImpl
import com.diogo.shop.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel: ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val email: StateFlow<String> = _email
    val password: StateFlow<String> = _password

    private val dbAuth = Firebase.auth
    private val authApi: AuthApi = AuthRepositoryImpl(dbAuth)

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun register(user: User, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                // Create the user in Firebase Auth
                val authResult = authApi.register(user.email, user.password).await()
                val firebaseUser = authResult.user

                if (firebaseUser != null) {
                    //user.id = firebaseUser.uid
                    user.ToAddUserDto()
                    dbAuth.signOut() // Sign out the user
                    onSuccess() // Notify success
                }
            } catch (e: Exception) {
                onError(e) // Handle error
            }
        }
    }

}