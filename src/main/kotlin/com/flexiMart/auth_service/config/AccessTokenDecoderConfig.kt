package com.flexiMart.auth_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider
import java.security.interfaces.RSAPublicKey

@Configuration
class AccessTokenDecoderConfig {

    @Bean
    fun bearerTokenAuthenticationManager(): AuthenticationManager {
        val decoder: JwtDecoder = NimbusJwtDecoder.withPublicKey(KeyPairConfig.getKeyPair().public as RSAPublicKey)
            .signatureAlgorithm(SignatureAlgorithm.RS256)
            .build()

        return ProviderManager(JwtAuthenticationProvider(decoder))
    }
}