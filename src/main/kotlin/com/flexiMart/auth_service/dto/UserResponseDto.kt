package com.flexiMart.auth_service.dto

import lombok.Builder

@Builder
data class UserResponseDto(
    val id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null
)
