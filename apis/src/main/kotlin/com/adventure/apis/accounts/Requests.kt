package com.adventure.apis.accounts

import com.adventure.apis.accounts.State.*
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class Requests {
    data class CreateAccountRequest(
        @JsonProperty("first_name")
        val firstName: String,
        @JsonProperty("last_name")
        val lastName: String,
        @JsonProperty("date_of_birth")
        val dob: LocalDateTime,
        @JsonProperty("email_address")
        val email: String,
        @JsonProperty("gender")
        val gender: Sex,
        @JsonProperty("country")
        val country: String,
        @JsonProperty("role")
        val role: Role
    )

}