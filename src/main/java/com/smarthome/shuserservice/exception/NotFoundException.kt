package com.smarthome.shuserservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(
    private val nameEntity: String,
    private val param: String,
    exceptionMessage: String = "Not found $nameEntity with $param"
) : RuntimeException(exceptionMessage)
