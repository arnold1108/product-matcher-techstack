package com.adventure.gateway.security.config

import com.adventure.apis.accounts.State
import com.adventure.gateway.security.components.CustomAuthenticationProvider
import com.adventure.gateway.security.components.CustomCsrfTokenRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler

@Configuration
@EnableMethodSecurity
open class WeAuthorizationConfig(
    private val authenticationProvider: CustomAuthenticationProvider,
    private val tokenRepository: CustomCsrfTokenRepository
) {
    @Bean
    open fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .formLogin(Customizer.withDefaults())
            .authenticationProvider(authenticationProvider)
            .csrf {
                it.csrfTokenRepository(tokenRepository)
                it.csrfTokenRequestHandler(CsrfTokenRequestAttributeHandler())  // generates a new token during a GET request
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/store").hasRole(State.Role.SELLER.value)
                    .anyRequest().authenticated()
            }
        return httpSecurity.build()
    }
    

}