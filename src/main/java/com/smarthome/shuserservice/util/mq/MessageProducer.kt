package com.smarthome.shuserservice.util.mq

import com.smarthome.shuserservice.dto.CreateCartRequest
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
@EnableBinding(CartBinding::class)
open class MessageProducer(
    private val cartBinding: CartBinding
) {
    fun newUserAction(req: CreateCartRequest) {
        val message = MessageBuilder.withPayload(req).build()
        cartBinding.cartOutputChannel().send(message)
    }

}