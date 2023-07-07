package com.smarthome.shuserservice.util.feign

import com.smarthome.shuserservice.dto.CreateCartRequest
import com.smarthome.shuserservice.entity.Cart
import com.smarthome.shuserservice.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "sh-cart-service", fallback = CartFeignClientFallback::class)
interface CartFeignClient {

    @PostMapping("/create")
    fun createOrUpdateCart(@RequestBody req: CreateCartRequest): ResponseEntity<Cart>?

    @PostMapping("/{userId}/delete")
    fun delete(@PathVariable userId: Long): ResponseEntity<Void>?

}

@Component
class CartFeignClientFallback : CartFeignClient {

    private val ENTITY_NAME = "cart"

    private val log: Logger = LoggerFactory.getLogger(CartFeignClientFallback::class.java)
    override fun createOrUpdateCart(req: CreateCartRequest): ResponseEntity<Cart>? {
        log.error("Service sh-cart-service is not available. Request: /create")
        return null
    }

    override fun delete(userId: Long): ResponseEntity<Void> {
        log.error("Service sh-cart-service is not available. Request: /${userId}/delete")
        throw NotFoundException(ENTITY_NAME, "userId:${userId}")
    }

}