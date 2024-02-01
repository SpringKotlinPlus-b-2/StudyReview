package com.sparta.study_demo.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.boot.context.properties.bind.validation.ValidationErrors

class ErrorResponse(
    val code :String,
    val message: String,
) {

}