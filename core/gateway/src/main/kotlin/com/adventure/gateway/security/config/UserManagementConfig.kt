package com.adventure.gateway.security.config

import com.adventure.gateway.security.components.CustomUSerDetailsService
import com.adventure.gateway.security.dao.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class UserManagementConfig {

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService {
        return CustomUSerDetailsService(userRepository)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}