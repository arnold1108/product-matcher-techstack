package com.adventure.apis.auth

import com.adventure.apis.accounts.State.Role
import com.fasterxml.jackson.annotation.JsonProperty

class Requests {
    data class SignupRequest(
        @field: JsonProperty("email_address") val emailAddress: String,
        @field: JsonProperty("password") val password: String,
        @field: JsonProperty("role") val role: Role
    )
    data class LoginRequest(
        @field: JsonProperty("email_address") val emailAddress: String,
        @field: JsonProperty("password") val password: String,
    )
}