package com.sparta.study_demo.common.exception


import com.sparta.study_demo.common.dto.ErrorResponse

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class CommonExceptionHandler:ResponseEntityExceptionHandler() {

    //errorcode를 사용하기 위함.
    private fun handleExceptionInternal(errorCode: ErrorCode):ResponseEntity<ErrorResponse>
        = ResponseEntity
            .status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.getName(),errorCode.message))

    //동욱님이 말씀하신것처럼 domain별로 쪼개기가 어려운것 같다.
//    @ExceptionHandler(NoSuchElementFoundException::class)
//    fun handleNoSuchElementFoundException(e: NoSuchElementFoundException):ResponseEntity<ErrorResponse>
//    = handleExceptionInternal()

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e :IllegalArgumentException):ResponseEntity<ErrorResponse>
    =ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.!!,e.message!!))


}