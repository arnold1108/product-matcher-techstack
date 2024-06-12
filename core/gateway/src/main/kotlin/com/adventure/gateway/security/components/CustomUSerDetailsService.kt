package com.adventure.gateway.security.components

import com.adventure.gateway.security.dao.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUSerDetailsService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUserName(username)
        return user.map {
            CustomUserDetails(it)
        }.orElseThrow { UsernameNotFoundException("User $username not found!!")}
    }
}