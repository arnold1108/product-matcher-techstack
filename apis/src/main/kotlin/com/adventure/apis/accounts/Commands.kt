package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.Role
import com.adventure.apis.accounts.State.Sex
import java.time.LocalDate
import java.util.UUID

class Commands {
    data class CreateAccount(
        val userId: UUID,
        val firstName: String,
        val lastName: String,
        val dateOfBirth: LocalDate,
        val email: String,
        val gender: Sex,
        val country: String,
        val role: Role
    )
}