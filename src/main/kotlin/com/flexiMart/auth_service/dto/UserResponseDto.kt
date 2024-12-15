package com.flexiMart.auth_service.dto




data class UserResponseDto(
    val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null
)
