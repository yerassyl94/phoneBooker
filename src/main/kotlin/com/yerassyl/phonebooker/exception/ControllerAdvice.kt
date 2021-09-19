package com.yerassyl.phonebooker.exception

import com.yerassyl.phonebooker.model.response.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdvice : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ExceptionResponse> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ExceptionResponse(message = exception.message, status = HttpStatus.NOT_FOUND.reasonPhrase))

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException): ResponseEntity<ExceptionResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(message = exception.message, status = HttpStatus.BAD_REQUEST.reasonPhrase))
}
