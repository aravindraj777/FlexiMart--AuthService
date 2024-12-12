package com.flexiMart.auth_service.config

import com.flexiMart.auth_service.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
data class CustomUserDetails(private val user: User):UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
       return listOf(SimpleGrantedAuthority(user.role?.name ?: "ROLE_DEFAULT"))
    }

    override fun getPassword(): String {
       return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }


    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return true
    }

}