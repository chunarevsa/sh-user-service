package com.smarthome.userservice.dto

data class EditRoleRequest(
    var userId: Long,
    var role: String,
)
