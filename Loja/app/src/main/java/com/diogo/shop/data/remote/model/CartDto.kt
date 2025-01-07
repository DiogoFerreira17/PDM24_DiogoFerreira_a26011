package com.diogo.shop.data.remote.model

import com.diogo.shop.domain.model.Phone

data class CartDto (
    val email: String,
    var phones: List<Phone>
)