package com.sparta.study_demo.common.exception

import org.springframework.http.HttpStatus


//#1
//interface ErrorCode {
////    val httpStatus:HttpStatus
////    val message:String
//    fun getHttpStatus():HttpStatus
//    fun getMessage():String
//}


interface ErrorCode {
    val httpStatus:HttpStatus
    val message:String
    fun getName():String; // enum클래스에 name을 변경
}

