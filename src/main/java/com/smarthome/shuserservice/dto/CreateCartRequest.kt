package com.smarthome.shuserservice.dto


data class CreateCartRequest(
    val userId: Long,
    val itemsUnit: MutableList<ItemUnitDto>?
)
