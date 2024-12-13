package com.flexiMart.auth_service


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

@Configuration
class KeyPairConfig {

    companion object {
        private val keyPair: KeyPair = generateRsaKey()

        @JvmStatic
        @Throws(NoSuchAlgorithmException::class)
        private fun generateRsaKey(): KeyPair {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            return keyPairGenerator.generateKeyPair()
        }

        @JvmStatic
        fun getKeyPair(): KeyPair = keyPair
    }

    @Bean
    @Throws(NoSuchAlgorithmException::class)
    fun jwkSource(): JWKSource<SecurityContext> {
        val rsaKey = generateRsa()
        val jwkSet = JWKSet(rsaKey)

        return JWKSource { jwkSelector, _ -> jwkSelector.select(jwkSet) }
    }

    private fun generateRsa(): RSAKey {
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey

        return RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build()
    }
}
