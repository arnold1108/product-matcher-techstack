package com.adventure.authorization_server.dao.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.*

@Entity(name = "soko_users")
class Users {
    @Id
    @Column(name = "principal_id") var principalId: UUID = UUID.randomUUID()
    @Column(name = "email_address") var userName: String = ""
    @Column(name = "password") var password: String = ""
    @Column(name = "role")
    @Enumerated
    var role: Role = Role.SELLER

    companion object {
        fun newUser(
            principalId: UUID,
            userName: String,
            password: String,
            role: Role
        ) = Users().apply {
            this.principalId = principalId
            this.userName = userName
            this.password = password
            this.role = role

        }
    }

    fun updatePassword(newPassword: String) = Users().apply { this.password = newPassword }
}

enum class Role(val value: String) {
    BUYER("Buyer"),
    SELLER("Seller"),
}