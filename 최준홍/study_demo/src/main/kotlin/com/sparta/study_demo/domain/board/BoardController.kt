package com.sparta.study_demo.domain.board

import com.sparta.study_demo.common.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class BoardController {

    @GetMapping("/test")
    fun test():ResponseEntity<ErrorResponse>{
        println("test")

        throw IllegalArgumentException("테스트 메시지.")

    }

}