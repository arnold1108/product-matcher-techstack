package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.Role
import java.time.LocalDate
import java.util.*

class Events {
    
    data class AccountCreated(
        val accountId: UUID,
        val firstName: String,
        val lastName: String,
        val dateOfBirth: LocalDate,
        val email: String,
        val gender: String,
        val country: String,
        val role: Role
    )
    data class AccountSuspended(
        val accountId: UUID
    )
}