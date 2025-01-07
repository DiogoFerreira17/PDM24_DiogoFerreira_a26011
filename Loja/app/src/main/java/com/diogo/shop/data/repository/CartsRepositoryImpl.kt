package com.diogo.shop.data.repository
import android.util.Log
import com.diogo.shop.data.remote.model.CartDto
import com.diogo.shop.domain.model.Cart
import com.diogo.shop.domain.repository.CartsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CartsRepositoryImpl(private val api: FirebaseFirestore):CartsRepository {
    override suspend fun addOrUpdateCart(cart: CartDto): CartsRepository.AddOrUpdateCartStatus {
        return try {
            val cartRef = api.collection("carts").document(cart.email)
            cartRef.set(cart).await()
            CartsRepository.AddOrUpdateCartStatus.Success
        } catch (e: Exception) {
            Log.e("Firestore Error", "Erro: ${e.localizedMessage}")
            CartsRepository.AddOrUpdateCartStatus.Failure(e.message ?: "Erro desconhecido")
        }
    }

    override suspend fun getCartByEmail(email: String): Cart? {
        return try {
            val cartRef = api.collection("carts").document(email).get().await()
            if (cartRef.exists()) {
                cartRef.toObject(Cart::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("Firestore Error", "Erro: ${e.localizedMessage}")
            null
        }
    }

    override suspend fun deleteCart(email: String): Boolean {
        return try {
            api.collection("carts").document(email).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }
}