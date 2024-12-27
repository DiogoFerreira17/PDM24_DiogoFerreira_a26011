package com.diogo.shop.data.remote.api

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthApi {
    fun login(email: String, password: String): Task<AuthResult>
    fun register(email: String,password: String): Task<AuthResult>
}