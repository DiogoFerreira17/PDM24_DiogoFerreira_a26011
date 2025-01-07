package com.diogo.shop.domain.use_case

import com.diogo.shop.domain.model.Phone
import com.diogo.shop.domain.repository.PhonesRepository

class GetAllPhonesUseCase(private val repository: PhonesRepository) {
    suspend operator fun invoke(): List<Phone>
    {
        return repository.getAllPhones()
    }
}