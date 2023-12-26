package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.Sex
import java.util.Locale.IsoCountryCode
import java.util.UUID

class Commands {
    data class CreateBuyerAccount(
        val buyerId: UUID,
        val details: UserDetails
    )
    data class UserDetails(
        val firstName: String,
        val lastName: String,
        val dob: String,
        val email: String,
        val gender: Sex,
        val countryCode: IsoCountryCode,

    )
    data class CreateSellerAccount(
        val sellerId: UUID,
        val details: UserDetails
    )
}