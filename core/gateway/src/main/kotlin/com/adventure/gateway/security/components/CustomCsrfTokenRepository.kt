package com.adventure.gateway.security.components

import org.springframework.stereotype.Component

@Component
class CustomCsrfTokenRepository(private val tokenRepository: Token) {
}