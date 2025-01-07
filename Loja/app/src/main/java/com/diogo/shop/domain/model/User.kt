package com.diogo.shop.domain.model

import com.diogo.shop.data.remote.model.UserDto

class User (
    val email:String,
    val password: String,
){
    fun ToAddUserDto(): UserDto {
        return UserDto(
            email = email,
            password = password
        )
    }
}