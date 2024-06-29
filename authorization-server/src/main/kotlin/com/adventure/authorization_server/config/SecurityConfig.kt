package com.adventure.authorization_server.config

import com.adventure.authorization_server.components.CustomAuthenticationSuccessHandler
import com.adventure.authorization_server.components.CustomUserDetails
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint


@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler
) {

    @Bean
    @Order(1)
    fun asFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity)
        httpSecurity
            .getConfigurer(OAuth2AuthorizationServerConfigurer::class.java)
            .oidc(Customizer.withDefaults())

        httpSecurity.exceptionHandling {
            it.authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))
        }
        return httpSecurity.build()
    }

    @Bean
    @Order(2)
    fun defaultSecurityChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .formLogin{
                it.successHandler(customAuthenticationSuccessHandler)
            }
            .logout {
                it.logoutSuccessHandler { _, response, _ ->
                    response.sendRedirect("/login")
                }
            }
            .authorizeHttpRequests {
                it.requestMatchers("/auth").permitAll()
                    .anyRequest().authenticated()
            }
            .build()
    }

    @Bean
    fun jwtCustomizer(): OAuth2TokenCustomizer<JwtEncodingContext> {
        return OAuth2TokenCustomizer {
            val principal = it.getPrincipal<Authentication>()

            if (principal != null) {
                val customUserDetails = principal.principal as CustomUserDetails
                val userId = customUserDetails.getUserId()
                it.claims.claim("userId", userId.toString())
            }
        }
    }

}