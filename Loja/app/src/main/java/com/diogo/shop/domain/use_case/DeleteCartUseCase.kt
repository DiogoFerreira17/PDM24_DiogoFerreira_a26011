package com.diogo.shop.domain.use_case

import com.diogo.shop.domain.repository.CartsRepository

class DeleteCartUseCase (private val repository: CartsRepository){
    suspend operator fun invoke(email: String): Boolean
    {
        return repository.deleteCart(email)
    }
}