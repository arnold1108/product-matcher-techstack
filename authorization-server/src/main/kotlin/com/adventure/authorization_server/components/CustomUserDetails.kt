package com.adventure.authorization_server.components

import com.adventure.authorization_server.dao.entity.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class CustomUserDetails(private val users: Users): UserDetails {

    fun getUserId(): UUID {
        return users.principalId
    }
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(
            GrantedAuthority { users.role.value }
        )
    }

    override fun getPassword(): String {
        return users.password
    }

    override fun getUsername(): String {
        return users.userName
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