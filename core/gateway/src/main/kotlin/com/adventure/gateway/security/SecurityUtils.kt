package com.adventure.gateway.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import java.util.UUID

object SecurityUtils {

    fun extractUserId(): UUID {
        val securityContext = SecurityContextHolder.getContext()
        val jwtAuthenticationToken = securityContext.authentication as JwtAuthenticationToken
        return UUID.fromString(jwtAuthenticationToken.token.subject)
    }

    fun extractEmailAddress(): String {
        val securityContext = SecurityContextHolder.getContext()
        val jwtAuthenticationToken = securityContext.authentication as JwtAuthenticationToken
        return jwtAuthenticationToken.name
    }
}