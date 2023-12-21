package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.Sex
import java.util.UUID

class Commands {
    data class CreateBuyerAccount(
        val buyerId: UUID,
        val firstName: String,
        val lastName: String,
        val email: String,
        val gender: Sex
    )
    data class CreateSellerAccount(
        val sellerId: UUID,
        val firstName: String,
        val lastName: String,
        val email: String,
        val gender: Sex
    )
}