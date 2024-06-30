package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.Role
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.util.UUID

class Commands {
    data class CreateAccount(
        @TargetAggregateIdentifier
        val accountId: UUID,
        val firstName: String,
        val lastName: String,
        val dateOfBirth: LocalDate,
        val email: String,
        val gender: String,
        val country: String,
        val role: Role
    )
}