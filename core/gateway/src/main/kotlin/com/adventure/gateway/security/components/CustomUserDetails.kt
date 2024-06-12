package com.adventure.gateway.security.components

import com.adventure.gateway.security.dao.entity.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(private val user: Users): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            GrantedAuthority { user.role.value }
        )
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}