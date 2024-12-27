package com.diogo.shop.domain.model

import com.diogo.shop.data.remote.model.UserDto

class User (
    //var id: String,
    val email:String,
    val password: String,
){
    fun ToAddUserDto(): UserDto {
        return UserDto(
            //id = id,
            email = email,
            password = password
        )
    }
}