package com.sparta.study_demo.common.exception


import com.sparta.study_demo.common.dto.ErrorResponse
import io.github.oshai.kotlinlogging.KotlinLogging

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.Exception


private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class CommonExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        // 401, 403, 404(x)
        logger.warn(e)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e., e.message!!))
    }


    @ExceptionHandler(Exception::class)
    fun handleAllException(exception: Exception): ResponseEntity<ErrorResponse> {
        logger.warn(exception)
        return handleExceptionInternal(exception.)
    }

    private fun handleExceptionInternal(errorCode: ErrorCode): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(errorCode.httpStatus).body(ErrorResponse(errorCode.getName(), errorCode.message))

    private fun handleExceptionInternal(errorCode: ErrorCode, message: String): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(errorCode.httpStatus).body(ErrorResponse(errorCode.getName(), errorCode.message))


    private fun handleExceptionInternal(exception: BindException, errorCode: ErrorCode): ResponseEntity<ErrorResponse> =
        exception.bindingResult.getFieldErrors().map{ErrorResponse.ValidationError.of(it)}
            .let {
                ErrorResponse(
                    code = errorCode.getName(),
                    message = errorCode.message,
                    )
                    .let {
                ResponseEntity.status(errorCode.httpStatus).body(it)
            }
        }
}

//에러처리 1차구분은 도마인별로 처리할 예정  -> ENUM 클래스에서 할예정
// 모든에러는 글로벌 에러에서 로그 찍힌후 처리 예정