package com.adventure.gateway.security.dao.repository

import com.adventure.gateway.security.dao.entity.Tokens
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository: JpaRepository<Tokens, UUID> {
    fun findTokenByIdentifier(identifier: String): Optional<Tokens>

}