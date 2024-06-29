package com.adventure.authorization_server.service

import com.adventure.authorization_server.components.CustomUserDetails
import com.adventure.authorization_server.dao.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByUserName(username)
        return user.map {
            CustomUserDetails(it)
        }.orElseThrow { UsernameNotFoundException("User $username not found!") }
    }
}