package com.sparta.study_demo.common.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.boot.context.properties.bind.validation.ValidationErrors
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.validation.FieldError

class ErrorResponse(
    val code : String,
    val message: String,

//    @JsonInclde(JsonInclude.Include.NON_EMPTY)
    val errors :List<ValidationError> = listOf(),
) {
    class ValidationError(
        val filed:String,
        val message:String?
    ){
        companion object {
            fun of(filedError: FieldError): ValidationError {
                return ValidationError(
                    filed = filedError.field,
                    message = filedError.defaultMessage
                )
            }
        }

    }

}

