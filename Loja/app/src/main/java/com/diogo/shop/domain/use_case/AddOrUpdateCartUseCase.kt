package com.diogo.shop.domain.use_case

import android.util.Log
import com.diogo.shop.data.remote.model.CartDto
import com.diogo.shop.domain.repository.CartsRepository

class AddOrUpdateCartUseCase(private val repository: CartsRepository) {
    suspend operator fun invoke(cart: CartDto): CartsRepository.AddOrUpdateCartStatus
    {
        //Log.e("aqui", cart.phones.toString())
        return repository.addOrUpdateCart(cart)
    }
}