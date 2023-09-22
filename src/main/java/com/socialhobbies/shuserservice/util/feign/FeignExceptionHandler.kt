package com.socialhobbies.shuserservice.util.feign

import com.google.common.io.CharStreams
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import java.io.Reader
import java.nio.charset.Charset

@Component
class FeignExceptionHandler : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception =
        ResponseStatusException(HttpStatus.valueOf(response.status()), readMessage(response))

    private fun readMessage(response: Response): String? {
        var reader: Reader? = null
        var message: String? = null
        try {
            reader = response.body().asReader(Charset.defaultCharset())
            message = reader?.let { CharStreams.toString(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            reader?.close()
        }

        return message
    }
}