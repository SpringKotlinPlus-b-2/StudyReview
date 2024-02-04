package com.sparta.study_demo.common.exception.exceptions

import com.sparta.study_demo.common.exception.ErrorCode
import org.springframework.http.HttpStatus

class NoSuchElementFoundException(
 val errorCode:ErrorCode,
 val data: Map<String,Any> = mapOf()
)
 : GlobalException(errorCode, data) {


}