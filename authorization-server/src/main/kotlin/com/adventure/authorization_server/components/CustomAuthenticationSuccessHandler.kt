package com.adventure.authorization_server.components

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val authorities = authentication.authorities
        val redirectUri = if (authorities.any { it.authority == "SELLER" }) {
            "/store"
        } else {
            "/explore"
        }

        response.sendRedirect(redirectUri)
    }
}