package com.smarthome.shuserservice.util.mq

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface CartBinding {

    companion object {
        const val OUTPUT_CHANNEL: String = "cartOutputChannel"
    }

    @Output(OUTPUT_CHANNEL)
    fun cartOutputChannel(): MessageChannel


}