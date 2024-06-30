package com.adventure.apis.accounts

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class Requests {
    data class CompleteSignupRequest(
        @JsonProperty("first_name")
        val firstName: String,
        @JsonProperty("last_name")
        val lastName: String,
        @JsonProperty("date_of_birth")
        val dob: LocalDate,
        @JsonProperty("gender")
        val gender: String,
        @JsonProperty("country")
        val country: String,
    )

}