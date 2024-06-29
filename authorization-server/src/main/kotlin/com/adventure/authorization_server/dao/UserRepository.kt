package com.adventure.authorization_server.dao


import com.adventure.authorization_server.dao.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<Users, UUID> {
    fun findByUserName(username: String): Optional<Users>
    fun existsByUserName(userName: String): Boolean
}

