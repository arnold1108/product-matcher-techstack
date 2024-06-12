package com.adventure.gateway.service

import com.adventure.gateway.security.dao.repository.TokenRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val tokenRepository: TokenRepository
) {

    fun accountExists(emailAddress: String): Boolean {
        return tokenRepository.existsByUserName(userName = emailAddress)  // We will use email address as the username
    }
}