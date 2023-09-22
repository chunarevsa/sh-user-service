package com.socialhobbies.shuserservice.dto

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String,
)
