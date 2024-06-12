package com.adventure.gateway.security.components

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val userName = authentication.name
        val password = authentication.credentials.toString()
        val userDetails = userDetailsService.loadUserByUsername(userName)

        if (passwordEncoder.matches(password, userDetails.password)) {
            return UsernamePasswordAuthenticationToken(userName, password)
        } else throw BadCredentialsException("Invalid Credentials")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}