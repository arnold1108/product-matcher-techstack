package com.adventure.gateway.security.dao.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class Tokens {
    @Id
    @Column(name = "token_id")
    var tokenId: UUID = UUID.randomUUID()
    @Column(name = "identifier")
    var identifier: String = ""
    @Column(name = "token")
    var token: String = ""

    companion object {
        fun createToken(
            token: String,
            identifier: String
        ): Tokens = Tokens().apply {
            this.token = token
            this.identifier = identifier

        }
    }
}