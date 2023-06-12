package com.smarthome.shuserservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
class NotFoundUserException(
    private val userId: Long,
    exceptionMessage: String = "Not found user with userId:$userId"
) : RuntimeException(exceptionMessage)
