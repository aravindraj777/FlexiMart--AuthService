package com.flexiMart.auth_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val filter: AccessTokenAuthenticationFilter,
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun defaultSecurityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests {
            it.requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        }
            .addFilterAfter(filter, UsernamePasswordAuthenticationFilter::class.java)
            .formLogin { it.defaultSuccessUrl("/") }

        return httpSecurity.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun configureAuthentication(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authenticationProvider)
    }
}