package com.smarthome.shuserservice.dto

// TODO add restrictions
data class UpdateUserRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val password: String? = null,
)
