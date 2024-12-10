package com.flexiMart.auth_service.config

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailService(private val userService:UserDetailsService):UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return try {
            val user = userService.getUserWithRoleByUserName(email)
            CustomUserDetails(user)
        }
        catch (e : Exception){
            throw UsernameNotFoundException(e.message)
        }
    }


}