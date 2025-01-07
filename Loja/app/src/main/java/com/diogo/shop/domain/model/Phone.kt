package com.diogo.shop.domain.model

data class Phone(
    val id: String = "",
    val name: String = "",
    val price: Float = 0f
){
    override fun toString(): String {
        return "Phone(id=$id, name='$name', price=$price)"
    }
}