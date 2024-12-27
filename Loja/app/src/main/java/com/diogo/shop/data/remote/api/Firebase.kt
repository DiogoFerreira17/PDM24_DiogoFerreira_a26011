package com.diogo.shop.data.remote.api

import com.google.firebase.auth.FirebaseAuth

object Firebase {
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
}