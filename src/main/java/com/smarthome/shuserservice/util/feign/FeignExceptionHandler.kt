package com.smarthome.shuserservice.util.feign

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class FeignExceptionHandler : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception =
        ResponseStatusException(HttpStatus.valueOf(response.status()), response.reason())

}