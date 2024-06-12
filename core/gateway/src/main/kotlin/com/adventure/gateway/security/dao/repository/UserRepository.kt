package com.adventure.gateway.security.dao.repository

import com.adventure.gateway.security.dao.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository: JpaRepository<Users, UUID> {
    fun findByUserName(username: String): Optional<Users>
    fun existsByUserName(userName: String): Boolean
}