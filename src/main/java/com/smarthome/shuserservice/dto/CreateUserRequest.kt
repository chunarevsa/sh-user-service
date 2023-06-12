package com.smarthome.userservice.dto

// TODO add restrictions
data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String,
)
