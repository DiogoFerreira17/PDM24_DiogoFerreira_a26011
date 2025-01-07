package com.diogo.shop.domain.repository

import com.diogo.shop.data.remote.model.CartDto
import com.diogo.shop.domain.model.Cart

interface CartsRepository {
    suspend fun addOrUpdateCart(cart: CartDto):AddOrUpdateCartStatus
    suspend fun getCartByEmail(email: String): Cart?
    suspend fun deleteCart(email: String): Boolean

    sealed class AddOrUpdateCartStatus {
        object Success : AddOrUpdateCartStatus()
        data class Failure(val error: String) : AddOrUpdateCartStatus()
    }
}