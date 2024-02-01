package com.sparta.study_demo.common.exception

import org.springframework.http.HttpStatus

//#1
// 생성자에 private를 사용하는건 너무 자바스러운데? 어떻게하면 코틀린스럽게 할 수 있을까?
//enum class CommonErrorCode(
//    private val httpStatus: HttpStatus,
//    private val message: String,
//):ErrorCode {
//    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included")
//    ;
//
//
//    //interface로 연결 시켜줘서 의존성을 분리해주고 싶은데 이렇게 하는게 맞는건가?
//    //코틀린은 멤버 변수 생성시에 get, set이 자동으로 만들어진다.
//    override fun getHttpStatus(): HttpStatus
//        = httpStatus;
//
//    override fun getMessage(): String
//        = this.message;
//}

enum class CommonErrorCode(
    override val httpStatus: HttpStatus,
    override val message: String,
):ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included")
    ;

    //코틀린스러운게 맞나?
    override fun getName(): String {
        return name;
    }

}