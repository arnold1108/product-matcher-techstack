package com.adventure.gateway.security.components

import com.adventure.gateway.security.dao.entity.Tokens
import com.adventure.gateway.security.dao.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CustomCsrfTokenRepository(private val tokenRepository: TokenRepository) : CsrfTokenRepository {
    override fun generateToken(request: HttpServletRequest): CsrfToken {
        val randomTokenId = UUID.randomUUID().toString()
        return DefaultCsrfToken(
            "X-CSRF-TOKEN",
            "_csrf",
            randomTokenId
        )
    }

    override fun saveToken(token: CsrfToken, request: HttpServletRequest, response: HttpServletResponse) {
        val tokenIdentifier = request.getHeader("X-IDENTIFIER")
        val existingToken = tokenRepository.findTokenByIdentifier(tokenIdentifier)

        if (existingToken.isPresent) {
            val csrfToken = existingToken.get()
            csrfToken.token = token.token
        } else {
            val newToken = Tokens.createToken(
                token = token.token,
                identifier = tokenIdentifier
            )
            tokenRepository.save(newToken)
        }
    }

    override fun loadToken(request: HttpServletRequest): CsrfToken? {
        val tokenIdentifier = request.getHeader("X-IDENTIFIER")
        val token = tokenRepository.findTokenByIdentifier(tokenIdentifier)

        return if (token.isPresent) {
            DefaultCsrfToken(
                "X-CSRF-TOKEN",
                "_csrf",
                token.get().token
            )
        } else null
    }
}