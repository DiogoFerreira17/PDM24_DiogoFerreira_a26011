package com.diogo.shop.domain.model

data class Cart(
    val email: String = "",
    val phones: List<Phone> = listOf()
)