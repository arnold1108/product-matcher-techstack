package com.adventure.gateway.security.dao.entity

import com.adventure.apis.accounts.State
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.*

@Entity(name = "soko_users")
class User {
    @Id
    @Column(name = "principal_id") var principalId: UUID = UUID.randomUUID()
    @Column(name = "email_address") var userName: String = ""
    @Column(name = "password") var password: String = ""
    @Column(name = "role")
    @Enumerated var role: State.Role = State.Role.SELLER
}