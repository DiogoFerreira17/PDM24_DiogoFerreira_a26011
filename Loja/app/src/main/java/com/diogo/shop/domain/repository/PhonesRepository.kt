package com.diogo.shop.domain.repository

import com.diogo.shop.domain.model.Phone

interface PhonesRepository {
    suspend fun getAllPhones(): List<Phone>
}