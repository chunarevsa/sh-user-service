package com.smarthome.userservice.dto

// TODO add restrictions
data class UpdateUserRequest(
    val id: Long,
    val firstName: String? = null,
    val lastName: String? = null,
    val password: String? = null,
)
