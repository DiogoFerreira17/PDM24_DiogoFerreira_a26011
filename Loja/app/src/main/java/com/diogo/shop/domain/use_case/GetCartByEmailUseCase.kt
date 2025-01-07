package com.diogo.shop.domain.use_case

import android.util.Log
import com.diogo.shop.domain.model.Cart
import com.diogo.shop.domain.repository.CartsRepository

class GetCartByEmailUseCase(private val repository: CartsRepository) {
    suspend operator fun invoke(email: String): Cart?
    {
        //Log.e("aqui",repository.getCartByEmail(email).toString())
        return repository.getCartByEmail(email)
    }
}