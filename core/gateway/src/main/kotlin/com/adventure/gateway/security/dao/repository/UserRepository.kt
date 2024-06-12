package com.adventure.gateway.security.dao.repository

import com.adventure.gateway.security.dao.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository: JpaRepository<User, UUID> {
    fun findByUserName(username: String): Optional<User>
}