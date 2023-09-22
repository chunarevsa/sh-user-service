package com.socialhobbies.shuserservice.util.mq.func

import com.socialhobbies.shuserservice.dto.CreateCartRequest
import com.socialhobbies.shuserservice.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import reactor.core.publisher.Sinks

@Service
class MessageFuncActions(
    private val messageFunc: MessageFunc
) {
    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun sendNewUserMessage(req: CreateCartRequest) {
        messageFunc.innerShell.emitNext(
            MessageBuilder.withPayload(req).build(),
            Sinks.EmitFailureHandler.FAIL_FAST)
        log.info("Message sent:${req}")
    }
}

