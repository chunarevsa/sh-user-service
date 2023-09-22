package com.socialhobbies.shuserservice.dto


data class CreateCartRequest(
    val userId: Long,
    val itemsUnit: MutableList<ItemUnitDto>?
)
