package com.adventure.authorization_server.api

import com.adventure.authorization_server.dao.entity.Role
import com.adventure.authorization_server.service.AuthService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val auth: AuthService) {

    @PostMapping(SIGNUP_MAPPING)
    fun signUp(@RequestBody request: SignupRequest) {
        ResponseEntity.ok(auth.createUser(requestBody = request))
    }

    @PostMapping(RECOVER_ACCOUNT)
    fun recoverAccount(request: RecoverAccountRequest) {
        ResponseEntity.ok(auth.resetPassword(requestBody = request))
    }

    @PostMapping(UPDATE_PASSWORD)
    fun updatePassword(request: UpdatePasswordRequest) {
        ResponseEntity.ok(auth.updatePassword(requestBody = request))
    }

    companion object {
        const val SIGNUP_MAPPING = "/auth/signUp"
        const val RECOVER_ACCOUNT = "/auth/recoverAccount"
        const val UPDATE_PASSWORD = "/auth/updatePassword"
    }
}

data class SignupRequest(
    val emailAddress: String,
    val password: String,
    val role: Role
)

data class RecoverAccountRequest(
    val newPassword: String,
    val emailAddress: String
)
data class UpdatePasswordRequest(
    val emailAddress: String,
    val oldPassword: String,
    val newPassword: String
)