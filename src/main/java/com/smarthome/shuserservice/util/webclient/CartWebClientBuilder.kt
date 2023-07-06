package com.smarthome.shuserservice.util.webclient

import com.smarthome.shuserservice.dto.CreateCartRequest
import com.smarthome.shuserservice.entity.Cart
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Component
class CartWebClientBuilder {

    companion object {
        const val baseUrl = "http://localhost:8765/api/v1/cart/"
    }

    fun createCart(userId: Long): Flux<Cart> =
        WebClient.create(baseUrl)
            .post()
            .uri("create")
            .bodyValue(CreateCartRequest(userId, null))
            .retrieve()
            .bodyToFlux(Cart::class.java)

    fun deleteCart(userId: Long): Flux<Void> = // TODO: It doesn't work!
        WebClient.create(baseUrl)
            .post()
            .uri("${userId}/delete")
            .retrieve()
            .bodyToFlux(Void::class.java)

}