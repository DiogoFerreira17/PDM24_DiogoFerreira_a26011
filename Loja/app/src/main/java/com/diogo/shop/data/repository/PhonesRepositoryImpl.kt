package com.diogo.shop.data.repository

import com.diogo.shop.domain.model.Phone
import com.diogo.shop.domain.repository.PhonesRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PhonesRepositoryImpl(private val api: FirebaseFirestore): PhonesRepository {
    override suspend fun getAllPhones(): List<Phone> {
        val snapshot = api.collection("phones")
            .get()
            .await()
        return snapshot.documents.mapNotNull { document ->
            try {
                val phone = document.toObject(Phone::class.java)?.copy(id = document.id)
                phone
            } catch (e: Exception) {
                null
            }
        }
    }
}