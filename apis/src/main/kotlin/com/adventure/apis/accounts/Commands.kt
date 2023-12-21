package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.Sex
import java.util.UUID

class Commands {
    data class CreateBuyerAccount(
        val accountId: UUID,
        val firstName: String,
        val lastName: String,
        val email: String,
        val gender: Sex
    )
    data class DeleteBuyerAccount(
        val accountId: UUID
    )
    data class CreateSellerAccount(
        val accountId: UUID,
        val firstName: String,
        val lastName: String,
        val email: String,
        val gender: Sex
    )
    data class DeleteSellerAccount(
        val accountId: UUID
    )
}