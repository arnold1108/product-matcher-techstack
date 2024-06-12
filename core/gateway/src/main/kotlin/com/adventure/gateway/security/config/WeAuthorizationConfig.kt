package com.adventure.gateway.security.config

import com.adventure.gateway.security.components.CustomAuthenticationProvider
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@Configuration
@EnableMethodSecurity
open class WeAuthorizationConfig(
    private val authenticationProvider: CustomAuthenticationProvider
) {

}