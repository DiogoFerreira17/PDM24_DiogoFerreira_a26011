package com.diogo.shop.data.remote.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object Firebase {
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
}