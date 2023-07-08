package com.smarthome.shuserservice.util.mq.func

import com.smarthome.shuserservice.dto.CreateCartRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.util.concurrent.Queues
import java.util.function.Supplier

@Configuration
open class MessageFunc {

    val innerShell: Sinks.Many<Message<CreateCartRequest>> =
        Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false)

    @Bean
    open fun newUserActionProduce(): Supplier<Flux<Message<CreateCartRequest>>> {
        return Supplier { innerShell.asFlux() }
    }
}
