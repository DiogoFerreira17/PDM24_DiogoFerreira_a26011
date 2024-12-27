package com.diogo.shop.data.repository

import com.diogo.shop.data.remote.api.AuthApi
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(private val auth: FirebaseAuth):AuthApi {
    override fun login(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email,password)
    }

    override fun register(email:String, password: String): Task<AuthResult>{
        return auth.createUserWithEmailAndPassword(email,password)
    }
}