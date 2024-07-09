package com.adventure.gateway.security
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//@EnableMethodSecurity
//class AuthorizationConfig(private val jwtAuthenticationConverter: JwtAuthenticationConverter) {
//    @Value("\${keySetUri}")
//    private lateinit var keySetUri: String
//
//    @Bean
//    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
//
//        return httpSecurity
//            .authorizeHttpRequests {
//                it.requestMatchers("/auth").permitAll()
//                    .anyRequest().authenticated()
//            }
//            .oauth2Login {  }
//            .oauth2ResourceServer {
//                it.jwt { token ->
//                    token.jwkSetUri(keySetUri)
//                        .jwtAuthenticationConverter(jwtAuthenticationConverter)
//                }
//            }
//            .build()
//    }
//
//}