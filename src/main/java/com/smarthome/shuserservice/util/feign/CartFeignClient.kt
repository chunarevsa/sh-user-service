package com.smarthome.shuserservice.util.feign

import com.smarthome.shuserservice.dto.CreateCartRequest
import com.smarthome.shuserservice.entity.Cart
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "sh-cart-service")
interface CartFeignClient {

    @PostMapping("/create")
    fun createOrUpdateCart(@RequestBody req: CreateCartRequest): ResponseEntity<Cart>

    @PostMapping("/{userId}/delete")
    fun delete(@PathVariable userId: Long): ResponseEntity<Void>


}