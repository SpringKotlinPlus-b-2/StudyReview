package com.umin.todoapp.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String
)
